package com.great.cms.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.great.cms.db.entity.CourseTask;
import com.great.cms.db.entity.Task;
import com.great.cms.db.entity.TaskType;
import com.great.cms.service.CourseTaskService;
import com.great.cms.service.TaskService;
import com.great.cms.service.TaskTypeService;

@Controller
@SessionAttributes("organization")
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskTypeService taskTypeService;
	@Autowired
	private CourseTaskService courseTaskService;

	JSONArray jsonArray;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/ajaxtasks")
	public @ResponseBody
	String getTaskList(Model model) {
		System.out.println("get task list method");
		
		// TODO: static list of tasks displayed for course_id 1, change to dynamic
		List<Task> tasks = taskService.getTaskListByCourseId(1);
		model.addAttribute("tasks", tasks);
		jsonArray = new JSONArray();
		if (tasks == null)
			System.out.println("TaskController : LIST IS NULL");

		for (Task t : tasks) {
			JSONArray jObj = new JSONArray();
			jObj.add(t.getTaskId().toString());
			jObj.add(t.getTaskTitle());
			/*
			 * if( t.getTaskTypeId().getTaskTypeId()==1) jObj.add("Project");
			 * else jObj.add("Assignment");
			 */
			jObj.add(t.getTaskTypeId().getTaskTypeId());
			jObj.add(t.getTaskDesc());
			jObj.add(t.getTaskDeadline().toString());
			jObj.add(String.valueOf(t.getIsOpen()));
			jObj.add(String.valueOf(t.getTaskTotalGroupNo()));
			jObj.add(String.valueOf(t.getTaskTotalSubmissonNo()));

			jsonArray.add(jObj);

		}
		JSONObject parameters = new JSONObject();

		parameters.put("draw", 1);

		parameters.put("recordsTotal", 1);

		parameters.put("recordsFiltered", 1);

		parameters.put("data", jsonArray);

		String taskJson = parameters.toJSONString();

		// System.out.print("DLSJDHSLKJDH:  "+taskJson);
		return taskJson;

	}

	@RequestMapping(value = "/edittask", method = RequestMethod.POST)
	public @ResponseBody
	String updateTask(Task task, BindingResult result,
			@RequestParam("taskTypeId") int taskType,
			@RequestParam("taskId") int taskId) {
		TaskType tt = new TaskType();
		tt.setTaskTypeId(taskType);
		task.setTaskId(taskId);
		task.setTaskTypeId(tt);

		taskService.updateTask(task);
		return "{ \"success\" : true }";

	}

	@RequestMapping(value = "/addtask", method = RequestMethod.POST)
	public @ResponseBody
	String addTask(Task task, BindingResult result,
			@RequestParam("taskTypeId") int taskType) {
		System.out.println("TaskController.java: Calling the addTask() method");
		TaskType tt = new TaskType();
		tt.setTaskTypeId(taskType);
		task.setTaskTypeId(tt);
		taskService.saveTask(task, 2, 2011);
		return "{ \"success\" : true }";

	}

	@RequestMapping(value = "/deletetask", method = RequestMethod.POST)
	public @ResponseBody
	String deletetask(@RequestParam("taskId") int id) {

		taskService.deleteTaskById(id);
		
		return "{ \"success\" : true }";
	}

}

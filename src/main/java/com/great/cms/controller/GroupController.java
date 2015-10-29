package com.great.cms.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.great.cms.db.entity.Groups;
import com.great.cms.db.entity.Project;
import com.great.cms.db.entity.Student;
import com.great.cms.db.entity.StudentGroup;
import com.great.cms.db.entity.Task;
import com.great.cms.service.TaskGroupService;

@Controller
public class GroupController {
	
	@Autowired
	private TaskGroupService taskgroupservice;
	private JSONArray jsonArray;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET,value="/ajaxgroups")
	public @ResponseBody String getGroupList(Model model,@RequestParam("task_id") int taskId)
	{
		System.out.println("Group Controller -> getGroupList");
		System.out.println("Task Id: "+taskId);
		List<Groups> groupList = null;
		
		groupList = taskgroupservice.findGroupsByTaskID(2); 
		
		System.out.println("Group Controller -> groupList " + groupList);
		
		//model.addAttribute("submissions",submissionList);
		
		jsonArray = new JSONArray();
		
		if(groupList==null)
		 System.out.println(" Group Controller -> getGroupList : LIST IS NULL");
		if(groupList.size() == 0)
			 System.out.println("Group Controller -> getGroupList : LIST IS EMPTY");
	
	    
	    for(Groups grp: groupList)
	    {
	    	JSONArray jsonObj = new JSONArray();
	    	jsonObj.add(grp.getGroupId().toString());
	    	jsonObj.add(grp.getGroupName());
	    	jsonObj.add(grp.getProjectGroupList().toString());
	    	jsonObj.add(grp.getStudentGroupList().toString());
	    		    	
	    	jsonArray.add(jsonObj);  
	}
	    
	    JSONObject parameters = new JSONObject();

    	parameters.put("draw", 1);

    	parameters.put("recordsTotal", 1 );
    	
    	parameters.put("recordsFiltered", 1 );
    	
    	parameters.put("data", jsonArray);
    	
    	String groupJson = parameters.toJSONString();
    	
    	//System.out.print("DLSJDHSLKJDH:  "+taskJson);
		return groupJson;
	
}
	
	@RequestMapping(value="/addgroup",method=RequestMethod.POST)
    public @ResponseBody String addGroup(String groupName,Task taskId ,List<Student> studentList )
    {
		System.out.println("Group Controller -> addgroup");
		
		taskgroupservice.addNewGroupOfTask(taskId, groupName, studentList);
		
		return "{ \"success\" : true }";
    }
	
	@RequestMapping(value="/editgroup",method=RequestMethod.POST)
    public @ResponseBody String editGroup(int groupId ,List<Student> studentList )
    {
		System.out.println("Group Controller -> editgroup");
		
		taskgroupservice.editGroupofTask(groupId, studentList);
		
		return "{ \"success\" : true }";
    }
	
	@RequestMapping(value="/deletegroup",method=RequestMethod.POST)
    public @ResponseBody String editGroup(int groupId  )
    {
		System.out.println("Group Controller -> deletegroup");
		
		taskgroupservice.deleteGroupTask(groupId);;
		
		return "{ \"success\" : true }";
    }
	

}


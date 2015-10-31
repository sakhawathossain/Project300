package com.great.cms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.cms.db.dao.CourseDao;
import com.great.cms.db.dao.CourseTaskDao;
import com.great.cms.db.dao.ExamCommitteeDao;
import com.great.cms.db.dao.TaskDao;
import com.great.cms.db.entity.CourseTask;
import com.great.cms.db.entity.Task;
import com.great.cms.service.TaskService;

@Service("TaskService")
public class TaskServiceImpl implements TaskService,Serializable {

	@Autowired
	TaskDao taskDao;
	@Autowired
	CourseTaskDao courseTaskDao;
	@Autowired
	ExamCommitteeDao examCommitteeDao;
	@Autowired
	CourseDao courseDao;
	

	@Override
	public List<Task> getTaskList() {
		// TODO Auto-generated method stub
		List<Task> taskList = null;
		taskList = this.taskDao.findAll();
		return taskList;
	}

	@Override	
	public void saveTask(Task task,int courseId,int session) {
		System.out.println("\nTaskServiceImpl.java: tryina add a new task homie\n");
		
		
//		CourseTask courseTask = new CourseTask();
//		courseTask.setTaskId(task);
//		courseTask.setCourseId(this.courseDao.findById(courseId));
//		courseTask.setExamCommitteeId(this.examCommitteeDao
//				.findBySession(session));
//		task.setCourseTask(courseTask);
		this.taskDao.save(task);
		CourseTask courseTask = new CourseTask();
		courseTask.setTaskId(task);
		courseTask.setCourseId(this.courseDao.findById(courseId));
		courseTask.setExamCommitteeId(this.examCommitteeDao
				.findBySession(session));
		
		this.courseTaskDao.save(courseTask);
	}

	@Override
	public void updateTask(Task task) {
		
		System.out.println("TaskServiceImpl.java: tryina edit this task with id "+task.getTaskId());
		this.taskDao.update(task);
				
	}

	@Override
	public void deleteTask(Task task) {
		// TODO Auto-generated method stub
		this.taskDao.delete(task);
	
	}

	@Override
	public Task findTaskById(int id) {
		// TODO Auto-generated method stub
		return this.taskDao.findById(id);
	}

	@Override
	public void deleteTaskById(int id) {
		// TODO Auto-generated method stub
		
		this.taskDao.deleteById(id);
		
	}

	@Override
	public List<Task> getTaskListByCourseId(int courseId) {
		// TODO Auto-generated method stub
		return this.taskDao.getTaskListByCourseId(courseId);
	}

	
	
	
}

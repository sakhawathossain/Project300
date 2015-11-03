package com.great.cms.service;

import java.util.List;

import com.great.cms.db.entity.Task;

public interface TaskService {
	
	List<Task>getTaskList();
	
	void saveTask(Task task,int courseId,int session);
	
	void updateTask(Task task);
	
	void deleteTask(Task task);
	
	public Task findTaskById(int id);
	
	public void deleteTaskById(int id);
	
    public List<Task> getTaskListByCourseId(int courseId);	
	

}

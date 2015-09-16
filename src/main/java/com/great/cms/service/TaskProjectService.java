package com.great.cms.service;

import java.util.List;

import com.great.cms.db.entity.TaskProject;
import com.great.cms.db.entity.Project;
import com.great.cms.db.entity.Task;

public interface TaskProjectService {
	public List<Project> findProjectsByTaskID(int taskId);
//	public void addProjectOfTask(Project project, int taskId);
	
		
}


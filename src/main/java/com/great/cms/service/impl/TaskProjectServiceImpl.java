package com.great.cms.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.cms.db.dao.ProjectDao;
import com.great.cms.db.dao.TaskDao;
import com.great.cms.db.dao.TaskProjectDao;
import com.great.cms.db.entity.Project;
import com.great.cms.db.entity.Task;
import com.great.cms.db.entity.TaskProject;
import com.great.cms.service.TaskProjectService;

@Service("TaskProjectService")
public class TaskProjectServiceImpl implements TaskProjectService,Serializable{

	@Autowired
	private TaskProjectDao taskProjectDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private TaskDao taskDao;
	
	
	@Override
	public List<Project> findProjectsByTaskID(int taskId){
		
		System.out.println("findProjectsByTaskIDCalled, id = " + taskId);
		List <TaskProject> list = null;
		ArrayList<Project> projectList = new ArrayList<Project>();
		
		Project project;
		try{
			list = taskProjectDao.getProjectsByTaskID(taskId);
			for(TaskProject tp : list){
				 projectList.add(tp.getProjectId());
				System.out.println("Project id = " + tp.getProjectId().getProjectId()
						+ " Project title = " + tp.getProjectId().getProjectTitle());
				
			}
			
		}catch(Exception e){
			System.out.println("TaskProjectService failed error = " + e);
			return null;
		}
		return projectList;
	}


	@Override
	public void addProjectOfTask(Project project, int taskId) {
		
		projectDao.save(project);
		TaskProject taskProject = new TaskProject();
		taskProject.setProjectId(project);
		taskProject.setTaskId(taskDao.findById(taskId));
		taskProjectDao.save(taskProject);
	}


	@Override
	public void updateProject(Project project) {
		projectDao.update(project);
		
	}


	@Override
	public void deleteProjectOfTask(int projectId) {
		projectDao.delete(projectDao.findById(projectId));
		
	}

}

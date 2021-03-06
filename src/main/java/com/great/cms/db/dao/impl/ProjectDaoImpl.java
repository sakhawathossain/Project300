package com.great.cms.db.dao.impl;

import org.springframework.stereotype.Repository;

import com.great.cms.db.dao.ProjectDao;
import com.great.cms.db.dao.TaskDao;
import com.great.cms.db.entity.Project;
import com.great.cms.db.entity.Task;

@Repository("ProjectDao")
public class ProjectDaoImpl extends GenericDaoImpl<Project, Integer> implements ProjectDao  {
	
	public ProjectDaoImpl() {
		super(Project.class);
	
	}

}

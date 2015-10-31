package com.great.cms.db.dao.impl;

import org.springframework.stereotype.Repository;

import com.great.cms.db.dao.ProjectGroupDao;
import com.great.cms.db.dao.ProjectGroupSubmitDao;
import com.great.cms.db.entity.ProjectGroup;


@Repository("ProjectGroupDao")
public class ProjectGroupDaoImpl extends GenericDaoImpl<ProjectGroup, Integer> implements ProjectGroupDao{

	public ProjectGroupDaoImpl() {
		super(ProjectGroup.class);
	}

}

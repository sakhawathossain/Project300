package com.great.cms.db.dao;

import java.util.List;

import com.great.cms.db.entity.StudentGroup;
import com.great.cms.db.entity.TaskProject;

public interface StudentGroupDao extends GenericDao<StudentGroup, Integer> {
  

	//public List<StudentGroup> findGroupsByTaskID(int taskId);
	public List<StudentGroup> findStudentByGroupId(int groupId);


	//void findGroupsByTaskID(int i);

	//void findGroupsByTaskID(int i);

	
}

package com.great.cms.db.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.great.cms.db.dao.StudentGroupDao;
import com.great.cms.db.entity.StudentGroup;


@Repository("StudentGroupDao")
public class StudentGroupDaoImpl extends GenericDaoImpl<StudentGroup, Integer> implements StudentGroupDao {

	public StudentGroupDaoImpl() {
		super(StudentGroup.class);
	}

	@Override
	public List<StudentGroup> findGroupsByTaskID(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

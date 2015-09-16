package com.great.cms.db.dao.impl;

import org.springframework.stereotype.Repository;

import com.great.cms.db.dao.StudentDao;
import com.great.cms.db.entity.Student;

@Repository("StudentDao")
public class StudentDaoImpl extends GenericDaoImpl<Student, Integer> implements StudentDao {

	public StudentDaoImpl() {
		super(Student.class);
	}

}

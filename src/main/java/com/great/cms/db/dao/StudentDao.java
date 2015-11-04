package com.great.cms.db.dao;

import java.util.List;

import com.great.cms.db.entity.Student;

public interface StudentDao extends GenericDao<Student, Integer> {
	public Student getStudentByRegNo(int registrationNo);
}

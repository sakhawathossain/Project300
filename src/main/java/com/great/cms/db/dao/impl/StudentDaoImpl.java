package com.great.cms.db.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.great.cms.db.dao.StudentDao;
import com.great.cms.db.entity.CourseRegistration;
import com.great.cms.db.entity.Student;
import com.great.cms.db.entity.Task;

@Repository("StudentDao")
public class StudentDaoImpl extends GenericDaoImpl<Student, Integer> implements StudentDao {

	public StudentDaoImpl() {
		super(Student.class);
	}

	@Override
	public Student getStudentByRegNo(int registrationNo) {
		Student student;
		String query = "select o from " + type.getName()
				+ " o where o.registrationNo = ?1";
		try {
			student = (Student) em.createQuery(query)
					.setParameter(1, registrationNo)
					.getResultList().get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;		
		}
		return student;
	}
}

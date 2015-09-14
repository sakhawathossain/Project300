package com.great.cms.db.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.great.cms.db.dao.StudentDao;
import com.great.cms.db.entity.Student;

@Repository("StudentDao")
public class StudentDaoImpl extends GenericDaoImpl<Student, Integer>  implements StudentDao {

	public StudentDaoImpl() {
		super(Student.class);
		
	}
    
	



	


}
package com.great.cms.db.dao.impl;

import org.springframework.stereotype.Repository;

import com.great.cms.db.dao.CourseDao;
import com.great.cms.db.entity.Course;

@Repository("CourseDao")
public class CourseDaoImpl extends GenericDaoImpl<Course, Integer> implements CourseDao {

	public CourseDaoImpl() {
		super(Course.class);
	}

}

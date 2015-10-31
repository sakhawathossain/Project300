package com.great.cms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.great.cms.db.dao.CourseDao;
import com.great.cms.db.entity.Course;
import com.great.cms.service.CourseService;

@Service("CourseService")
public class CourseServiceImpl implements CourseService, Serializable{

	@Autowired
	CourseDao courseDao;
	
	@Override
	public List<Course> getCourseList() {
		List<Course> courseList = null;
		courseList = this.courseDao.findAll();
		return courseList;
	}

	@Override
	public List<Course> getCourseListByUserId(int id) {
		// TODO implement this method for god's sake.
		return null;
	}

	@Override
	public String getCourseById(int id) {
		return this.courseDao.findById(id).getCourseCode();
	}

}

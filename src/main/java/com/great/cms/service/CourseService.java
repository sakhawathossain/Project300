package com.great.cms.service;

import java.util.List;

import com.great.cms.db.entity.Course;

public interface CourseService {
	List<Course> getCourseList();
	List<Course> getCourseListByUserId(Long id);
	String getCourseById(int id);
	List<Course> getCourseListByUser(String username);
}

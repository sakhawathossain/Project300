package com.great.cms.service;

import java.util.List;

import com.great.cms.db.entity.Course;

public interface TeacherCourseService {

	
	
	public List<Course> getCourseListByUserId(Long userId);
	
	public List<Course> getCourseListByInstructionId(Long InstId);
}

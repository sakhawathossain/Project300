package com.great.cms.service;

import java.util.List;

import com.great.cms.db.entity.Course;

public interface TeacherCourseService {

	
	
	public String getCourseListByUserId(Long userId);
	
	public String getCourseListByInstructionId(Long InstId);
}

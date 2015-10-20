package com.great.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.great.cms.db.dao.CourseDao;
import com.great.cms.db.entity.Course;
import com.great.cms.db.entity.CourseRegistration;
import com.great.cms.repository.CourseRepository;
import com.great.cms.service.CourseRegistrationService;
import com.great.cms.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseRegistrationService courseRegistrationService;

	@RequestMapping("/course")
	public String getCourse(Model model) {
		System.out.println("Course Controller");
		List<Course> courses = courseService.getCourseList();//courseRepository.findAll();		
		model.addAttribute("courses", courses);
	
		List<CourseRegistration> courseRegList = courseRegistrationService.getStudentRegistration(2);
		//System.out.println("####"+);
		return "course";
	}

}

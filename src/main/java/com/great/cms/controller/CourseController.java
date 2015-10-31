package com.great.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	JSONArray jsonArray;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/ajaxcourse")
	public @ResponseBody
		String getCourse(Model model,HttpServletRequest request) {
		System.out.println("Response Object:"+request.getParameter("userId").toString());		
		System.out.println("Course Controller");
		List<Course> courses = courseService.getCourseList();//courseRepository.findAll();		
		model.addAttribute("courses", courses);
		
		jsonArray = new JSONArray();
		if (courses == null)
			System.out.println("CourseController : LIST IS NULL");
	
		//List<CourseRegistration> courseRegList = courseRegistrationService.getStudentRegistration(2);
		//System.out.println("####"+);
		for(Course c: courses){
			JSONArray jObj = new JSONArray();
			jObj.add(c.getCourseId());
			jObj.add(c.getCourseCode());
			jObj.add(c.getCourseTitle());
			jObj.add(c.getCredit());
			jsonArray.add(jObj);
		}
		JSONObject parameters = new JSONObject();

		parameters.put("draw", 1);

		parameters.put("recordsTotal", 1);

		parameters.put("recordsFiltered", 1);

		parameters.put("data", jsonArray);

		String taskJson = parameters.toJSONString();

		// System.out.print("DLSJDHSLKJDH:  "+taskJson);
		return taskJson;
	}

}

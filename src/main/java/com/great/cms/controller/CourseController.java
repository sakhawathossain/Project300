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
import com.great.cms.db.entity.User;
import com.great.cms.repository.CourseRepository;
import com.great.cms.service.CourseRegistrationService;
import com.great.cms.service.CourseService;
import com.great.cms.service.UserService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private CourseRegistrationService courseRegistrationService;

	JSONArray jsonArray;
	
	

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/ajaxcourse")
	public @ResponseBody
	String getCourse(@RequestParam("username") String username) {

		
		User user = userService.getUserByName(username);
		// TODO: use username to return filtered course list
		List<Course> courses = courseService.getCourseListByUserId(user.getUserId());
		

		jsonArray = new JSONArray();
		if (courses == null)
			System.out.println("CourseController : LIST IS NULL");

		for (Course c : courses) {
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

		return taskJson;
	}
}

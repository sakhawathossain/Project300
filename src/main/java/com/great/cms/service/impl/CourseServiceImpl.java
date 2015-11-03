package com.great.cms.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.great.cms.db.dao.CourseDao;
import com.great.cms.db.dao.TeacherDao;
import com.great.cms.db.dao.TeachesDao;
import com.great.cms.db.dao.UserDao;
import com.great.cms.db.entity.Course;
import com.great.cms.db.entity.Teacher;
import com.great.cms.db.entity.Teaches;
import com.great.cms.db.entity.User;
import com.great.cms.service.CourseService;

@Service("CourseService")
public class CourseServiceImpl implements CourseService, Serializable{

	@Autowired
	CourseDao courseDao;
	
	@Autowired
	private TeacherDao teacherDao;
	
	@Autowired
	private TeachesDao teachesDao;
	
	@Autowired
	UserDao userDao;
	
	List<Course> list = new ArrayList<>();
	List<Teaches> teaches = new ArrayList<>();
	Teacher teacher = new Teacher();
	Long InstructorId = null;
	
	User user=null;
	
	@Override
	public List<Course> getCourseList() {
		List<Course> courseList = null;
		courseList = this.courseDao.findAll();
		return courseList;
	}

	@Override
	public List<Course> getCourseListByUserId(Long id) {
		// TODO implement this method for god's sake.
		list = new ArrayList<>();
	
		teacher = (Teacher) teacherDao.findByUserId(id);
		if(teacher == null)	
			System.out.println("Null teacher");
			
		
	    InstructorId = teacher.getInstructorId();
		
		teaches = teachesDao.findByInstructorId(InstructorId);

		if (teaches != null && teaches.size() > 0) {
			for (Teaches teach : teaches) {

				Course course = teach.getCourseId();

				System.out.println("##" + course.getCourseTitle());

				list.add(course);

			}
		}
		return list;
	}

	@Override
	public String getCourseById(int id) {
		return this.courseDao.findById(id).getCourseCode();
	}

	@Override
	public List<Course> getCourseListByUser(String username) {
		// TODO Auto-generated method stub
		
		user = userDao.findUserByName(username);
		list = this.getCourseListByUserId(user.getUserId());
		
		System.out.println("The Course List in Course Service Layer: "+list);
		return list;
	}

}

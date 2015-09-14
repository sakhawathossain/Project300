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
import com.great.cms.db.entity.Course;
import com.great.cms.db.entity.Teacher;
import com.great.cms.db.entity.Teaches;
import com.great.cms.service.TeacherCourseService;


//Tested
@Repository
@Service("TeacherCourseService")
public class TeacherCourseServiceImpl implements TeacherCourseService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private TeachesDao teachesDao;
	@Autowired
	private CourseDao courseDao;

	List<Course> list = new ArrayList<>();
	List<Teaches> teaches = new ArrayList<>();
	List<Teacher> teacher = new ArrayList<>();
	Long InstructorId = null;

	@Override
	public List<Course> getCourseListByUserId(Long userId) {

		teacher = teacherDao.findByUserId(userId);
		for (Teacher teachers : teacher) {

			InstructorId = teachers.getInstructorId();
		}
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
	public List<Course> getCourseListByInstructionId(Long InstId) {
		List<Teaches> teach = new ArrayList<>();

		List<Course> course = new ArrayList<>();

		teach = teachesDao.findByInstructorId(InstId);
		for (Teaches teaches : teach) {
			System.out.println(teaches.getCourseId().getCourseCode());
			course.add(teaches.getCourseId());

		}
		return course;
	}

}

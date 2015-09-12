package com.great.cms.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.great.cms.db.dao.CourseRegistrationDao;
import com.great.cms.db.entity.CourseRegistration;
import com.great.cms.service.CourseRegistrationService;
import com.great.cms.utils.CrmConstants;


@Repository
@Service("CourseRegistrationService")
public class CourseRegistrationServiceImpl implements CourseRegistrationService, Serializable {
//	private Logger logger = LoggerFactory.getLogger(this.getClass().getPackage().getName());
	
	@Autowired
	private CourseRegistrationDao courseRegistrationDao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7468488836689400267L;

	@Override
	public List<CourseRegistration> getStudentRegistration(int id) {
		// TODO Auto-generated method stub
		List<CourseRegistration> courseRegList = courseRegistrationDao.getRegistrationByIdStudent(id);
		//System.out.println("$"+courseReg.getIdStudent().getRegistrationNo());
		if(courseRegList != null && courseRegList.size()>0){
			for(CourseRegistration courseReg : courseRegList){
				//System.out.println("##"+courseReg.getIdStudent().getName());
		
		}
		}
//		logger.info("Id::::"+courseReg.getIdStudent().getRegistrationNo());
		return courseRegList;
	
	}
	
	
}

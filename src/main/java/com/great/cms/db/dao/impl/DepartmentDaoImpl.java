package com.great.cms.db.dao.impl;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.great.cms.db.dao.DepartmentDao;
import com.great.cms.db.entity.CourseRegistration;
import com.great.cms.db.entity.Department;

public class DepartmentDaoImpl extends GenericDaoImpl<Department, Integer> implements DepartmentDao {

	public DepartmentDaoImpl() {
		super(Department.class);
	}
	
	
	@Override
	public List<Department> findByDeptCode(String deptCode) {
		List<Department> list = null;
			try{
				String query = "select o from " + type.getName() + " o where " +
     				   "o.deptCode = ?1 " +
     				   "order by o.deptCode ";
     	list = em.createQuery(query)
     			 .setParameter(1, deptCode)
     			 .getResultList();
     	
     	
     		}
			catch(Exception e){
				System.out.println("Exception in findByDeptCode,dept code = "+deptCode + " error trace : ");
				e.printStackTrace();
				return null;
	        }
			    System.out.println("findByDeptCode was successful");
			    return list;
	}

	
}

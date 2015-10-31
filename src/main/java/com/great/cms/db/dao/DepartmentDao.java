package com.great.cms.db.dao;

import java.util.List;

import com.great.cms.db.entity.Department;
import com.great.cms.db.entity.User;

/**
 * @author ziniapc
 *
 */
public interface DepartmentDao extends GenericDao<Department, Integer> {
	
	
	public List<Department> findByDeptCode(String deptCode);
	
    
	
	

}

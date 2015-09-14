package com.great.cms.db.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.great.cms.db.dao.GroupsDao;
import com.great.cms.db.entity.Groups;
@Repository("GroupsDao")

public class GroupsDaoImpl extends GenericDaoImpl<Groups, Integer> implements GroupsDao {

	public GroupsDaoImpl() {
		super(Groups.class);
		
	}

	@Override
	public Groups findByName(String Name) throws RuntimeException {
		Groups list = null;
		try{
			String query= "select o from " + type.getName() + " o where o.groupName="+Name+" ";
			list=(Groups)em.createQuery(query).getResultList().get(0);
		}catch(Exception e){
			System.out.println("*****Failed*****");
		}
	         System.out.println("**********Success*****");
		return list;
	}

	@Override
	public List<Groups> findByTaskId(Integer taskId) throws RuntimeException {
		List<Groups> list = null;
		try{
			String query= "select o from " + type.getName() + " o where o.taskId.taskId="+taskId+" ";
			list=em.createQuery(query).getResultList();
		}catch(Exception e){
			System.out.println("*****Failed*****");
		}
	         System.out.println("**********Success*****");
		return list;
	}

          

}

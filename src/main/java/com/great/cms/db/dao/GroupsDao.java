package com.great.cms.db.dao;

import java.util.List;

import com.great.cms.db.entity.Groups;

public interface GroupsDao extends GenericDao<Groups, Integer> {
	
	public Groups findByName(String Name)throws RuntimeException;
	
	public List<Groups> findByTaskId(Integer taskId)throws RuntimeException;

}

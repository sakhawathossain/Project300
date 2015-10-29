package com.great.cms.service;

import com.great.cms.db.entity.User;

public interface UserService {

	public User getUserByID(long id);
	public User getUserByName(String userName);
}
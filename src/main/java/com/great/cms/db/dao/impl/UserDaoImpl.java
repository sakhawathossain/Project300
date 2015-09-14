package com.great.cms.db.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.great.cms.db.dao.UserDao;
import com.great.cms.db.entity.User;
import com.great.cms.db.entity.UserType;

//Tested
@Repository("User")
public class UserDaoImpl extends GenericDaoImpl<User, Long>implements UserDao {



	public UserDaoImpl( ) {
		super(User.class);
		
	}

	

	@Override
	public User findUserByName(String Name) {
		User user = null;
		try{
			user = (User) em.createQuery("select o from " + type.getName() + " o where o.userName="+Name+" ").getResultList().get(0);
 	             
 		}
		catch(Exception e){
			System.out.println("*******failure*******");
        }
		    System.out.println("*******successful*******");
		return user;
		
	}



	@Override
	public User findByEmail(String Email) {
		User user = null;
		try{
			user = (User) em.createQuery("select o from " + type.getName() + " o where o.userEmail="+Email).getResultList().get(0);
 	
 		}
		catch(Exception e){
			System.out.println("*******failure*******");
        }
		    System.out.println("*******successful*******");
		return user;
		
	}

}

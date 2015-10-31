package com.great.cms.db.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.great.cms.db.dao.StudentGroupDao;
import com.great.cms.db.entity.StudentGroup;


@Repository("StudentGroupDao")
public class StudentGroupDaoImpl extends GenericDaoImpl<StudentGroup, Integer> implements StudentGroupDao {

	public StudentGroupDaoImpl() {
		super(StudentGroup.class);
	}

	
	@Override
	public List<StudentGroup> findStudentByGroupId(int groupId) {
		
		List<StudentGroup> list = null;
		String query = "select o from " + type.getName()
				+ " o where o.groupId.groupId = ?1";

		try {
			
			list = em.createQuery(query)
					.setParameter(1, groupId)
					.getResultList();
			
		} catch (Exception e) {
			
			System.out.println("Exception in findStudentByGroupId,groupId = "
					+ groupId + " error trace : ");
			e.printStackTrace();
			return null;
			
		}
		
		System.out.println("findStudentByGroupId was successful");

		return list;
	}

}

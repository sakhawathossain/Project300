package com.great.cms.db.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.great.cms.db.dao.ExamCommitteeDao;
import com.great.cms.db.entity.CourseRegistration;
import com.great.cms.db.entity.ExamCommittee;
@Repository("ExamCommitteeDao")
public class ExamCommitteeDaoImpl extends GenericDaoImpl<ExamCommittee, Integer> implements ExamCommitteeDao {

	public ExamCommitteeDaoImpl() {
		super(ExamCommittee.class);
	}

	@Override
	public ExamCommittee findBySession(int session) {
		// TODO Auto-generated method stub
		List<ExamCommittee> list = null;
		//public ConfUser getConfUserByAccctMsisdn(long acctMsisdn){
				try{
				//courseReg = (CourseRegistration) em.createQuery("select o from " + type.getName() + " o where o.idStudent.idStudent ="+id+" ").getResultList();
				String query = "select o from " + type.getName() + " o where " +
     				   "o.session = ?1 ";
     	list = em.createQuery(query)
     			 .setParameter(1, session)
     			 .getResultList();
     	
     	
     		}
			catch(Exception e){
				System.out.println("*******failure*******");
	        }
			System.out.println("*******successful*******");
			return list.get(0);
		
	}

}

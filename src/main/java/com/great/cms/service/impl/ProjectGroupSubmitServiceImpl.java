package com.great.cms.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.cms.db.dao.ProjectGroupDao;
import com.great.cms.db.dao.ProjectGroupSubmitDao;
import com.great.cms.db.dao.SubmissionDao;
import com.great.cms.db.dao.UserDao;
import com.great.cms.db.entity.ProjectGroup;
import com.great.cms.db.entity.ProjectGroupSubmit;
import com.great.cms.db.entity.Submission;
import com.great.cms.service.ProjectGroupSubmitService;

@Service("ProjectGroupSubmitService")
public class ProjectGroupSubmitServiceImpl implements ProjectGroupSubmitService,Serializable{

	@Autowired
	private ProjectGroupSubmitDao projGroupSubDao;
	
	@Autowired
	private SubmissionDao submissionDao;
	@Autowired
	ProjectGroupDao projectGroupDao;

	@Autowired
	UserDao userDao;
	
	
	@Override
	public List<Submission> findSubmissionListByProjectGroupId(int projectGroupId) {
		
		System.out.println("findSubmissionByProjectGroupId Called, id = " + projectGroupId);
		List<ProjectGroupSubmit> list = null;
		ArrayList<Submission> submissionList = new ArrayList<Submission>();
		
		Submission sub;
		try{
			list = projGroupSubDao.getSubmissionByProjectGroup(projectGroupId);
			for(ProjectGroupSubmit pGS : list){
				submissionList.add(pGS.getSubmissionId());
				System.out.println("Submission id = " + pGS.getSubmissionId().getSubmissionId()
						+ " Submission time = " + pGS.getSubmissionId().getSubmissionTime());
				
			}
			
		}catch(Exception e){
			System.out.println("ProjectGroupSubmitService failed error = " + e);
			return null;
		}
		return submissionList;
	}


	
	@Override
	public void addProjectGroupSubmit(Submission submission,int projectGroupID){
		
		System.out.println("add project group submit is called");
		

		Submission s = null;
		ProjectGroupSubmit pgs = new ProjectGroupSubmit(); 
		
		
		submissionDao.save(submission);
		System.out.println("The MAGIC: "+submission.getSubmissionId());
		
		s = submissionDao.findByVersionAndTime(submission.getSubmissionVer(),submission.getSubmissionTime());
		
		if(s == null){
			System.out.println("Submission adding failed!");
			return;
		}
		
		System.out.println("new submission id = " + s.getSubmissionId());
		pgs.setProjectGroupId(projectGroupDao.findById(projectGroupID));
		pgs.setSubmissionId(s);
		
		projGroupSubDao.save(pgs);
		
		
	}

	@Override
	public void updateProjectGroupSubmit(Submission submission,int projectGroupID) {
		
		
	}


}

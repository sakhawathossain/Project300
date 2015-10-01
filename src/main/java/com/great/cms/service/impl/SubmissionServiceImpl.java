package com.great.cms.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.cms.db.dao.SubmissionDao;
import com.great.cms.db.entity.Submission;
import com.great.cms.service.SubmissionService;


@Service("SubmissionService")
public class SubmissionServiceImpl implements SubmissionService,Serializable{

	@Autowired
	SubmissionDao submissionDao;


	@Override
	public void updateSubmission(Submission submission) {
		
		submissionDao.update(submission);
	}

}

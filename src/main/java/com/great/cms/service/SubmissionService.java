package com.great.cms.service;

import org.springframework.web.multipart.MultipartFile;

import com.great.cms.bean.SubmissionBean;
import com.great.cms.db.entity.Submission;

public interface SubmissionService {
	
	public void updateSubmission(Submission submission);
	
	public void saveSubmission(SubmissionBean submissionBean, MultipartFile multipartFile);
	
	public void deleteSubmission(int submissionId);
	

}

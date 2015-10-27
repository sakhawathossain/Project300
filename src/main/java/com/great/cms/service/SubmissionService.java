package com.great.cms.service;

import org.springframework.web.multipart.MultipartFile;

import com.great.cms.db.entity.Submission;

public interface SubmissionService {
	
	public void updateSubmission(Submission submission);
	
	public void saveSubmission(Submission submission,int projectGroupID,MultipartFile multipartFile);
	
	public void deleteSubmission(int submissionId);
	

}

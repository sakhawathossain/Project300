package com.great.cms.bean;

import org.springframework.web.multipart.MultipartFile;

public class SubmissionBean {
	private String commentTeacher;
	private String submissionTime;
	private byte[] submissionFile;
	
	public String getCommentTeacher() {
		return commentTeacher;
	}
	public void setCommentTeacher(String commentTeacher) {
		this.commentTeacher = commentTeacher;
	}
	public String getSubmissionTime() {
		return submissionTime;
	}
	public void setSubmissionTime(String submissionTime) {
		this.submissionTime = submissionTime;
	}
	public byte[] getSubmissionFile() {
		return submissionFile;
	}
	public void setSubmissionFile(byte[] submissionFile) {
		this.submissionFile = submissionFile;
	}

}

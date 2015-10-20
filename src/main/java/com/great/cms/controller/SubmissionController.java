package com.great.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.great.cms.service.SubmissionService;

@Controller
public class SubmissionController {

	@Autowired
	private SubmissionService submissionService;
	
	public String getSubmissionList()
	{
		//submissionService.
		return null;
	}
	
}

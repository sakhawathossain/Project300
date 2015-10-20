package com.great.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.great.cms.service.ProjectGroupSubmitService;

@Controller
public class ProjectGroupController {

	@Autowired
	private ProjectGroupSubmitService projGrpSubService;
	
	@RequestMapping(method=RequestMethod.GET,value="/projectgroup")
	public @ResponseBody String getSubmissionByProjectGroupID(Model model)
	{
		projGrpSubService.findSubmissionListByProjectGroupId(1);
		return "project-groups";
	}
	
	
	
	
	
	
	
	
	
}

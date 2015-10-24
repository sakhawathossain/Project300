package com.great.cms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.omg.CORBA.portable.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.great.cms.bean.SubmissionBean;
import com.great.cms.db.entity.Submission;
import com.great.cms.db.entity.Task;
import com.great.cms.service.ProjectGroupSubmitService;
import com.great.cms.service.SubmissionService;

@Controller
// @SessionAttributes("organization")
public class SubmissionController {

	@Autowired
	private SubmissionService submissionService;

	@Autowired
	private ProjectGroupSubmitService projGrpSubService;

	private JSONArray jsonArray;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/ajaxsubmissions")
	public @ResponseBody
	String getSubmissionList(Model model) {
		List<Submission> submissionList = null;

		submissionList = projGrpSubService
				.findSubmissionListByProjectGroupId(2);

		model.addAttribute("submissions", submissionList);

		jsonArray = new JSONArray();

		if (submissionList == null)
			System.out
					.println("Submission Controller -> getSubmissionList : LIST IS NULL");

		for (Submission s : submissionList) {
			JSONArray jsonObj = new JSONArray();
			jsonObj.add(s.getSubmissionId().toString());
			jsonObj.add(s.getSubmissionTime());
			jsonObj.add(s.getCommentTeacher());
			jsonObj.add(s.getSubmissionUrl());
			/*
			 * if( s.getTaskTypeId().getTaskTypeId()==1) jsonObj.add("Project");
			 * else jsonObj.add("Assignment");
			 */
		
			jsonArray.add(jsonObj);
		}

		JSONObject parameters = new JSONObject();

		parameters.put("draw", 1);

		parameters.put("recordsTotal", 1);

		parameters.put("recordsFiltered", 1);

		parameters.put("data", jsonArray);

		String submissionJson = parameters.toJSONString();

		// System.out.print("DLSJDHSLKJDH:  "+taskJson);
		return submissionJson;

	}

//	// Adding Submissions
//	@RequestMapping(value = "/addsubmission",method = RequestMethod.POST)
//	public @ResponseBody
//	String addSubmission(SubmissionBean submission, BindingResult result,
//			@RequestPart("submissionFileTest")Object file) {
//		    
//		       //MultipartFile file = request.getFile("submissionFile");
//			         System.out
//				.println("\nSubmissionController: addSubmission method.--> \n"
//						+ submission.getCommentTeacher() + " \n"
//						+ submission.getSubmissionTime());
//		        if (file!=null ) {
//		        	
//		        	System.out.println("Succeeded to upload file: "
//		        	+submission.getSubmissionFile());
//		            
//		        } else {
//		        	System.out.println("You failed to upload because the file was empty.") ;
//		        }
//		 return "{ \"success\" : true }";
//		
//	}

	// Adding Submissions
	@RequestMapping(value = "/editsubmission", method = RequestMethod.POST)
	public @ResponseBody
	String editSubmission(Submission submission, BindingResult result) {

		// submissionService.updateSubmission(submission);
		return "{ \"success\" : true }";
	}
	
	@RequestMapping(value = "/addsubmission", method = RequestMethod.POST)
	public @ResponseBody String doUpload(Submission submission, 
			@RequestParam("file") MultipartFile multipartFile
			) throws FileNotFoundException { 
		    System.out.println("We're in a new method.\nfilename: "
			+multipartFile.getOriginalFilename()
			+"\nComment: "+submission.getCommentTeacher());
		    
		    //Uploading file to a specific folder//
		    
//		    InputStream inputStream = null;
//		    FileOutputStream outputStream =null;
//		    
//		    if(multipartFile.getSize()>0){
//		    	try {
//		    		inputStream = multipartFile.getInputStream();
//					outputStream = new FileOutputStream("G:\\Work\\Upload Repo\\"+multipartFile.getOriginalFilename());
//					int readBytes = 0;
//					byte[] buffer = new byte[8192];
//					while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
//					System.out.println("===ddd=======");
//					outputStream.write(buffer, 0, readBytes);
//					}
//					outputStream.close();
//					inputStream.close();
//					
//		    	
//		    	
//		    	
//		    	} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    	
//		    }
		    
		    //Saving the Submission Entity//
		   // submissionService.saveSubmission(submission);
		   
		projGrpSubService.addProjectGroupSubmit(submission, 2, multipartFile);
	    return "Uploaded: " + multipartFile.getSize() + " bytes";
	}
	
	@RequestMapping(value = "/downloadfile", method = RequestMethod.GET)
	public @ResponseBody
	String provideDownloadable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("Download file path: " + request.getParameter("filename"));
		
		
		// submissionService.updateSubmission(submission);
		

		//Downloading the File
		
		try {
			File file = new File("G:/Work/Upload Repo/"+request.getParameter("filename")+".zip");
			FileInputStream inputStream = new FileInputStream(file);
			
		    //MIME type of the file
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\""+file.getName()+"\"");
			
			//Read from the file and write into the response
			ServletOutputStream os = response.getOutputStream();
			
			byte[] buffer = new byte[1024];
			int len;
			while((len=inputStream.read(buffer))!= -1){
				os.write(buffer,0,len);
			}
			
			os.flush();
			os.close();
			inputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
             		
		
		
		
		return "{ \"success\" : true }";
	}
	

	
	

}

package com.great.cms.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.great.cms.db.dao.ProjectGroupDao;
import com.great.cms.db.dao.ProjectGroupSubmitDao;
import com.great.cms.db.dao.SubmissionDao;
import com.great.cms.db.entity.ProjectGroupSubmit;
import com.great.cms.db.entity.Submission;
import com.great.cms.service.SubmissionService;


@Service("SubmissionService")
public class SubmissionServiceImpl implements SubmissionService,Serializable{

	String uploadDirectory; 
	String fileToDelete;
	
	File f = null;
    boolean bool = false;
	
	@Autowired
	private ProjectGroupSubmitDao projGroupSubDao;
	
	@Autowired
	private SubmissionDao submissionDao;
	
	@Autowired
	ProjectGroupDao projectGroupDao;


	@Override
	public void updateSubmission(Submission submission) {
		
		submissionDao.update(submission);
	}
	
//	@Override
//	public void saveSubmission(Submission submission) {
//		
//		submissionDao.save(submission);
//	}

	@Override
	public void deleteSubmission(int submissionId) {
		
		if(submissionDao.findById(submissionId).getSubmissionUrl()!=null){
			
			
			System.out.println("We have file to delete");
			//Delete the file from the directory
			fileToDelete = "G:\\Work\\Upload Repo\\" + submissionDao.findById(submissionId).getSubmissionUrl() + ".zip";
            f = new File(fileToDelete);
            bool = f.delete();
            
            if(bool){
            	System.out.println("File Deleted");
            }
          
						
		}
		
		   		
		   //Delete the object anyway
			this.submissionDao.deleteById(submissionId);
			

		
		
		
		
	}

	@Override
	public void saveSubmission(Submission submission, int projectGroupID,
			MultipartFile multipartFile) {
System.out.println("add project group submit is called");
		

		//Submission s = null;
		ProjectGroupSubmit pgs = new ProjectGroupSubmit(); 
		
		InputStream inputStream = null;
	    FileOutputStream outputStream =null;
	    
	    String newFileName = fileNameFromDate(submission.getSubmissionTime(), projectGroupID);
	    
	    System.out.println("Proposed new file name: "+newFileName);
	    
	    uploadDirectory = "G:\\Work\\Upload Repo\\"+ newFileName + ".zip";
	    
	    if(multipartFile.getSize()>0){
	    	try {
	    		inputStream = multipartFile.getInputStream();
				outputStream = new FileOutputStream(uploadDirectory);
				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
				System.out.println("===ddd=======");
				outputStream.write(buffer, 0, readBytes);
				
				}
				outputStream.close();
				inputStream.close();
				
				submission.setSubmissionUrl(newFileName);
	    	
	    	
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }   
	    
	    else{
	    	submission.setSubmissionUrl("");
	    }
	    
		
		
		submissionDao.save(submission);
		

//		s = submissionDao.findByVersionAndTime(submission.getSubmissionVer(),submission.getSubmissionTime());
//		
//		if(s == null){
//			System.out.println("Submission adding failed!");
//			return;
//		}
//		
//		System.out.println("new submission id = " + s.getSubmissionId());
		pgs.setProjectGroupId(projectGroupDao.findById(projectGroupID));
		pgs.setSubmissionId(submission);
		
		projGroupSubDao.save(pgs);
		
	}
	
	public String fileNameFromDate(String submissionTime, int projectGroupId){
		char[] tempFileName = submissionTime.toCharArray();
		Random random = new Random();
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i<7; i++)
	    	sb.append( (char) (random.nextInt((122-65) + 1) + 65) );
	    for(char ch : tempFileName){
	    	if(ch >= '0' && ch <= '9')
	    		sb.append(ch);
	    }
	    sb.append(projectGroupId);
	   return sb.toString();
	}
	

}

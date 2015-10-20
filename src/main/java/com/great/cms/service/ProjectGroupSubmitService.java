package com.great.cms.service;
import java.util.List;

import com.great.cms.db.entity.Submission;
public interface ProjectGroupSubmitService {

		public List<Submission> findSubmissionListByProjectGroupId(int projectGroupId);
		public void addProjectGroupSubmit(Submission submission,int projectGroupID);
		public void updateProjectGroupSubmit(Submission submission, int projectGroupID);

}

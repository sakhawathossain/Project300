package com.great.cms.service;

import java.util.List;

import com.great.cms.bean.GroupBean;
import com.great.cms.db.entity.Student;

public interface ProjectGroupService {
	

	public List<GroupBean> findGroupsByProjectId(int projectId);
	public void addGroupOfProject(int projectId, String groupName, List<Student>studentList,int taskId);
	public void editGroupOfProject(int groupId, List<Student> studentList);
	public void deleteGroupOfProject(int groupId);
}
package com.great.cms.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.hql.classic.GroupByParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.cms.bean.GroupBean;
import com.great.cms.db.dao.GroupsDao;
import com.great.cms.db.dao.ProjectDao;
import com.great.cms.db.dao.ProjectGroupDao;
import com.great.cms.db.dao.StudentDao;
import com.great.cms.db.dao.StudentGroupDao;
import com.great.cms.db.dao.TaskDao;
import com.great.cms.db.entity.Groups;
import com.great.cms.db.entity.ProjectGroup;
import com.great.cms.db.entity.Student;
import com.great.cms.db.entity.StudentGroup;
import com.great.cms.service.ProjectGroupService;

@Service("ProjectGroupService")
public class ProjectGroupServiceImpl implements ProjectGroupService,Serializable{

	@Autowired
	private ProjectGroupDao projectGroupDao;
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private GroupsDao groupsDao;
	@Autowired
	private StudentGroupDao studentGroupDao;
	@Autowired
	private ProjectDao projectDao;
	
	public List<GroupBean> findGroupsByProjectId(int projectId) {
		
		
		List<ProjectGroup> projectGroupList = projectGroupDao.findAll();
		List<GroupBean> groupList = new ArrayList();
		for(ProjectGroup pg : projectGroupList)
		{
			// this if statement filters the groups
			if(pg.getProjectId().getProjectId() == projectId){
				GroupBean gb = new GroupBean();
				gb.setGroupId(pg.getGroupId().getGroupId());
				gb.setGroupName(pg.getGroupId().getGroupName());
				
					List<String> memberList = new ArrayList<>();
					List <StudentGroup> members = studentGroupDao.findStudentByGroupId(gb.getGroupId());
					for(StudentGroup sg : members)
						memberList.add("" + sg.getStudentId().getRegistrationNo() );
					
				gb.setMemberList((ArrayList<String>) memberList);
				groupList.add(gb);
			}
		}
		return groupList;
	}

	@Override
	public void addGroupOfProject(int projectId, String groupName,
			List<Student> studentList,int taskId) {
		Groups group;
		group = new Groups();
		group.setGroupName(groupName);
		group.setTaskId(taskDao.findById(taskId));
		groupsDao.save(group);
		for(Student s: studentList)
		{
			StudentGroup studentGroup = new StudentGroup();
			studentGroup.setGroupId(group);
			studentGroup.setStudentId(s);
			studentGroupDao.save(studentGroup);
			
			
		}
		ProjectGroup projGrp = new ProjectGroup();
		projGrp.setGroupId(group);
		projGrp.setProjectId(projectDao.findById(projectId));
		
		projectGroupDao.save(projGrp);
		
	}

	@Override
	public void editGroupOfProject(int groupId, List<Student> studentList) {
		
		Groups group = groupsDao.findById(groupId);
		
		List<StudentGroup> studgroupList= studentGroupDao.findStudentByGroupId(groupId);
		
		for(StudentGroup sg:studgroupList )
		{
			studentGroupDao.delete(sg);
		}
		for(Student s: studentList)
		{
			StudentGroup studentGroup =new StudentGroup();
			studentGroup.setGroupId(group);
			studentGroup.setStudentId(s);
			studentGroupDao.save(studentGroup);
			
			
		}
		
	}

	@Override
	public void deleteGroupOfProject(int groupId) {
		groupsDao.deleteById(groupId);
		
	}


}
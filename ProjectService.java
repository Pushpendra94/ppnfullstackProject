package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

//@ComponentScan("com.example.demo")
@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BacklogRepository backlogRepository;
	@Autowired
	private UserRepository userRepository;
	public Project saveOrupdate(Project project,String userName)
	{
		if(project.getId()!=null) {
			Project existingProject=projectRepository.findByProjectIdentifir(project.getProjectIdentifir());
			if(existingProject!=null &&(!existingProject.getProjectLeader().equals(userName)))
			{
				throw new ProjectIdException("Project does not exixts in your account");
			}else if(existingProject==null) {
				throw new ProjectIdException("Project with id:"+project.getProjectIdentifir()+"can not be updated");
			}
		}

		try
		{
			//project.getId!=null;
			//find by db id->null
			User user=userRepository.findByUserName(userName);
			project.setUser(user);
			project.setProjectLeader(user.getUsername());
			project.setProjectIdentifir(project.getProjectIdentifir());
			if(project.getId()==null) {
			Backlog backlog=new Backlog();
			project.setBacklog(backlog);
			backlog.setProject(project);
			backlog.setProjectIdentifir(project.getProjectIdentifir());
			}
			//Backlog backlog=new Backlog();
			if(project.getId()!=null)
			{
				project.setBacklog(backlogRepository.findByProjectIdentifir( project.getProjectIdentifir()));
			}
		return projectRepository.save(project);
	}catch(Exception e)
		{
		throw new ProjectIdException("Project Id "+ project.getProjectIdentifir().toUpperCase()+ "   already exists");
		}
	}
		public Project findProjectByIdentifir (String projectId,String userName)
		{
			Project project = projectRepository.findByProjectIdentifir(projectId);
			if(project==null)
			{
				throw new ProjectIdException("Project does not exists");
			}
			if(!project.getProjectLeader().equals(userName)){
				throw new ProjectIdException("Project does not exists in your account");
				
			}
			return project;
		}
		
	public Iterable<Project> findAllProjects(String userName)
	{
		return projectRepository.findAllByProjectLeader(userName);
	}
	public void DeleteProjectByIdentifir(String projectId,String userName)
	{
		//Project project=projectRepository.findByProjectIdentifir(projectId);
//		if(project==null)
//		{
//			throw new ProjectIdException("can not delete project with id "+projectId+"does not exixts");
//		}
		projectRepository.delete(findProjectByIdentifir ( projectId, userName));
	}

}

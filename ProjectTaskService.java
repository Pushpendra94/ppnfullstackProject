package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProjectTaskService {
	@Autowired
    private BacklogRepository backlogRepository;
	@Autowired
    private ProjecttaskRepository projecttaskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ProjectService projectService;
	
public Projecttask addProjecttask(String projectIdentifir,Projecttask projecttask,String userName){
	
		//Exception:project not found
		//Pts to be added to a specific project!==null(project)
		
		Backlog backlog=projectService.findProjectByIdentifir(projectIdentifir, userName).getBacklog();
				//backlogRepository.findByProjectIdentifir(projectIdentifir);
		//set the bl to pt
		projecttask.setBacklog(backlog);
		// we want our project sequence to be like this  IDPRO-1,IDPRO-2,...100101
		
		Integer BacklogSequence = backlog.getPTSequence();
		
		//update the bl sequnce 
		//BacklogSequence++;
		//Add Sequence to Project Task
		
		
		
		projecttask.setAcceptanceCriteria(projectIdentifir+"-"+BacklogSequence);
		//projecttask.setProjectSequence("backlog-0"+BacklogSequence);
		BacklogSequence=BacklogSequence+1;
		backlog.setPTSequence(BacklogSequence);
		projecttask.setProjectIdentifir(projectIdentifir);
		
		//intial  priority when peroirity is null
		if( projecttask.getPriority()==null) {
			projecttask.setPriority(3);
	}
		//initial status when status is null
		if(projecttask.getStatus()==null) {
			projecttask.setStatus("TO_DO");
		}
		
		  return projecttaskRepository.save(projecttask);

	
}

 public Iterable<Projecttask>findBacklogById(String id,String userName){
	 //Project project=projectRepository.findByProjectIdentifir(id,userName);
	 projectService.findProjectByIdentifir(id, userName);
//	 if(project==null) {
//		 throw new ProjectIdException("project with id= "   +id+" does not exixts");
//	 }
	return projecttaskRepository.findByProjectIdentifirOrderByPriority(id);
	
}
 public Projecttask findPTByAcceptanceCriteria(String backlog_id,String pt_id,String userName)
 {
	 //make sure we are searching on right backlog
//	 Backlog backlog=backlogRepository.findByProjectIdentifir(backlog_id);
//	 if(backlog==null) {
//		 throw new ProjectIdException("Backlog  with id="+backlog_id+"  does not exixts");
//	 }
	 //make sure that our task exits
	 projectService.findProjectByIdentifir(backlog_id, userName);
	 
	 Projecttask projectTask=projecttaskRepository.findByAcceptanceCriteria(pt_id);
	 if(projectTask==null) {
		 throw new ProjectIdException("Project Task="+pt_id+" does not exists");
	 }
	 //make sure that the backlog/project id in the path corresponds to right Project
	 if(!projectTask.getProjectIdentifir().equals(backlog_id))
	 {
		 throw new ProjectIdException("Project Task = "+pt_id+
				 "does not exists in project ="+backlog_id+ "with this backlog id " );
	 }
	 return projectTask ;
 }
//update project Task
//find existing project task
//replace it with updated task
//save update
 public Projecttask updateByAcceptanceCriteria(Projecttask updatedtask,String backlog_id,String pt_id,String userName)
 {
	 Projecttask projectTask=findPTByAcceptanceCriteria(backlog_id,pt_id,userName);
	 projectTask=updatedtask;
	 return projecttaskRepository.save(projectTask);
 }
 
 public void deleteByAcceptanceCriteria(String backlog_id,String pt_id,String userName) {
//	 Projecttask projectTask=projecttaskRepository.findByAcceptanceCriteria(pt_id);
//	 Backlog backlog=backlogRepository.findByProjectIdentifir(backlog_id);
//	 //Backlog backlog=projectTask.getBacklog();
//	 if(backlog==null) {
//		 throw new ProjectIdException("Backlog  with id="+backlog_id+"  does not exixts");
//	 }
//	 //make sure that our task exits
//	 
//	// Projecttask projectTask=projecttaskRepository.findByAcceptanceCriteria(pt_id);
//	 else if(projectTask==null) {
//		 throw new ProjectIdException("Project Task="+pt_id+" does not exits");
//	 }
//	 //make sure that the backlog/project id in the path corresponds to right Project
//	 else if(!projectTask.getProjectIdentifir().equals(backlog_id))
//	 {
//		 throw new ProjectIdException("Project Task = "+pt_id+
//				 "does not exits in project ="+backlog_id+ "with this backlog id " );
//	 }else
//	 {
//	 
//	 
////	 List<Projecttask> pts=backlog.getProjecttask();
////	 pts.remove(projectTask);
////	 backlogRepository.save(backlog);
	 Projecttask projectTask=findPTByAcceptanceCriteria(backlog_id,pt_id,userName);
	 projecttaskRepository.delete(projectTask);
	 }
 
 
}

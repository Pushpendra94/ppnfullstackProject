package com.example.demo;

	import java.security.Principal;
import java.util.List;
	
	import javax.validation.Valid;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
	@Autowired
	private ProjectTaskService projectTaskService;
	@Autowired
	private MapValiationErrorService mapValidationErrorService;
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody Projecttask projecttask,
		BindingResult result,@PathVariable String backlog_id,Principal principal){
		ResponseEntity<?>errorMap=mapValidationErrorService.MapValiationErrorService(result);
		if(errorMap!=null)
			return errorMap;
		Projecttask projecttask1=projectTaskService.addProjecttask(backlog_id, projecttask,principal.getName());
		return new ResponseEntity<Projecttask>(projecttask1,HttpStatus.CREATED);
	}
	@GetMapping("/{backlog_id}")
	public Iterable<Projecttask> getProjectBacklog(@PathVariable String backlog_id,Principal principal)
	{
		return projectTaskService.findBacklogById(backlog_id,principal.getName());
		
	}
	@GetMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id ,@PathVariable String pt_id,Principal principal )
	{
		Projecttask projecttask=projectTaskService.findPTByAcceptanceCriteria(backlog_id, pt_id,principal.getName());
		return new ResponseEntity<Projecttask>(projecttask,HttpStatus.OK);
	}
	
	@PatchMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?>updateProjectTask(@Valid @RequestBody Projecttask  projectTask ,BindingResult result,
			@PathVariable  String backlog_id,@PathVariable String pt_id,Principal principal)
	{
		ResponseEntity<?> errorMap=mapValidationErrorService.MapValiationErrorService(result);
		if(errorMap!=null)
		{
			return errorMap;
		}
		Projecttask updatedTask=projectTaskService.updateByAcceptanceCriteria(projectTask, backlog_id, pt_id,principal.getName());
		return new ResponseEntity<Projecttask>(updatedTask,HttpStatus.OK);
	}
	
	@DeleteMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?>deleteProjectTask(@PathVariable  String backlog_id,@PathVariable String pt_id,Principal principal)
	{
		projectTaskService.deleteByAcceptanceCriteria(backlog_id, pt_id,principal.getName());
		return new ResponseEntity<String>("Project Task ="+pt_id+" was deleted successfully",HttpStatus.OK);
	}
	

}

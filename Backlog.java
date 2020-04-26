package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Backlog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer PTSequence=1;
	private String projectIdentifir;

	
	
	
	// with project
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="project_id",nullable=false)
	//@Autowired
	@JsonIgnore
	private Project project;

	//OneTOMany projectTask
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="backlog",orphanRemoval=true)
	//Cascade REFRESH
	//ORPHAN REMOVAL
	private List<Projecttask> projecttask=new ArrayList<>();
	
	public Backlog() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPTSequence() {
		return PTSequence;
	}
	public void setPTSequence(Integer pTSequence) {
		PTSequence = pTSequence;
	}
	public String getProjectIdentifir() {
		return projectIdentifir;
	}
	public void setProjectIdentifir(String projectIdentifir) {
		this.projectIdentifir = projectIdentifir;
	}
	public List<Projecttask> getProjecttask() {
		return projecttask;
	}
	public void setProjecttask(List<Projecttask> projecttask) {
		this.projecttask = projecttask;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

}

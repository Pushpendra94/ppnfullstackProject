package com.example.demo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectIdentifir() {
		return projectIdentifir;
	}
	public void setProjectIdentifir(String projectIdentifir) {
		this.projectIdentifir = projectIdentifir;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpate_date() {
		return upate_date;
	}
	public void setUpate_date(Date upate_date) {
		this.upate_date = upate_date;
	}
	@NotBlank(message="project name is required")
	private String projectName;
	@NotBlank(message="project Identifier is required")
	@Size(min=4,max=5,message="please use 4to 5 character")
	@Column(updatable=false ,unique=true)
	private String projectIdentifir;
	@NotBlank(message="project description is required")
	private String description;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date create_date;
	@JsonFormat(pattern="yyyy-mm-dd")
	@Column(updatable=false)
	private Date upate_date;
	//@Autowired
    private String projectLeader;
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="project")
	//@Autowired
	//Backlog backlog=new Backlog();
	@JsonIgnore
	private Backlog backlog;
	@ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
	
	public Project()
	{
		
	}
	@PrePersist
	protected  void onCreate()
	{
		this.create_date=new Date();
	}
	@PreUpdate
	protected void onUpdate()
	{
		this.upate_date=new Date();
	}
	
	
	public Backlog getBacklog() {
		return backlog;
	}
	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getProjectLeader() {
		return projectLeader;
	}
	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}
	

}

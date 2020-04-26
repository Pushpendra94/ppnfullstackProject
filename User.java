package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Email(message="Username needs to be email")
	@NotBlank(message="username is required")
	@Column(unique = true)
	private String userName;
	@NotBlank(message="please enter your full name")
	private String fullName;
	@NotBlank(message="password field is required ")
	@Column(nullable = false)
	private String password;
	@Transient
	private String confirmPassword;
	private Date create_At;
	private Date update_At;
	//OneToMany with Project
	
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    //For user management you might wan to JsonIgnore
   //List<Project> projects = new ArrayList<>();
   private List<Project> projects = new ArrayList<>();

	public User() {
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	} 
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	} 
	public Date getCreate_At() {
		return create_At;
	}

	public void setCreate_At(Date create_At) {
		this.create_At = create_At;
	}

	public Date getUpdate_At() {
		return update_At;
	}

	public void setUpdate_At(Date update_At) {
		this.update_At = update_At;
	}


	
	@PrePersist
	protected void onCreate() {
		this.create_At=new Date();
		
	}
	@PreUpdate
	protected void onUpdate() {
		this.update_At=new Date();
	}
	//UserDetails interface implementation
	@Override
    @JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}
	
	
}

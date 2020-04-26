package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
	
	Project findByProjectIdentifir(String projectId);
//	@Override
//	Iterable<Project>findAllById(Iterable<Long>iterable);
	
	@Override
	Iterable<Project>findAll();
	Iterable<Project>findAllByProjectLeader(String userName);

}

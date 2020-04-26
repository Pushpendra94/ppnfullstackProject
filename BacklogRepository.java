package com.example.demo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BacklogRepository  extends CrudRepository<Backlog,Long>{
	
	Backlog findByProjectIdentifir(String projectIdentifir);
}

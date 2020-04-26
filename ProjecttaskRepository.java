package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProjecttaskRepository  extends CrudRepository<Projecttask,Long>{
   List<Projecttask> findByProjectIdentifirOrderByPriority(String id);
   Projecttask findByAcceptanceCriteria(String sequence);
}

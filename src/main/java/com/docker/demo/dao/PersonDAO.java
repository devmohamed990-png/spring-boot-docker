package com.docker.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.docker.demo.model.Person;

public interface PersonDAO extends JpaRepository<Person, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE Person P SET P.name = ?1, P.email = ?2 WHERE P.id = ?3")
	public void update(String name, String email, long id);
}
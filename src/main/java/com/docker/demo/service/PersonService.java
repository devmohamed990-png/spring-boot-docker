package com.docker.demo.service;

import java.util.List;

import com.docker.demo.model.Person;

public interface PersonService {

	public List<Person> findAll();

	public Person findById(long id);

	public void save(Person person);

	public void update(Person person);

	public void delete(long id);
}
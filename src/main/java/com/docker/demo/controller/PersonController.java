package com.docker.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.docker.demo.model.Person;
import com.docker.demo.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping(value = "/findAll", produces = "application/json")
	public List<Person> findAll() {

		return personService.findAll();
	}

	@PostMapping(value = "/findById", produces = "application/json")
	public Person findById(@RequestParam("id") long id) {

		return personService.findById(id);
	}

	@PostMapping(value = "/save", produces = "application/json")
	public void save(@RequestBody Person person) {

		personService.save(person);
	}

	@RequestMapping(value = "/update", method = RequestMethod.OPTIONS)
	public void update(@RequestBody Person person) {

		personService.update(person);
	}

	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam("id") long id) {

		personService.delete(id);
		;
	}
}
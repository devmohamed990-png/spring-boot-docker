package com.docker.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docker.demo.dao.PersonDAO;
import com.docker.demo.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;

	@Override
	public List<Person> findAll() {

		return personDAO.findAll();
	}

	@Override
	public Person findById(long id) {

		Optional<Person> personOptional = personDAO.findById(id);

		if (personOptional.isPresent())
			return personOptional.get();
		else
			return null;
	}

	@Override
	public void save(Person person) {

		personDAO.save(person);
	}

	@Override
	public void update(Person person) {

		if (person.getId() > 0) {

			if (findById(person.getId()) != null) {

				personDAO.update(person.getName(), person.getEmail(), person.getId());

			} else {

			}
		}
	}

	@Override
	public void delete(long id) {

		personDAO.deleteById(id);
	}
}
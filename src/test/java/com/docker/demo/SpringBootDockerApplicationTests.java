package com.docker.demo;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.docker.demo.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment =WebEnvironment.DEFINED_PORT)
class SpringBootDockerApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private String getUrl() {

		return "http://localhost:9090";
	}

	@Test
	@DisplayName("Get All Persons")
	public void findAll() {

		List<Person> personList = restTemplate.getForEntity(getUrl() + "/findAll", List.class).getBody();

		assertNotNull(personList);
	}

	@Test
	@DisplayName("Get Person By his ID")
	public void findById() {

		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(getUrl() + "/findById")
				.queryParam("id", 1);

		Person person = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Person.class).getBody();

		assertNotNull(person);
	}

	@Test
	@DisplayName("Save New Person")
	public void save() {

		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(getUrl() + "/findById")
				.queryParam("id", 1);

		Person person = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Person.class).getBody();

		Person createdPerson = new Person("Mohamed Arafa", "devmohamed990@gmail.com");

		assertNull(restTemplate.postForEntity(getUrl() + "/save", createdPerson, Void.class).getBody());
	}
}
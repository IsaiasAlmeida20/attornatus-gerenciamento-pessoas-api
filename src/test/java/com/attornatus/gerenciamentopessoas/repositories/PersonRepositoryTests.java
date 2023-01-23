package com.attornatus.gerenciamentopessoas.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.attornatus.gerenciamentopessoas.entities.Person;

@DataJpaTest
public class PersonRepositoryTests {

	@Autowired
	private PersonRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
	}
	
	@Test
	public void saveShouldPersistEntity() {
		
		Person person = new Person(null, "Matheus", LocalDate.of(1999, 05, 12));
		
		person = repository.save(person);
		
		Assertions.assertNotNull(person.getId());
	}
	
	@Test 
	public void getReferenceByIdShouldReturnReferenceIdWhenIdExists() {
		
		Person person = repository.getReferenceById(existingId);
		
		Assertions.assertEquals(existingId, person.getId());
		
	}
	
	@Test
	public void findByIdShouldReturnOptionalPersonNotEmptyWhenIdExists() {
			
		Optional<Person> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnOptionalPersonEmptyWhenIdDoesNotExists() {
		
		Optional<Person> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
	
}

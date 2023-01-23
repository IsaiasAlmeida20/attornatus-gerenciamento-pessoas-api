package com.attornatus.gerenciamentopessoas.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.attornatus.gerenciamentopessoas.dto.AddressDTO;
import com.attornatus.gerenciamentopessoas.dto.PersonDTO;
import com.attornatus.gerenciamentopessoas.entities.Address;
import com.attornatus.gerenciamentopessoas.entities.Person;
import com.attornatus.gerenciamentopessoas.repositories.AddressRepository;
import com.attornatus.gerenciamentopessoas.repositories.PersonRepository;
import com.attornatus.gerenciamentopessoas.services.exceptions.ResourceNotFoundException;
import com.attornatus.gerenciamentopessoas.tests.Factory;

@ExtendWith(SpringExtension.class)
public class PersonServiceTests {
	
	@InjectMocks
	private PersonService personService;
	
	@Mock
	private PersonRepository personRepository;
	
	@Mock
	private AddressRepository addressRepository;
	
	private Long existingId;
	private Long nonExistingId;
	private Person person;
	private PersonDTO personDTO;
	private PageImpl<Person> page;
	private Address address;
	private AddressDTO addressDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		person = Factory.createPerson();
		personDTO = Factory.createPersonDTO();
		page = new PageImpl<>(List.of(person));
		address = Factory.createAddress();
		addressDTO = new AddressDTO(address);
		person.getAdresses().add(address);
		
		Mockito.when(personRepository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		Mockito.when(personRepository.save(ArgumentMatchers.any())).thenReturn(person);
		Mockito.when(addressRepository.save(ArgumentMatchers.any())).thenReturn(address);
		
		Mockito.when(personRepository.findById(existingId)).thenReturn(Optional.of(person));
		Mockito.when(personRepository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.when(personRepository.getReferenceById(existingId)).thenReturn(person);
		Mockito.when(personRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
	}
	
	@Test
	public void findAllPersonPagedShouldReturnPage() {
		
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<PersonDTO> result = personService.findAllPersonPaged(pageable);
		
		Assertions.assertNotNull(result);
		Mockito.verify(personRepository).findAll(pageable);
	}
	
	@Test void createPersonPersonShouldReturnPerson() {
		
		PersonDTO dto = personService.createPerson(personDTO);
		
		Assertions.assertNotNull(dto);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			personService.updatePerson(nonExistingId, personDTO);
		});
	}
	
	@Test
	public void updateShouldReturnProductDTOWhenIdExists() {
		
		PersonDTO result = personService.updatePerson(existingId, personDTO);
		
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			personService.findPersonById(nonExistingId);
		});
	}
	
	@Test
	public void findByIdShouldReturnPersontDTOWhenIdExists() {
		
		PersonDTO result = personService.findPersonById(existingId);
		
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void createAddresPersonShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			personService.createAddresPerson(nonExistingId, addressDTO);
		});
	}
	
	@Test
	public void createAddresPersonShouldReturnAddressDTOWhenIdExists() {
		
		AddressDTO result = personService.createAddresPerson(existingId, addressDTO);
		
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findAllAddressPersonShouldReturnListAddressDTO() {
		
		List<AddressDTO> result = personService.findAllAddressPerson(existingId);
		
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findAllAddressPersonShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			personService.findAllAddressPerson(nonExistingId);
		});
	}
}

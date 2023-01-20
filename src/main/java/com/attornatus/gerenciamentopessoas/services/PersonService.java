package com.attornatus.gerenciamentopessoas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.gerenciamentopessoas.dto.AddressDTO;
import com.attornatus.gerenciamentopessoas.dto.PersonDTO;
import com.attornatus.gerenciamentopessoas.entities.Address;
import com.attornatus.gerenciamentopessoas.entities.Person;
import com.attornatus.gerenciamentopessoas.repositories.AddressRepository;
import com.attornatus.gerenciamentopessoas.repositories.PersonRepository;
import com.attornatus.gerenciamentopessoas.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressRepository addressRepository; 
	
	@Transactional
	public PersonDTO create(PersonDTO dto) {
		Person entity = new Person();
		Address address = new Address();
		copyDtoToEntity(dto, entity, address);
		entity = personRepository.save(entity);
		return new PersonDTO(entity);
	}
	
	@Transactional
	public PersonDTO update(Long id, PersonDTO dto) {
		try {
			Person entity = personRepository.getReferenceById(id);
			Address address = new Address();
			copyDtoToEntity(dto, entity, address);
			entity = personRepository.save(entity);
			return new PersonDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	@Transactional(readOnly = true)
	public PersonDTO findById(Long id) {
		Optional<Person> obj = personRepository.findById(id);
		Person entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new PersonDTO(entity, entity.getAdresses());
	}
	
	@Transactional(readOnly = true)
	public Page<PersonDTO> findAllPaged(Pageable pageable) {
		Page<Person> list = personRepository.findAll(pageable);
		return list.map(x -> new PersonDTO(x, x.getAdresses()));
	}
	
	private void copyDtoToEntity(PersonDTO dto, Person entity, Address address) {
		
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		
		entity.getAdresses().clear();
		for (AddressDTO addressDTO : dto.getAdresses()) {
			
			Address createAddres = new Address();
			
			createAddres.setPublicPlace(addressDTO.getPublicPlace());
			createAddres.setZipCode(addressDTO.getZipCode());
			createAddres.setNumber(addressDTO.getNumber());
			createAddres.setCity(addressDTO.getCity());
			
			createAddres = addressRepository.save(createAddres);
			
			entity.getAdresses().add(createAddres);
		}
		
	}

}
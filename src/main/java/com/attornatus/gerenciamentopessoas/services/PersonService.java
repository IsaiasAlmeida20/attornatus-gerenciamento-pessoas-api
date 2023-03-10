package com.attornatus.gerenciamentopessoas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.gerenciamentopessoas.dto.AddressDTO;
import com.attornatus.gerenciamentopessoas.dto.PersonDTO;
import com.attornatus.gerenciamentopessoas.entities.Address;
import com.attornatus.gerenciamentopessoas.entities.Person;
import com.attornatus.gerenciamentopessoas.enumns.StatusAddress;
import com.attornatus.gerenciamentopessoas.repositories.AddressRepository;
import com.attornatus.gerenciamentopessoas.repositories.PersonRepository;
import com.attornatus.gerenciamentopessoas.services.exceptions.ResourceNotFoundException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressRepository addressRepository; 
	
	@Transactional
	public PersonDTO createPerson(PersonDTO personDTO) {
		Person person = new Person();
		copyPersonDtoToEntity(personDTO, person);
		person = personRepository.save(person);
		return new PersonDTO(person, person.getAdresses());
	}
	
	@Transactional
	public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
		try {
			Person person = personRepository.getReferenceById(id);
			person.setName(personDTO.getName());
			person.setBirthDate(personDTO.getBirthDate());
			person = personRepository.save(person);
			return new PersonDTO(person);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	@Transactional(readOnly = true)
	public PersonDTO findPersonById(Long id) {
		Optional<Person> obj = personRepository.findById(id);
		Person person = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new PersonDTO(person, person.getAdresses());
	}
	
	@Transactional(readOnly = true)
	public Page<PersonDTO> findAllPersonPaged(Pageable pageable) {
		Page<Person> list = personRepository.findAll(pageable);
		return list.map(person -> new PersonDTO(person, person.getAdresses()));
	}
	
	@Transactional
	public AddressDTO createAddresPerson(Long id, AddressDTO addressDTO) {
		try {
			Person person = personRepository.getReferenceById(id);
			Address address = new Address();
			copyAddressDtoToEntity(addressDTO, address);
			address = addressRepository.save(address);
			person.getAdresses().add(address);
			return new AddressDTO(address);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	
	@Transactional(readOnly = true)
	public List<AddressDTO> findAllAddressPerson(Long id) {
		try {
			Person person = personRepository.getReferenceById(id);
			return person.getAdresses().stream().map(address -> new AddressDTO(address)).collect(Collectors.toList());
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	@Transactional
	public PersonDTO changePrimaryAddress(Long perosnId, Long addressId) {
		try {
			Person person = personRepository.getReferenceById(perosnId);
			Address addr = addressRepository.getReferenceById(addressId);
			if(person.getAdresses().contains(addr)) {
				for(Address address : person.getAdresses()) {
					if(address.getId() == addressId ) {
						address.setStatus(StatusAddress.PRIMARY);
					}else {
						address.setStatus(StatusAddress.SECONDARY);
					}
					address = addressRepository.save(address);
				}
			}else {
				throw new ResourceNotFoundException("Id not found " + addressId);
			}
			return new PersonDTO(person, person.getAdresses());

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + perosnId);
		}
	}
	
	
	private void copyPersonDtoToEntity(PersonDTO personDTO, Person person) {
		
		person.setName(personDTO.getName());
		person.setBirthDate(personDTO.getBirthDate());
		
		person.getAdresses().clear();
		for (AddressDTO addressDTO : personDTO.getAdresses()) {
			
			Address createAddress = new Address();
			
			createAddress.setPublicPlace(addressDTO.getPublicPlace());
			createAddress.setZipCode(addressDTO.getZipCode());
			createAddress.setNumber(addressDTO.getNumber());
			createAddress.setCity(addressDTO.getCity());
			createAddress.setStatus(addressDTO.getStatus());
			
			createAddress = addressRepository.save(createAddress);
			
			person.getAdresses().add(createAddress);
		}
		
	}
	
	private void copyAddressDtoToEntity(AddressDTO addressDTO, Address address) {
		
		address.setPublicPlace(addressDTO.getPublicPlace());
		address.setZipCode(addressDTO.getZipCode());
		address.setNumber(addressDTO.getNumber());
		address.setCity(addressDTO.getCity());
		address.setStatus(addressDTO.getStatus());
	}

}

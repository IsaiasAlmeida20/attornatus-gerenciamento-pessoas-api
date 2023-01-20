package com.attornatus.gerenciamentopessoas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.gerenciamentopessoas.dto.AddressDTO;
import com.attornatus.gerenciamentopessoas.entities.Address;
import com.attornatus.gerenciamentopessoas.entities.Person;
import com.attornatus.gerenciamentopessoas.repositories.AddressRepository;
import com.attornatus.gerenciamentopessoas.repositories.PersonRepository;
import com.attornatus.gerenciamentopessoas.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository; 
	
	@Autowired
	private PersonRepository personRepository;
	
	@Transactional
	public AddressDTO createAddres(Long id, AddressDTO dto) {
		Person person = personRepository.getReferenceById(id);
		Address address= new Address();
		
		address.setPublicPlace(dto.getPublicPlace());
		address.setZipCode(dto.getZipCode());
		address.setNumber(dto.getNumber());
		address.setCity(dto.getCity());
		
		address = addressRepository.save(address);
		
		person.getAdresses().add(address);
		
		return new AddressDTO(address);
	}
	
	@Transactional(readOnly = true)
	public List<AddressDTO> findById(Long id) {
		try {
			Person person = personRepository.getReferenceById(id);
			return person.getAdresses().stream().map(address -> new AddressDTO(address)).collect(Collectors.toList());
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	@Transactional(readOnly = true)
	public List<AddressDTO> findAll() {
		List<Address> list = addressRepository.findAll();
		return list.stream().map(x -> new AddressDTO(x)).collect(Collectors.toList());
	}
}

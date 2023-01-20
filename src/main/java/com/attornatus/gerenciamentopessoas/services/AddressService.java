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
	public List<AddressDTO> findAll() {
		List<Address> list = addressRepository.findAll();
		return list.stream().map(x -> new AddressDTO(x)).collect(Collectors.toList());
	}
}

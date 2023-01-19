package com.attornatus.gerenciamentopessoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.gerenciamentopessoas.dto.AddressDTO;
import com.attornatus.gerenciamentopessoas.entities.Address;
import com.attornatus.gerenciamentopessoas.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;
	
	@Transactional
	public AddressDTO createAddres(AddressDTO dto) {
		Address entity = new Address();
		entity.setPublicPlace(dto.getPublicPlace());
		entity.setZipCode(dto.getZipCode());
		entity.setNumber(dto.getNumber());
		entity.setCity(dto.getCity());
		entity = repository.save(entity);
		return new AddressDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<AddressDTO> findAllPaged(Pageable pageable) {
		Page<Address> list = repository.findAll(pageable);
		return list.map(x -> new AddressDTO(x));
	}
}

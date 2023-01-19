package com.attornatus.gerenciamentopessoas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.gerenciamentopessoas.dto.AddressDTO;
import com.attornatus.gerenciamentopessoas.services.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressResource {
	
	@Autowired
	private AddressService service;
	
	@GetMapping
	public ResponseEntity<Page<AddressDTO>> findAll(Pageable pageable) {
		Page<AddressDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

}

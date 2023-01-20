package com.attornatus.gerenciamentopessoas.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.attornatus.gerenciamentopessoas.dto.AddressDTO;
import com.attornatus.gerenciamentopessoas.services.AddressService;

@RestController
@RequestMapping(value = "/adresses")
public class AddressResource {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<AddressDTO>> findAll() {
		List<AddressDTO> list = addressService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<AddressDTO>> findAllAddressPerson(@PathVariable Long id) {
		List<AddressDTO> list = addressService.findById(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<AddressDTO> insert(@PathVariable Long id,@RequestBody AddressDTO dto) {
		dto = addressService.createAddres(id, dto);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AddressDTO> changePrimaryAddress(@PathVariable Long id) {
		AddressDTO dto = addressService.changePrimaryAddress(id);
		return ResponseEntity.ok().body(dto);
	}
	

}

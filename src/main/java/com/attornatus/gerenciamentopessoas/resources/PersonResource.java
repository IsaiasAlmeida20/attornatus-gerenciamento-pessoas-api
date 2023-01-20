package com.attornatus.gerenciamentopessoas.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.attornatus.gerenciamentopessoas.dto.PersonDTO;
import com.attornatus.gerenciamentopessoas.services.PersonService;

@RestController
@RequestMapping(value = "/person")
public class PersonResource {
	
	@Autowired
	private PersonService service;
	
	@PostMapping
	public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
		personDTO = service.create(personDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(personDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(personDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PersonDTO> findPersonById(@PathVariable Long id) {
		PersonDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<PersonDTO>> findAllPerson(Pageable pageable) {
		Page<PersonDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}
	
}

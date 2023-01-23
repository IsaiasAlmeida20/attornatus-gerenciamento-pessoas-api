package com.attornatus.gerenciamentopessoas.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
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
import com.attornatus.gerenciamentopessoas.dto.PersonDTO;
import com.attornatus.gerenciamentopessoas.services.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Management person", description = "Api for management person")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@PostMapping
	@Operation(summary = "Create a person")
	@ApiResponse(responseCode = "201", description = "created", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
	})
	public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
		personDTO = personService.createPerson(personDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(personDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(personDTO);
	}
	
	@PutMapping(value = "/{id}")
	@Operation(summary = "Update a person")
	@ApiResponse(responseCode = "200", description = "success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
	})
	public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
		personDTO = personService.updatePerson(id, personDTO);
		return ResponseEntity.ok().body(personDTO);
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Find one person")
	@ApiResponse(responseCode = "200", description = "success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
	})
	public ResponseEntity<PersonDTO> findPersonById(@PathVariable Long id) {
		PersonDTO dto = personService.findPersonById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping
	@Operation(summary = "Find all person")
	@ApiResponse(responseCode = "200", description = "success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
	})
	public ResponseEntity<Page<PersonDTO>> findAllPerson(Pageable pageable) {
		Page<PersonDTO> list = personService.findAllPersonPaged(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping(value = "/{id}/address")
	@Operation(summary = "Create address to person")
	@ApiResponse(responseCode = "201", description = "success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
	})
	public ResponseEntity<AddressDTO> createAddresPerson(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
		addressDTO = personService.createAddresPerson(id, addressDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addressDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(addressDTO);
	}
	
	@GetMapping(value = "/{id}/address")
	@Operation(summary = "Find all adresses person")
	@ApiResponse(responseCode = "200", description = "success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
	})
	public ResponseEntity<List<AddressDTO>> findAllAddressPerson(@PathVariable Long id) {
		List<AddressDTO> list = personService.findAllAddressPerson(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping(value = "/{personId}/{addressId}")
	@Operation(summary = "Change address as primary")
	@ApiResponse(responseCode = "200", description = "success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
	})
	public ResponseEntity<PersonDTO> changePrimaryAddress(@PathVariable Long personId, @PathVariable Long addressId) {
		PersonDTO personDTO = personService.changePrimaryAddress(personId, addressId);
		return ResponseEntity.ok().body(personDTO);
	}
	
	
}

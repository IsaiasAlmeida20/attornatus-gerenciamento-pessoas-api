package com.attornatus.gerenciamentopessoas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.gerenciamentopessoas.dto.EnderecoDTO;
import com.attornatus.gerenciamentopessoas.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService service;
	
	@GetMapping
	public ResponseEntity<Page<EnderecoDTO>> findAll(Pageable pageable) {
		Page<EnderecoDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

}

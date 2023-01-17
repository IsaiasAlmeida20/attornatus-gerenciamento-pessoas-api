package com.attornatus.gerenciamentopessoas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.gerenciamentopessoas.dto.PessoaDTO;
import com.attornatus.gerenciamentopessoas.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService service;
	
	@GetMapping
	public ResponseEntity<Page<PessoaDTO>> findAll(Pageable pageable) {
		Page<PessoaDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

}

package com.attornatus.gerenciamentopessoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.gerenciamentopessoas.dto.AddressDTO;
import com.attornatus.gerenciamentopessoas.dto.PessoaDTO;
import com.attornatus.gerenciamentopessoas.entities.Address;
import com.attornatus.gerenciamentopessoas.entities.Person;
import com.attornatus.gerenciamentopessoas.repositories.AddressRepository;
import com.attornatus.gerenciamentopessoas.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private AddressRepository enderecoRepository; 
	

	@Transactional
	public PessoaDTO insert(PessoaDTO dto) {
		Person entity = new Person();
		copyDtoToEntity(dto, entity);
		entity = pessoaRepository.save(entity);
		return new PessoaDTO(entity);
	}
	
	
	
	@Transactional(readOnly = true)
	public Page<PessoaDTO> findAllPaged(Pageable pageable) {
		Page<Person> list = pessoaRepository.findAll(pageable);
		return list.map(x -> new PessoaDTO(x));
	}
	
	
	
	private void copyDtoToEntity(PessoaDTO dto, Person entity) {
		entity.setNome(dto.getNome());
		entity.setDataNascimento(dto.getDataNascimento());
		entity.getEnderecos().clear();
		for (AddressDTO enderecoDTO : dto.getEnderecos()) {
			Address endereco = enderecoRepository.getReferenceById(enderecoDTO.getId());
			entity.getEnderecos().add(endereco);
		}
		
	}
}

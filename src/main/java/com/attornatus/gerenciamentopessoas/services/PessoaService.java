package com.attornatus.gerenciamentopessoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.gerenciamentopessoas.dto.AddressDTO;
import com.attornatus.gerenciamentopessoas.dto.PersonDTO;
import com.attornatus.gerenciamentopessoas.entities.Address;
import com.attornatus.gerenciamentopessoas.entities.Person;
import com.attornatus.gerenciamentopessoas.repositories.AddressRepository;
import com.attornatus.gerenciamentopessoas.repositories.PersonRepository;

@Service
public class PessoaService {

	@Autowired
	private PersonRepository pessoaRepository;
	
	@Autowired
	private AddressRepository enderecoRepository; 
	

	@Transactional
	public PersonDTO insert(PersonDTO dto) {
		Person entity = new Person();
		copyDtoToEntity(dto, entity);
		entity = pessoaRepository.save(entity);
		return new PersonDTO(entity);
	}
	
	
	
	@Transactional(readOnly = true)
	public Page<PersonDTO> findAllPaged(Pageable pageable) {
		Page<Person> list = pessoaRepository.findAll(pageable);
		return list.map(x -> new PersonDTO(x));
	}
	
	
	
	private void copyDtoToEntity(PersonDTO dto, Person entity) {
		entity.setNome(dto.getNome());
		entity.setDataNascimento(dto.getDataNascimento());
		entity.getEnderecos().clear();
		for (AddressDTO enderecoDTO : dto.getEnderecos()) {
			Address endereco = enderecoRepository.getReferenceById(enderecoDTO.getId());
			entity.getEnderecos().add(endereco);
		}
		
	}
}

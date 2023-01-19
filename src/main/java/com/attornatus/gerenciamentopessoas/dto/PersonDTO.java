package com.attornatus.gerenciamentopessoas.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.attornatus.gerenciamentopessoas.entities.Address;
import com.attornatus.gerenciamentopessoas.entities.Person;

public class PersonDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private LocalDate birthDate;
	
	private List<AddressDTO> adresses = new ArrayList<>();
	
	public PersonDTO() {
	}

	public PersonDTO(Long id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	
	public PersonDTO(Person entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.birthDate = entity.getBirthDate();
	}
	
	public PersonDTO(Person entity, Set<Address> adresses) {
		this(entity);
		adresses.forEach(address -> this.adresses.add(new AddressDTO(address)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<AddressDTO> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<AddressDTO> adresses) {
		this.adresses = adresses;
	}

}

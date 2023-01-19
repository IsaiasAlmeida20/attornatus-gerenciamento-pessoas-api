package com.attornatus.gerenciamentopessoas.dto;

import java.io.Serializable;

import com.attornatus.gerenciamentopessoas.entities.Address;

public class AddressDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String publicPlace;
	private Long zipCode;
	private Integer number;
	private String city;
	
	public AddressDTO() {
	}

	public AddressDTO(Long id, String publicPlace, Long zipCode, Integer number, String city) {
		this.id = id;
		this.publicPlace = publicPlace;
		this.zipCode = zipCode;
		this.number = number;
		this.city = city;
	}
	
	public AddressDTO(Address entity) {
		this.id = entity.getId();
		this.publicPlace = entity.getPublicPlace();
		this.zipCode = entity.getZipCode();
		this.number = entity.getNumber();
		this.city = entity.getCity();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return publicPlace;
	}

	public void setLogradouro(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}

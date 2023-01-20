package com.attornatus.gerenciamentopessoas.dto;

import java.io.Serializable;

import com.attornatus.gerenciamentopessoas.entities.Address;
import com.attornatus.gerenciamentopessoas.enumns.StatusAddress;

public class AddressDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String publicPlace;
	private Long zipCode;
	private Integer number;
	private String city;
	private StatusAddress status;
	
	public AddressDTO() {
	}

	public AddressDTO(Long id, String publicPlace, Long zipCode, Integer number, String city, StatusAddress status) {
		this.id = id;
		this.publicPlace = publicPlace;
		this.zipCode = zipCode;
		this.number = number;
		this.city = city;
		this.status = status;
	}
	
	public AddressDTO(Address entity) {
		this.id = entity.getId();
		this.publicPlace = entity.getPublicPlace();
		this.zipCode = entity.getZipCode();
		this.number = entity.getNumber();
		this.city = entity.getCity();
		this.status = entity.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
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

	public StatusAddress getStatus() {
		return status;
	}

	public void setStatus(StatusAddress status) {
		this.status = status;
	}
	
}

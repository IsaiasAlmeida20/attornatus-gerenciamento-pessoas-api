package com.attornatus.gerenciamentopessoas.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.attornatus.gerenciamentopessoas.enumns.StatusAddress;

@Entity
@Table(name = "tb_addres")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String publicPlace;
	
	@Column(nullable = false)
	private Integer zipCode;
	
	@Column(nullable = false)
	private Integer number;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusAddress status;
	
	public Address() {
	}

	public Address(Long id, String publicPlace, Integer zipCode, Integer number, String city, StatusAddress status) {
		this.id = id;
		this.publicPlace = publicPlace;
		this.zipCode = zipCode;
		this.number = number;
		this.city = city;
		this.status = status;
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

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}
	
}

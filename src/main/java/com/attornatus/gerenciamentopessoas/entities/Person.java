package com.attornatus.gerenciamentopessoas.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
	private LocalDate birthDate;
	
	@ManyToMany
	@JoinTable(
			name = "tb_person_address",
			joinColumns = @JoinColumn(name = "person_id"),
			inverseJoinColumns = @JoinColumn(name = "address_id")
			)
	private Set<Address> adresses = new HashSet<>();

	public Person() {
	}

	public Person(Long id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
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
	
	public Set<Address> getAdresses() {
		return adresses;
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
		Person other = (Person) obj;
		return Objects.equals(id, other.id);
	}
	
}

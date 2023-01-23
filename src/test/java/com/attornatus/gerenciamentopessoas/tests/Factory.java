package com.attornatus.gerenciamentopessoas.tests;

import java.time.LocalDate;

import com.attornatus.gerenciamentopessoas.dto.AddressDTO;
import com.attornatus.gerenciamentopessoas.dto.PersonDTO;
import com.attornatus.gerenciamentopessoas.entities.Address;
import com.attornatus.gerenciamentopessoas.entities.Person;
import com.attornatus.gerenciamentopessoas.enumns.StatusAddress;

public class Factory {

	public static Person createPerson() {  
		Person person = new Person(1L, "Matheus", LocalDate.of(1999, 05, 12));
		person.getAdresses().add(createAddress());
		return person;
	}
	
	public static PersonDTO createPersonDTO() {
		Person person = createPerson();
		return new PersonDTO(person, person.getAdresses());
	}
	
	public static Address createAddress() {
		return new Address(1L, "Rua Z", 1200000, 10, "City 03", StatusAddress.SECONDARY);
	}
	
	public static AddressDTO createAddressDTO() {
		Address address = createAddress();
		return new AddressDTO(address);
	}
}

package com.attornatus.gerenciamentopessoas.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.attornatus.gerenciamentopessoas.dto.PersonDTO;
import com.attornatus.gerenciamentopessoas.services.PersonService;
import com.attornatus.gerenciamentopessoas.services.exceptions.ResourceNotFoundException;
import com.attornatus.gerenciamentopessoas.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PersonController.class)
public class PersonControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Long existingId;
	private Long nonExistingId;
	private PersonDTO personDTO;
	private PageImpl<PersonDTO> page;
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExistingId = 2L;
		
		personDTO = Factory.createPersonDTO();
		page = new PageImpl<>(List.of(personDTO));
		
		when(personService.findAllPersonPaged(any())).thenReturn(page);

		when(personService.findPersonById(existingId)).thenReturn(personDTO);
		when(personService.findPersonById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

		when(personService.createPerson(any())).thenReturn(personDTO);
		
		when(personService.updatePerson(eq(existingId), any())).thenReturn(personDTO);
		when(personService.updatePerson(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);

	}
	
	@Test
	public void createPersonShouldReturnPersonDTOCreated() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(personDTO);
		
		ResultActions result = 
				mockMvc.perform(post("/person")
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.birthDate").exists());
	}
	
	@Test
	public void updatePersonShouldReturnPersonDTOWhenIdExists() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(personDTO);
		
		ResultActions result = 
				mockMvc.perform(put("/person/{id}", existingId)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.birthDate").exists());
	}
	
	@Test
	public void updatePersonShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(personDTO);
		
		ResultActions result = 
				mockMvc.perform(put("/person/{id}", nonExistingId)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findPersonByIdShouldReturnPersonWhenIdExists() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(get("/person/{id}", existingId)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.birthDate").exists());
	}
	
	@Test
	public void findPersonByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(get("/person/{id}", nonExistingId)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findAllPersonShouldReturnPage() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(get("/person")
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
	}
		
}

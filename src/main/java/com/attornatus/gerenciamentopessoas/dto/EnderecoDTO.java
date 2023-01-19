package com.attornatus.gerenciamentopessoas.dto;

import java.io.Serializable;

import com.attornatus.gerenciamentopessoas.entities.Address;

public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String logradouro;
	private Long cep;
	private Integer numero;
	private String cidade;
	private Long pessoaId;
	
	public EnderecoDTO() {
	}

	public EnderecoDTO(Long id, String logradouro, Long cep, Integer numero, String cidade, Long pessoaId) {
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.pessoaId = pessoaId;
	}
	
	public EnderecoDTO(Address entity) {
		this.id = entity.getId();
		this.logradouro = entity.getLogradouro();
		this.cep = entity.getCep();
		this.numero = entity.getNumero();
		this.cidade = entity.getCidade();
		this.pessoaId = entity.getPessoa().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}
	
}

package com.attornatus.gerenciamentopessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.gerenciamentopessoas.entities.Pessoa;

@Repository
public interface PessoaRespository extends JpaRepository<Pessoa, Long> {

}

package com.attornatus.gerenciamentopessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.gerenciamentopessoas.entities.Person;

@Repository
public interface PessoaRepository extends JpaRepository<Person, Long> {

}

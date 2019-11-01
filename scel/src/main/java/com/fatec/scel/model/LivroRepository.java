package com.fatec.scel.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatec.scel.model.Livro;
//https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
@Repository
public interface LivroRepository extends JpaRepository <Livro, Integer> {
	@Query("select a from Livro a where a.isbn = ?1")
	Optional<Livro> findByISBN(String isbn);
}
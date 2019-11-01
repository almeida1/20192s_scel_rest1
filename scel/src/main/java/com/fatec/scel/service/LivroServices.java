package com.fatec.scel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
/**
 * Valida as regras de negócio da classe Livro
 * @author edson
 *
 */
@Service
public class LivroServices {
	@Autowired
	private LivroRepository livroRepo;
	public List<Livro> findAll() {
		List<Livro> livros = livroRepo.findAll();
		return livros;
	}

	public ResponseEntity<Object> buscaPorRa(String isbn) {
		Optional<Livro> livro = livroRepo.findByISBN(isbn);
		if (livro.isPresent()) 
			 return new ResponseEntity<>(livro, HttpStatus.OK);
			else
			 return new ResponseEntity<>("Livro não encontrado", HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Object> save(Livro livro) {
		Optional<Livro> umLivro = livroRepo.findByISBN(livro.getIsbn());
	
		if (umLivro.isPresent()) {
			return new ResponseEntity<>("Livro já cadastrado", HttpStatus.BAD_REQUEST); //(400)
		} else {
			livroRepo.save(livro);
			return new ResponseEntity<>("Livro incluido com sucesso", HttpStatus.CREATED);
		}
	}
	public ResponseEntity<Object> update(Livro livro) {
		livroRepo.save(livro);
		return new ResponseEntity<>("Livro atualizado com sucesso", HttpStatus.OK);
	}
    public ResponseEntity<Object> delete(String isbn) {
		
		Optional<Livro> livro = livroRepo.findByISBN(isbn);
		if (livro.isPresent())  {
			livroRepo.delete(livro.get());
			return new ResponseEntity<>("Livro excluido", HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Livro não encontrado", HttpStatus.NOT_FOUND);
	}
}
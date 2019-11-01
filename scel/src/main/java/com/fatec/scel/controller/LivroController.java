package com.fatec.scel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.scel.model.Livro;
import com.fatec.scel.service.LivroServices;

@RestController
@RequestMapping(value="/api")
public class LivroController {
	
	@Autowired
	private LivroServices servico;
	
	@RequestMapping(value = "/livros", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getTodos() {
		return new ResponseEntity<>(servico.findAll(), HttpStatus.OK);
	}
	@RequestMapping(value = "/livros", method = RequestMethod.POST)
	public ResponseEntity<Object> cadastraLivro(@RequestBody Livro livro) {
		return servico.save(livro);
	}
	
	@RequestMapping(value = "/livros", method = RequestMethod.PUT)
	public ResponseEntity<Object> atualizaLivro(@RequestBody Livro livro) {
		return servico.update(livro);
	}
	@RequestMapping(value="/livros/{isbn}", method=RequestMethod.GET)
	public ResponseEntity<?> pesquisarPorRa(@PathVariable String isbn) {
		return servico.buscaPorRa(isbn);
	}
	@RequestMapping(value = "/livros/{isbn}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> excluirLivro(@PathVariable("isbn") String isbn) {
		return servico.delete(isbn);
	}
}
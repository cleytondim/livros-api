package br.com.kbase.livros.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.kbase.livros.domain.model.Autor;
import br.com.kbase.livros.domain.model.Critica;
import br.com.kbase.livros.domain.model.Livro;
import br.com.kbase.livros.domain.repository.AutorRepository;
import br.com.kbase.livros.domain.repository.CriticaRepository;
import br.com.kbase.livros.domain.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivroController {


	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private CriticaRepository criticaRepository;
	
	
	@GetMapping
	public List<Livro> Listar() {
		return livroRepository.findAll();
	}
	
	@GetMapping("/{livroId}")
	public ResponseEntity<Livro> buscar(@PathVariable String livroId) {
		return livroRepository.findById(livroId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@GetMapping("/autor/{autorNome}")
	@ResponseStatus(HttpStatus.OK)
	public List<Livro> buscarPorAutor(@PathVariable String autorNome) {
		List<Autor>Autores = autorRepository.findByNomeContaining(autorNome);
		List<Livro> livros = new ArrayList<>();
		for (Autor autor : Autores) {
			livros.add(autor.getLivro());
		}
		
		return livros;
	}
	
	
	@PostMapping
	public String cadastrar(@Valid @RequestBody Livro livro, HttpServletResponse response) {
		
		if(livroRepository.existsById(livro.getIsbn())) {
			 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			 return "Livro com ISBN "+livro.getIsbn()+" ja existe";
		}
		for (Autor autor : livro.getAutores()) {
			autor.setLivro(livro);
		}
		for (Critica critica : livro.getCriticas()) {
			critica.setLivro(livro);
		}
		livroRepository.save(livro);
		 response.setStatus(HttpServletResponse.SC_CREATED);
		return "Cadastrado";
	}
	
	@PutMapping("/{isbn}")
	public String atualizar(@PathVariable String isbn,
			@Valid @RequestBody Livro livro, HttpServletResponse response){
		if(!livroRepository.existsById(isbn)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "Livro com ISBN "+livro.getIsbn()+" nao existe";
		}
		livro.setIsbn(isbn);
		
	
		List<Autor> autores = autorRepository.findByLivro(livro);
		for (Autor autor : autores) {
			autorRepository.deleteById(autor.getId());
		}
		for (Autor autor : livro.getAutores()) {
			autor.setLivro(livro);
		}
		List<Critica> criticas = criticaRepository.findByLivro(livro);
		for (Critica critica : criticas) {
			criticaRepository.deleteById(critica.getId());
		}
		for (Critica critica : livro.getCriticas()) {
			critica.setLivro(livro);
		}
		livro = livroRepository.save(livro);
		response.setStatus(HttpServletResponse.SC_OK);
		return "Livro com ISBN "+livro.getIsbn()+" foi atualizado";
	}
	
	@DeleteMapping("/{livroId}")
	public ResponseEntity<Void> remover(@PathVariable String livroId) {
		if(!livroRepository.existsById(livroId)) {
			return ResponseEntity.notFound().build();
		}
		livroRepository.deleteById(livroId);
		return ResponseEntity.noContent().build();
	}
	
	
}

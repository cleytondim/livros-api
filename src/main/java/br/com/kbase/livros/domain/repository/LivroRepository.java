package br.com.kbase.livros.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kbase.livros.domain.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, String>{

	List<Livro> findByTitulo(String titulo);
	
	List<Livro> findByTituloContaining(String titulo);
	
	
	
}

package br.com.kbase.livros.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kbase.livros.domain.model.Autor;
import br.com.kbase.livros.domain.model.Livro;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{
	List<Autor> findByNomeContaining(String nomeAutor);
	List<Autor> findByLivro(Livro livro);
}

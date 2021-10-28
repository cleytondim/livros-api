package br.com.kbase.livros.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kbase.livros.domain.model.Critica;
import br.com.kbase.livros.domain.model.Livro;

@Repository
public interface CriticaRepository extends JpaRepository<Critica, Integer>{
	List<Critica> findByLivro(Livro livro);
}

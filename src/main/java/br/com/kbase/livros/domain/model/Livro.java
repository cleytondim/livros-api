package br.com.kbase.livros.domain.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "livro")
public class Livro {

	@EqualsAndHashCode.Include
	@Id
	private String isbn;
	
	@NotBlank
	@Size(max=255)
	private String titulo;
	
	@NotNull
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "livro")
	private List<Autor> autores = new ArrayList<>();
	
	@NotBlank
	@Size(max=100)
	private String editora;
	
	@NotNull
	private Date datalancamento;
	
	@NotNull
	private float preco;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "livro")
	private List<Critica> criticas = new ArrayList<>();
	
}

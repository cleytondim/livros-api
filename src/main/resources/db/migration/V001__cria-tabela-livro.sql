create table livro (
	isbn varchar(60) not null,
    titulo varchar(255) not null,
    editora varchar(100) not null,
    datalancamento DATE not null,
    preco float not null,
    
    primary key (isbn)
);
create table autor (
	id_autor int not null AUTO_INCREMENT,
    nome varchar(255) not null,
    isbn varchar(60) not null,
    
    primary key (id_autor),
    foreign key (isbn) REFERENCES livro(isbn)
);

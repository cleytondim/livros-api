create table critica (
	id_critica int not null AUTO_INCREMENT,
    critica varchar(255) not null,
    isbn varchar(60) not null,
    
    primary key (id_critica),
    foreign key (isbn) REFERENCES livro(isbn)
);

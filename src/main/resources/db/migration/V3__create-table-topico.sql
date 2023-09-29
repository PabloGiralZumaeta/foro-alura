create table topico(
    id BIGINT not null auto_increment,
    titulo varchar(200) not null,
    mensaje varchar(400) not null,
    fecha_creacion datetime not null,
    estatus varchar(100) not null,
    autor_id bigint,
    curso_id int,

    primary key(id),
    FOREIGN KEY(autor_id) REFERENCES usuarios(id),
    FOREIGN KEY(curso_id) REFERENCES cursos(id)
);
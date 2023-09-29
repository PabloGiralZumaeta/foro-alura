create table respuesta(
    id bigint not null auto_increment,
    mensaje varchar(500) not null,
    topico_id bigint,
    fecha_creacion datetime,
    autor_id bigint,
    solucion tinyint,

    primary key(id),
    foreign key(topico_id) references topico(id),
    foreign key(autor_id) references  usuarios(id)
);
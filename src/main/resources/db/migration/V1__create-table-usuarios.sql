create table usuarios(
    id bigint not null auto_increment,
    login varchar(100) not null,
    clave varchar(100) not null,
    nombre_usuario varchar(150) not null,
    nombres varchar(100) not null,
    apellidos varchar(150) not null,
    correo varchar(150) not null,
    estado tinyint,

    primary key(id)
);
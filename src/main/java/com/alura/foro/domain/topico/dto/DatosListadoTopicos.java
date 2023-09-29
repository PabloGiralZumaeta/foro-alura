package com.alura.foro.domain.topico.dto;


import com.alura.foro.domain.curso.Cursos;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosListadoTopicos(String titulo, LocalDateTime fechaCreacion, Usuario autor, Cursos curso) {

    public DatosListadoTopicos(Topico topico){
        this(topico.getTitulo(),topico.getFechaCreacion(),topico.getAutor(),topico.getCurso());

    }


}

package com.alura.foro.domain.topico.dto;

import com.alura.foro.domain.curso.Cursos;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;

public record DatosSalidaGuardarTopico(String titulo, String mensaje, Usuario autor, Cursos
        curso) {
    public DatosSalidaGuardarTopico(Topico topico) {
        this(topico.getTitulo(),topico.getMensaje(),topico.getAutor(),topico.getCurso());
    }
}

package com.alura.foro.domain.curso.dto;

import com.alura.foro.domain.curso.Categoria;
import com.alura.foro.domain.curso.Cursos;
import com.alura.foro.domain.curso.NombreCursos;

public record GuardarSalidaCurso(
        NombreCursos nombre,
         Categoria categoria
) {


    public GuardarSalidaCurso(Cursos datos){
        this( datos.getNombre(), datos.getCategoria());
    }
}

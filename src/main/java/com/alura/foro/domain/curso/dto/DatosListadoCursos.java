package com.alura.foro.domain.curso.dto;

import com.alura.foro.domain.curso.Categoria;
import com.alura.foro.domain.curso.Cursos;
import com.alura.foro.domain.curso.NombreCursos;

public record DatosListadoCursos(Long id, NombreCursos nombre, Categoria categoria) {

    public DatosListadoCursos(Cursos cursos){
        this(cursos.getId(),cursos.getNombre(),cursos.getCategoria());
    }
}

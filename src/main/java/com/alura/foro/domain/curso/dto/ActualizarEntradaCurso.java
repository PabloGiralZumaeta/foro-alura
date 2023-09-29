package com.alura.foro.domain.curso.dto;

import com.alura.foro.domain.curso.Categoria;
import com.alura.foro.domain.curso.NombreCursos;

public record ActualizarEntradaCurso(
        NombreCursos nombre , Categoria categoria
) {

}

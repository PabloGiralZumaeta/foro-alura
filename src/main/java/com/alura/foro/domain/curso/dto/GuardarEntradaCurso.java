package com.alura.foro.domain.curso.dto;

import com.alura.foro.domain.curso.Categoria;
import com.alura.foro.domain.curso.NombreCursos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GuardarEntradaCurso(
        @NotNull NombreCursos nombre,
        @NotNull Categoria categoria
        ) {
}

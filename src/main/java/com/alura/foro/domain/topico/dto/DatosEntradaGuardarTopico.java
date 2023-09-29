package com.alura.foro.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;


public record DatosEntradaGuardarTopico(@NotBlank String titulo, @NotBlank String mensaje, Long autor, Long curso) {
}

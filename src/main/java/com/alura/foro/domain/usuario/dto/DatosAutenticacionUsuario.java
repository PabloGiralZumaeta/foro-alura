package com.alura.foro.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(@NotBlank String login, @NotBlank String clave) {
}
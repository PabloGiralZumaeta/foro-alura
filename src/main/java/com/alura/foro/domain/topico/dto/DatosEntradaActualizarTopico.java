package com.alura.foro.domain.topico.dto;

import com.alura.foro.domain.curso.dto.ActualizarEntradaCurso;
import com.alura.foro.domain.topico.EstatusTopico;

public record DatosEntradaActualizarTopico(
        String titulo, String mensaje, EstatusTopico estatus, ActualizarEntradaCurso curso
) {

}

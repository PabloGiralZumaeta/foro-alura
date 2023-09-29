package com.alura.foro.domain.respuesta.dto;

import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosListadoRespuesta(Long id, String mensaje, LocalDateTime fechaCreacion, String nombreUsuario) {


    public DatosListadoRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(),respuesta.getFechaCreacion(),respuesta.getAutor().getNombreUsuario());
    }


}

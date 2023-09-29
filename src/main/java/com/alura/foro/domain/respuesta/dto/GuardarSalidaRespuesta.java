package com.alura.foro.domain.respuesta.dto;

import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;

public record GuardarSalidaRespuesta(String mensaje, Topico topico, Usuario autor) {

    public GuardarSalidaRespuesta(Respuesta respuesta){
        this(respuesta.getMensaje(),respuesta.getTopico(),respuesta.getAutor());
    }
}

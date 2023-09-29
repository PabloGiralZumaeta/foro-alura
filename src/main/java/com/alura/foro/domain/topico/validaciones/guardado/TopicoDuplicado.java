package com.alura.foro.domain.topico.validaciones.guardado;

import com.alura.foro.domain.topico.dto.DatosEntradaGuardarTopico;
import com.alura.foro.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoDuplicado implements  ValidadorGuardado{

    @Autowired
    private TopicoRepository topicoRepository;


    public void validar(DatosEntradaGuardarTopico datos) {
        //var titulo = topicoRepository.existsByTitulo(datos.titulo());
        //var mensaje = topicoRepository.existsByMensaje(datos.mensaje());
        if(topicoRepository.existsByTituloAndMensaje(datos.titulo(),datos.mensaje())){
            throw  new ValidationException("Ya existe un topico con el mismo titulo y mensaje");
        }
    }

}

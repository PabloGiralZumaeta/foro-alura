package com.alura.foro.controller;

import com.alura.foro.domain.respuesta.dto.*;
import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.respuesta.RespuestasRepository;
import com.alura.foro.domain.topico.TopicoRepository;
import com.alura.foro.domain.usuario.UsuarioRepository;
import com.alura.foro.infra.errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestasRepository respuestasRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;



    @PostMapping("/{id}/respuesta")
    @Transactional
    @Operation(
            summary = "Guarda una respuesta en la base de datos",
            description = "",
            tags = { "Respuesta", "post" })
    public ResponseEntity guardarRespuestas(@PathVariable Long id, @RequestBody GuardarEntradaRespuesta datos){
        var topico = topicoRepository.findById(id);
        if(!topico.isPresent()){
            throw  new ValidacionDeIntegridad("Topico no encontrado");
        }

        var autor = usuarioRepository.findById(datos.autor());
        if(!autor.isPresent()){
            throw new ValidacionDeIntegridad("Autor no encontrado");
        }

        var respuesta = new Respuesta(datos.mensaje(), topico.get(), autor.get());

        var response  = new GuardarSalidaRespuesta(respuesta);


        return  ResponseEntity.ok(response);


    }

    @GetMapping("/{id}/respuesta")
    @Operation(
            summary = "Retorna una lista de todas las respuestas",
            description = "",
            tags = { "Respuesta", "get" })
    public ResponseEntity<Page<DatosListadoRespuesta>> respuestas(@PathVariable Long id, Pageable paginacion ){
        var topico = topicoRepository.findById(id);
        if(!topico.isPresent()){
            throw  new ValidacionDeIntegridad("Topico no encontrado");
        }
        Page<Respuesta> respuestas = respuestasRepository.buscarPorIdTopico(id,paginacion);

        return ResponseEntity.ok(respuestas.map(DatosListadoRespuesta::new));

    }

    @PutMapping("/{idTopico}/respuesta/{idRespuesta}")
    @Transactional
    @Operation(
            summary = "Actualiza una respuesta en la base de datos",
            description = "",
            tags = { "Respuesta", "put" })
    public ResponseEntity actualizarRespuesta(@PathVariable Long idTopico, @PathVariable Long idRespuesta, @RequestBody ActualizarEntradaRespuesta datos) {
        var topico = topicoRepository.existsById(idTopico);
        if(!topico){
            throw  new ValidacionDeIntegridad("Topico no encontrado");
        }


        Respuesta respuestaRetornada = respuestasRepository.buscarPorTopicoYRespuesta(idTopico, idRespuesta);

        if(respuestaRetornada == null){
            throw  new ValidacionDeIntegridad("Id de la respuesta inexistente");
        }

        respuestaRetornada.actualizarRespuesta(datos);

        ActualizarSalidaRespuesta response = new ActualizarSalidaRespuesta(respuestaRetornada.getId(), respuestaRetornada.getTopico().getId() ,  respuestaRetornada.getAutor().getNombreUsuario(),respuestaRetornada.getMensaje());


        return  ResponseEntity.ok(response);

    }


    @DeleteMapping("/{idTopico}/respuesta/{idRespuesta}")
    public ResponseEntity actualizarRespuesta(@PathVariable Long idTopico, @PathVariable Long idRespuesta) {
        var topico = topicoRepository.existsById(idTopico);
        if (!topico) {
            throw new ValidacionDeIntegridad("Topico no encontrado");
        }

        Respuesta respuestaRetornada = respuestasRepository.buscarPorTopicoYRespuesta(idTopico, idRespuesta);

        if (respuestaRetornada == null) {
            throw new ValidacionDeIntegridad("Id de la respuesta inexistente");
        }

        respuestasRepository.deleteById(idRespuesta);

        return ResponseEntity.noContent().build();

    }



    }

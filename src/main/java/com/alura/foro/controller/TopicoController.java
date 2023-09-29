package com.alura.foro.controller;

import com.alura.foro.domain.topico.*;
import com.alura.foro.domain.topico.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")

public class TopicoController {


    @Autowired
    private ServicioTopico service;
    @Autowired
    private  TopicoRepository repository;




    @PostMapping
    @Transactional
    @Operation(
            summary = "Guarda un topico en la base de datos",
            description = "",
            tags = { "Topico", "post" })
    public ResponseEntity<DatosSalidaGuardarTopico> GuardarTopico(@RequestBody @Valid DatosEntradaGuardarTopico datosEntradaTopico){
            var response = service.guardarTopico(datosEntradaTopico);
            return ResponseEntity.ok(response);
    }



    @GetMapping
    @Operation(
            summary = "Retorna una lista de topicos de la base de datos",
            description = "",
            tags = { "Topico", "get" })
    public ResponseEntity<Page<DatosListadoTopicos>> ObtenerTopicos(Pageable paginacion){
        return  ResponseEntity.ok(repository.findAll(paginacion).map(DatosListadoTopicos::new));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtiene un topico en la base de datos",
            description = "",
            tags = { "Topico", "get" })
    public ResponseEntity<DatosListadoTopicos> obtenerTopicoId(@PathVariable Long id){
        if(id==null){
            throw  new ValidationException("Id vacio");
        }
        var reponse = service.topicoPorId(id);
        return  ResponseEntity.ok(reponse);
    }




    @PutMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Actualiza un topico en la base de datos",
            description = "",
            tags = { "Topico", "put" })
    public ResponseEntity<DatosSalidaActualizarTopicos> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosEntradaActualizarTopico datos){

        var resultado= service.actualizarTopicos(id,datos);

        return ResponseEntity.ok(resultado);


    }


    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Elimina un topico en la base de datos",
            description = "",
            tags = { "Topico", "delete" })
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        service.elimarTopico(id);
        return  ResponseEntity.noContent().build();
    }



}

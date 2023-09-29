package com.alura.foro.controller;

import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.curso.dto.ActualizarEntradaCurso;
import com.alura.foro.domain.curso.dto.ActualizarSalidaCurso;
import com.alura.foro.domain.curso.dto.DatosListadoCursos;
import com.alura.foro.domain.curso.ServicioCurso;
import com.alura.foro.domain.curso.dto.GuardarEntradaCurso;
import com.alura.foro.infra.errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursosController {

    @Autowired
    ServicioCurso service;

    @Autowired
    CursoRepository repository;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra un curso en la base de datos",
            description = "",
            tags = { "Cursos", "post" })
    public ResponseEntity guardarCurso(@RequestBody @Valid GuardarEntradaCurso datosEntradaCurso){
         var datos = service.guardarCurso(datosEntradaCurso);
        return ResponseEntity.ok(datos);

    }


    @Operation(
            summary = "Obtiene todos los cursos en la base de datos",
            description = "",
            tags = { "Cursos", "get" })
    @GetMapping
    public ResponseEntity<Page<DatosListadoCursos>> ObtenerCursos(Pageable paginacion){
        return  ResponseEntity.ok(repository.findAll(paginacion).map(DatosListadoCursos::new));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Modifica un curso en la base de datos",
            description = "",
            tags = { "Cursos", "put" })
    public  ResponseEntity<ActualizarSalidaCurso> actualizarCurso(@PathVariable Long id, @RequestBody ActualizarEntradaCurso datos){

         var response= service.actualizarCursos(id,datos);
         return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Elimina un curso en la base de datos",
            description = "",
            tags = { "Cursos", "delete" })
    public ResponseEntity eliminarCurso(@PathVariable Long id){

        if(!repository.existsById(id)){
            throw  new ValidacionDeIntegridad("Curso no encontrado");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}

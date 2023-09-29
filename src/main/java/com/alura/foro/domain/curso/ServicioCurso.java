package com.alura.foro.domain.curso;

import com.alura.foro.domain.curso.dto.ActualizarEntradaCurso;
import com.alura.foro.domain.curso.dto.ActualizarSalidaCurso;
import com.alura.foro.domain.curso.dto.GuardarEntradaCurso;
import com.alura.foro.domain.curso.dto.GuardarSalidaCurso;
import com.alura.foro.domain.curso.validaciones.guardado.ValidadorGuardadoCurso;
import com.alura.foro.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCurso {


    @Autowired
    CursoRepository repository;


    @Autowired
    List<ValidadorGuardadoCurso> validadorGuardado;

    public GuardarSalidaCurso guardarCurso(GuardarEntradaCurso curso){

        validadorGuardado.forEach( v -> v.validar(curso));

        var cursoRetorno =  new Cursos(curso.nombre(),curso.categoria());

        repository.save(cursoRetorno);
        return  new GuardarSalidaCurso(cursoRetorno);

    }

    public ActualizarSalidaCurso actualizarCursos(Long id, ActualizarEntradaCurso datos) {

        var curso = repository.findById(id);

        if(!curso.isPresent()){
            throw  new ValidacionDeIntegridad("Curso inexistente");
        }

        curso.get().ActualizarCurso(datos);
        var response = new ActualizarSalidaCurso(curso.get());

        return response ;

    }
}

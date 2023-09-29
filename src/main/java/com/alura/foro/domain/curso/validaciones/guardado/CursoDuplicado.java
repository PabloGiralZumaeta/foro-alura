package com.alura.foro.domain.curso.validaciones.guardado;

import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.curso.dto.GuardarEntradaCurso;
import com.alura.foro.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CursoDuplicado implements ValidadorGuardadoCurso{
    @Autowired
    CursoRepository repository;
    @Override
    public void validar(GuardarEntradaCurso datos) {
        var nombre = datos.nombre();
        var categoria = datos.categoria();

        if(repository.existsByNombreAndCategoria(nombre,categoria)){
            throw new ValidacionDeIntegridad("Ya existe un curso asi");
        }
    }
}

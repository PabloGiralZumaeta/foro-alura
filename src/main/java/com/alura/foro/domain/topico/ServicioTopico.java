package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.curso.dto.ActualizarSalidaCurso;
import com.alura.foro.domain.topico.dto.*;
import com.alura.foro.domain.topico.validaciones.guardado.ValidadorGuardado;
import com.alura.foro.domain.usuario.UsuarioRepository;
import com.alura.foro.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioTopico {


    @Autowired
    private  TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidadorGuardado> validadorGuardados;

    public DatosSalidaGuardarTopico guardarTopico(DatosEntradaGuardarTopico datos){

        if(!usuarioRepository.existsById(datos.autor())){
            throw new ValidacionDeIntegridad("Autor no valido");
        }
        if(!usuarioRepository.existsById(datos.curso())){
            throw new ValidacionDeIntegridad(("Curso invalido"));
        }

        validadorGuardados.forEach(t->t.validar(datos));

        var curso = cursoRepository.findById(datos.curso()).get();
        var autor = usuarioRepository.findById(datos.autor()).get();

        var topico  = new Topico(datos.titulo(),datos.mensaje(),autor,curso);

        topicoRepository.save(topico);
        return new DatosSalidaGuardarTopico(topico);

    }


    public DatosListadoTopicos topicoPorId(Long id){
        var topico = topicoRepository.findById(id);

        if(!topico.isPresent()){
            throw  new ValidacionDeIntegridad("Id invalido");
        }
        var listado = new DatosListadoTopicos(topico.get());

        return  listado;
    }


    public DatosSalidaActualizarTopicos actualizarTopicos(Long id, DatosEntradaActualizarTopico datos){
        var topico = topicoRepository.findById(id);

        if(!topico.isPresent()){
            throw new ValidacionDeIntegridad("Topico inexistente");
        }

        /*

        if(datos.cursoEntradaActualizar().nombre()==null){
            throw new ValidadorDeIntegridad("Nombre de curso invalido");
        }

        if(datos.cursoEntradaActualizar().categoria()==null){
            throw new ValidadorDeIntegridad("Nombre de categoria invalido");
        }
*/

        var actualizar =  topico.get();

        var curso =  cursoRepository.getReferenceById(actualizar.getCurso().getId());

        curso.actualizar(datos.curso());
        //curso.setNombre(datos.cursoEntradaActualizar().nombre());
        //curso.setCategoria(datos.cursoEntradaActualizar().categoria());


        //var response= new DatosSalidaActualizarTopicos(datos.id(),datos.titulo(),datos.mensaje(), new CursoSalidaActualizar(datos.cursoEntradaActualizar().nombre(), datos.cursoEntradaActualizar().categoria()));
        var response= new DatosSalidaActualizarTopicos(datos.titulo(),datos.mensaje(), new ActualizarSalidaCurso(curso.getNombre(),curso.getCategoria()));
        actualizar.actualizarTopico(response);
        return response;
    }



    public void elimarTopico(Long id){

        if(!topicoRepository.findById(id).isPresent()){
            throw new ValidacionDeIntegridad("Nombre no encontrado");
        }
        topicoRepository.deleteById(id);


    }


}

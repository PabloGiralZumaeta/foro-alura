package com.alura.foro.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespuestasRepository extends JpaRepository<Respuesta, Long> {

    @Query("""
        select r from Respuesta r 
        join r.topico t 
        join r.autor a
        where t.id = :id
    """)
    Page<Respuesta> buscarPorIdTopico(Long id, Pageable paginacion);


    @Query("""
            select r from Respuesta r
            join  r.topico t 
            where t.id =: idTopico and
            r.id =: idRespuesta
            """)
    Respuesta buscarPorTopicoYRespuesta(Long idTopico, Long idRespuesta);

}

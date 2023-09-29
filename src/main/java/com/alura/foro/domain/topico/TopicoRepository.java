package com.alura.foro.domain.topico;

import com.alura.foro.domain.respuesta.Respuesta;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

//    Boolean existsByTitulo(String titulo);

  //  Boolean existsByMensaje(String mensaje);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

}

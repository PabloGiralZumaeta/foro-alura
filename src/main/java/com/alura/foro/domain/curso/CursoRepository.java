package com.alura.foro.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Cursos,Long> {
    Boolean existsByNombre(String nombre);

    boolean existsByCategoria(String categoria);

    boolean existsByNombreAndCategoria(NombreCursos nombre, Categoria categoria);
}

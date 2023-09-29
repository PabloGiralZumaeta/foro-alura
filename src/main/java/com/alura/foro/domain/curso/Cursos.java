package com.alura.foro.domain.curso;

import com.alura.foro.domain.curso.dto.ActualizarEntradaCurso;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "cursos")
@Entity(name = "Cursos")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    @Enumerated(EnumType.STRING)
    @Setter
    private NombreCursos nombre;
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    @Setter
    private Categoria categoria;


    public Cursos(NombreCursos nombre,Categoria categoria){
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public void  actualizar(ActualizarEntradaCurso curso) {
        if(curso.nombre()!= null){
            this.nombre = curso.nombre();
        }
        if(curso.categoria()!=null){
            this.categoria = curso.categoria();
        }
    }

    public void ActualizarCurso(ActualizarEntradaCurso datos) {
        if(datos.nombre()!=null){
            this.nombre = datos.nombre();
        }

        if(datos.categoria()!=null){
            this.categoria=datos.categoria();
        }
    }
}

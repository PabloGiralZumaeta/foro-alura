package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Cursos;
import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.topico.dto.DatosSalidaActualizarTopicos;
import com.alura.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topico")
@Entity(name = "Topicos")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "estatus")
    @Enumerated(EnumType.STRING)
    private EstatusTopico estatus = EstatusTopico.NO_SOLUCIONADO;


    /// NOTA una solucion para evitar el problema de serialización es usar un DTO poco convencional- GET controller
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Cursos curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas = new ArrayList<>();


    public Topico(String titulo, String mensaje, Usuario autor, Cursos curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarTopico(DatosSalidaActualizarTopicos datosActualizarTopicos){
        if(datosActualizarTopicos.titulo() != null){
            this.titulo = datosActualizarTopicos.titulo();
        }
        if(datosActualizarTopicos.mensaje() != null){
            this.titulo= datosActualizarTopicos.mensaje();
        }
        this.fechaCreacion = LocalDateTime.now();

    }

}

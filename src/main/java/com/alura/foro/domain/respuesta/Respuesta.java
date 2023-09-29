package com.alura.foro.domain.respuesta;

import com.alura.foro.domain.respuesta.dto.ActualizarEntradaRespuesta;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "respuesta")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mensaje")
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Column(name = "solucion")
    private Boolean solucion =false;

    public Respuesta(String mensaje, Topico topico, Usuario usuario) {
        this.mensaje = mensaje;
        this.topico = topico;
        this.autor = usuario;
    }


    public void actualizarRespuesta(ActualizarEntradaRespuesta datos){
        if(datos.mensaje() !=null){
            this.mensaje = datos.mensaje();
        }
        this.fechaCreacion = LocalDateTime.now();

    }

}

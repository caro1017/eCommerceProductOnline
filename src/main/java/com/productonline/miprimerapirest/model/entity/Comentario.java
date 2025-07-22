package com.productonline.miprimerapirest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;

    @ManyToOne
    @JoinColumn(name = "Cedula", nullable = false)
    @JsonIgnoreProperties("comentarios") // Ignorar la propiedad que puede causar ciclo
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "IdProducto", nullable = false)
    @JsonIgnoreProperties("comentarios") // Ignorar la propiedad que puede causar ciclo
    private Producto producto;

    @Column(name = "TextoComentario", nullable = false)
    private String textoComentario;

    @Column(name = "FechaComentario", nullable = false)
    private LocalDateTime fechaComentario;

}

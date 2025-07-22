package com.productonline.miprimerapirest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Favoritos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFavoritos")
    private Integer idFavoritos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cedula", referencedColumnName = "Cedula", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdProducto", referencedColumnName = "IdProducto", nullable = false)
    @JsonIgnore
    private Producto producto;

    @Column(name = "FechaAgregado", nullable = false)
    private LocalDateTime fechaAgregado;
}
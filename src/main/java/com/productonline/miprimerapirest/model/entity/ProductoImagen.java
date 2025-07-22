package com.productonline.miprimerapirest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "ProductoImagen")
public class ProductoImagen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdImagen")
    private Integer idImagen;

    @ManyToOne
    //@JoinColumn(name = "IdProducto", nullable = false)
    @JoinColumn(name = "IdProducto", referencedColumnName = "IdProducto")
    @JsonBackReference("producto-imagen")
    private Producto producto;

    @Column(name = "Url", nullable = false)
    private String url;

    @Override
    public String toString() {
        return "ProductoImagen{" +
                "idImagen=" + idImagen +
                ", url='" + url + '\'' +
                // Omite la referencia al producto para evitar la recursión infinita
                '}';
    }
}



package com.productonline.miprimerapirest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "Producto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProducto")
    private Integer idproducto;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Precio")
    private BigDecimal precio;

    @Column(name = "IdCategoria")
    private Integer idcategoria;

//    @Column(name = "Cedula")
//    private String cedula;

    @Column(name = "Referencia")
    private String referencia;

    @Column(name = "Stock")
    private Integer stock;

    @Column(name = "Talla")
    private String talla;

    @Column(name = "Marca")
    private String marca;

    @Column(name = "Genero")
    private String genero;

    @Column(name = "Color")
    private String color;

    @Column(name = "Garantia")
    private String garantia;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("producto-imagen")
    private List<ProductoImagen> imagenes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @JsonBackReference("usuario-producto")
    private Usuario usuario;

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idproducto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                // Omite las imágenes para evitar la recursión infinita
                '}';
    }
}



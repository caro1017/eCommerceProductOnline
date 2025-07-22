package com.productonline.miprimerapirest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "Usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario implements Serializable {

    @Id
    @Column(name = "Cedula")
    private String cedula;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "Mail")
    private String mail;

    @Column(name = "Password")
    private String password;

    @Column(name = "Usuario")
    private String usuario;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Nacionalidad")
    private String nacionalidad;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference("usuario-factura")
    private List<Factura> facturas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference("usuario-producto")
    private List<Producto> productos;

    @Override
    public String toString() {
        return "Usuario [cedula=" + cedula
                + ", nombre=" + nombre
                + ", apellido=" + apellido
                + ", numFacturas=" + (facturas != null ? facturas.size() : "0")
                + "]";
    }
}



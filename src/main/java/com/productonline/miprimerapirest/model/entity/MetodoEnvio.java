package com.productonline.miprimerapirest.model.entity;

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
@Table(name = "MetodoEnvio")
public class MetodoEnvio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMetodoEnvio")
    private Integer idMetodoEnvio;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Costo", nullable = false)
    private Double costo;

    @Column(name = "TiempoDias", nullable = false)
    private Integer tiempoDias; // Tiempo en días

//    // Relación con Factura (Un MetodoEnvio puede estar relacionado con varias facturas)
//    @OneToMany(mappedBy = "metodoEnvio", cascade = CascadeType.ALL)
//  @JsonManagedReference
//    private List<Factura> facturas;

//    @Override
//    public String toString() {
//        return "MetodoEnvio{" +
//                "idMetodoEnvio=" + idMetodoEnvio +
//                ", nombre='" + nombre + '\'' +
//                ", costo=" + costo +
//                ", tiempoDias=" + tiempoDias +
//                ", facturasCount=" + (facturas != null ? facturas.size() : 0) +
//                '}';
//    }
}



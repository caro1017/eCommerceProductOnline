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
@Table(name = "MetodoPago")
public class MetodoPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMetodoPago")
    private Integer idMetodoPago;

    @Column(name = "TipoPago")
    private String tipoPago;

    // Relación con Factura (Un MetodoPago puede estar relacionado con varias facturas)
//    @OneToMany(mappedBy = "metodoPago", cascade = CascadeType.ALL)
//    private List<Factura> facturas;

    // Relación con DetalleMetodoPago (Un MetodoPago puede tener varios detalles de pago)
    @OneToMany(mappedBy = "metodoPago", cascade = CascadeType.ALL)
    private List<DetalleMetodoPago> detallesMetodoPago;

    @Override
    public String toString() {
        return "MetodoPago{" +
                "idMetodoPago=" + idMetodoPago +
                ", tipoPago='" + tipoPago + '\'' +
                '}';
    }
}



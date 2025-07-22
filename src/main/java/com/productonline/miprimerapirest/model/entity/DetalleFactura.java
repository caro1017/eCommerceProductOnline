package com.productonline.miprimerapirest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DetalleFactura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // o AUTO
    @Column(name = "idDetalleFactura", length = 10)
    private Integer idDetalleFactura;

    @Column(length = 50, nullable = false)
    private String numeroPedido;

   //@Column(nullable = false)
   //private String idFactura;

    @Column(nullable = false)
    private Integer idProducto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private double precio;

    // Relación con Factura (si quieres establecerla)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFactura", referencedColumnName = "idFactura")
    @JsonIgnore
    private Factura factura;

    public void setIdFactura(String idFactura) {
    }
}

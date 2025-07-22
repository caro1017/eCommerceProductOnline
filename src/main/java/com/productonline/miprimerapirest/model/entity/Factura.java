package com.productonline.miprimerapirest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "Factura")
public class Factura implements Serializable {

    @Id
    @Column(length = 10)
    private String idFactura;

    @Column(length = 50, nullable = false)
    private String numeroPedido;

    @Column(name = "cedula")
    private String cedula;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    private String direccionEnvio;
    
   @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "IdMetodoEnvio")
  @JsonBackReference
 private MetodoEnvio metodoEnvio;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "IdMetodoPago")
   @JsonIgnore
    private MetodoPago metodoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cedula", referencedColumnName = "cedula", insertable = false, updatable = false)
    @JsonBackReference
    private Usuario usuario;

    // Constructor que genera automáticamente los campos idFactura y numeroPedido
    public Factura() {
        this.idFactura = generateRandomString(10);  // Genera un string alfanumérico de 10 caracteres
        this.numeroPedido = generateRandomString(10); // Igual para numeroPedido
        this.fecha = LocalDate.now();  // Fecha actual al crear la factura
    }

    // Método para generar strings aleatorios
    private String generateRandomString(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

}



package com.productonline.miprimerapirest.model.entity;

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
@Table(name = "Categoria")
public class Categoria implements Serializable {

    @Id
    @Column(name = "IdCategoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Column(name = "Nombre")
    private String nombreCategoria;
}



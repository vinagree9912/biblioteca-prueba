package com.samtel.prueba_ingreso.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean prestado;
    @ManyToOne
    private Usuario usuario;

}

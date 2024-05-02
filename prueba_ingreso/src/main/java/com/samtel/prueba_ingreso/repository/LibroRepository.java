package com.samtel.prueba_ingreso.repository;

import com.samtel.prueba_ingreso.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByPrestadoTrue();
    List<Libro> findByPrestadoFalse();
}
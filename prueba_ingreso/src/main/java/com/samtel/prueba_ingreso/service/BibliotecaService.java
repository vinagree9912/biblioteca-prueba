package com.samtel.prueba_ingreso.service;


import com.samtel.prueba_ingreso.entity.Libro;
import com.samtel.prueba_ingreso.entity.Usuario;
import com.samtel.prueba_ingreso.repository.BibliotecaRepository;
import com.samtel.prueba_ingreso.repository.LibroRepository;
import com.samtel.prueba_ingreso.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void agregarLibro(Libro libro) {
        libroRepository.save(libro);
    }

    public List<Libro> obtenerCatalogo() {
        return libroRepository.findAll();
    }
    public List<Libro> obtenerCatalogoPrestados() {
        return libroRepository.findByPrestadoTrue();
    }

    public List<Libro> obtenerCatalogoNoPrestados() {
        return libroRepository.findByPrestadoFalse();
    }
    public void prestarLibro(Long idLibro, Long idUsuario) {
        Libro libro = bibliotecaRepository.findById(idLibro).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        if (libro.isPrestado()) {
            throw new RuntimeException("El libro ya está prestado");
        }
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        libro.setPrestado(true);
        libro.setUsuario(usuario);
        bibliotecaRepository.save(libro);
    }
    public void devolverLibro(Long idLibro) {
        Libro libro = libroRepository.findById(idLibro).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        if (!libro.isPrestado()) {
            throw new RuntimeException("El libro no estaba prestado");
        }

        // Limpia los datos de préstamo
        libro.setPrestado(false);
        libro.setUsuario(null);

        libroRepository.save(libro);
    }
}


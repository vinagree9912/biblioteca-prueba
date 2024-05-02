package com.samtel.prueba_ingreso.controller;

import com.samtel.prueba_ingreso.entity.Libro;
import com.samtel.prueba_ingreso.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
@CrossOrigin(origins = "http://localhost:4200")
public class BibliotecaController {
    @Autowired
    private BibliotecaService bibliotecaService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarLibro(@RequestBody Libro libro) {
        try {
            bibliotecaService.agregarLibro(libro);
            return ResponseEntity.ok("Libro agregado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar el libro: " + e.getMessage());
        }
    }
    @PostMapping("/prestar/{idLibro}/{idUsuario}")
    public ResponseEntity<String> prestarLibro(@PathVariable Long idLibro, @PathVariable Long idUsuario) {
        try {
            bibliotecaService.prestarLibro(idLibro, idUsuario);
            return ResponseEntity.ok("Libro prestado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al prestar el libro: " + e.getMessage());
        }
    }
    @GetMapping("/catalogo")
    public ResponseEntity<List<Libro>> verCatalogo() {
        try {
            List<Libro> catalogo = bibliotecaService.obtenerCatalogo();
            if (catalogo.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(catalogo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/catalogo/prestados")
    public ResponseEntity<List<Libro>> verCatalogoPrestados() {
        try {
            List<Libro> catalogoPrestados = bibliotecaService.obtenerCatalogoPrestados();
            if (catalogoPrestados.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(catalogoPrestados);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/catalogo/no-prestados")
    public ResponseEntity<List<Libro>> verCatalogoNoPrestados() {
        try {
            List<Libro> catalogoNoPrestados = bibliotecaService.obtenerCatalogoNoPrestados();
            if (catalogoNoPrestados.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(catalogoNoPrestados);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/devolver/{idLibro}")
    public ResponseEntity<String> devolverLibro(@PathVariable Long idLibro) {
        try {
            bibliotecaService.devolverLibro(idLibro);
            return ResponseEntity.ok("Libro devuelto exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al devolver el libro: " + e.getMessage());
        }
    }
}
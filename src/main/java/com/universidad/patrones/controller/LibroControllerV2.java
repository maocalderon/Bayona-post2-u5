package com.universidad.patrones.controller;

import com.universidad.patrones.dto.LibroRequestDTO;
import com.universidad.patrones.dto.LibroResponseDTO;
import com.universidad.patrones.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/libros")
@Tag(name = "Libros", description = "API para gestión de libros de biblioteca")
public class LibroControllerV2 {
    
    private final LibroService libroService;
    
    public LibroControllerV2(LibroService libroService) {
        this.libroService = libroService;
    }
    
    @GetMapping
    @Operation(summary = "Obtener todos los libros", description = "Retorna una lista de todos los libros disponibles")
    public ResponseEntity<List<LibroResponseDTO>> obtenerTodosLosLibros() {
        return ResponseEntity.ok(libroService.obtenerTodosLosLibros());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener libro por ID", description = "Retorna un libro específico según su ID")
    public ResponseEntity<LibroResponseDTO> obtenerLibroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.obtenerLibroPorId(id));
    }
    
    @PostMapping
    @Operation(summary = "Crear nuevo libro", description = "Crea un nuevo libro en la biblioteca")
    public ResponseEntity<LibroResponseDTO> crearLibro(@Valid @RequestBody LibroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libroService.crearLibro(dto));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar libro", description = "Actualiza los datos de un libro existente")
    public ResponseEntity<LibroResponseDTO> actualizarLibro(
            @PathVariable Long id, 
            @Valid @RequestBody LibroRequestDTO dto) {
        return ResponseEntity.ok(libroService.actualizarLibro(id, dto));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar libro", description = "Elimina un libro de la biblioteca")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}
package com.universidad.patrones.mapper;

import com.universidad.patrones.dto.LibroRequestDTO;
import com.universidad.patrones.dto.LibroResponseDTO;
import com.universidad.patrones.model.Libro;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {
    
    public Libro toEntity(LibroRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setAnio(dto.getAnio());
        libro.setCategoria(dto.getCategoria());
        return libro;
    }
    
    public LibroResponseDTO toDTO(Libro libro) {
        if (libro == null) {
            return null;
        }
        return new LibroResponseDTO(
            libro.getId(),
            libro.getTitulo(),
            libro.getAutor(),
            libro.getAnio(),
            libro.getCategoria()
        );
    }
    
    public void updateEntityFromDTO(LibroRequestDTO dto, Libro libro) {
        if (dto != null && libro != null) {
            libro.setTitulo(dto.getTitulo());
            libro.setAutor(dto.getAutor());
            libro.setAnio(dto.getAnio());
            libro.setCategoria(dto.getCategoria());
        }
    }
}
package com.universidad.patrones.service;

import com.universidad.patrones.dto.LibroRequestDTO;
import com.universidad.patrones.dto.LibroResponseDTO;
import com.universidad.patrones.exception.LibroNotFoundException;
import com.universidad.patrones.mapper.LibroMapper;
import com.universidad.patrones.model.Libro;
import com.universidad.patrones.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    
    private final LibroRepository libroRepository;
    private final LibroMapper mapper;
    
    public LibroService(LibroRepository libroRepository, LibroMapper mapper) {
        this.libroRepository = libroRepository;
        this.mapper = mapper;
    }
    
    public List<LibroResponseDTO> obtenerTodosLosLibros() {
        return libroRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
    
    public LibroResponseDTO obtenerLibroPorId(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException("Libro no encontrado con ID: " + id));
        return mapper.toDTO(libro);
    }
    
    public LibroResponseDTO crearLibro(LibroRequestDTO dto) {
        Libro libro = mapper.toEntity(dto);
        libro = libroRepository.save(libro);
        return mapper.toDTO(libro);
    }
    
    public LibroResponseDTO actualizarLibro(Long id, LibroRequestDTO dto) {
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException("Libro no encontrado con ID: " + id));
        mapper.updateEntityFromDTO(dto, libroExistente);
        libroExistente = libroRepository.save(libroExistente);
        return mapper.toDTO(libroExistente);
    }
    
    public void eliminarLibro(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNotFoundException("Libro no encontrado con ID: " + id);
        }
        libroRepository.deleteById(id);
    }
}
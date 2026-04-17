package com.universidad.patrones.exception;

public class LibroNotFoundException extends RuntimeException {
    
    public LibroNotFoundException(String message) {
        super(message);
    }
}
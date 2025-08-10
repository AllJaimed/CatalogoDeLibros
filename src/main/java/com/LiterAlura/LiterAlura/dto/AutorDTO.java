package com.LiterAlura.LiterAlura.dto;

import com.LiterAlura.LiterAlura.model.Libro;
import jakarta.persistence.OneToMany;

import java.util.List;

public record AutorDTO(Long id,
                       String nombre,
                       int anhoNacimiento,
                       int anhoFallecimiento) {
}

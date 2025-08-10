package com.LiterAlura.LiterAlura.dto;

import com.LiterAlura.LiterAlura.model.Autor;

public record LibroDTO(Long id,
                       String titulo,
                       String idioma,
                       int descargas) {
}

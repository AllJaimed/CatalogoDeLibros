package com.LiterAlura.LiterAlura.repository;

import com.LiterAlura.LiterAlura.model.Autor;
import com.LiterAlura.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByIdExternoEquals(Long idBuscar);
    @Query("SELECT l FROM Libro l WHERE l.idioma =:idioma")
    Optional<Libro> findLibrosIdiomas(@Param("idioma") String idioma);

}

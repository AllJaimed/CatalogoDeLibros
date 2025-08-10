package com.LiterAlura.LiterAlura.repository;

import com.LiterAlura.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);


    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libro")
    List<Autor> findAllWithLibros();

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libro WHERE a.anhoNacimiento <= :anho AND (a.anhoFallecimiento IS NULL OR a.anhoFallecimiento >= :anho)")
    List<Autor> findAutoresVivos(@Param("anho") Integer anho);

}

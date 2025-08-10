package com.LiterAlura.LiterAlura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idExterno;
    private String titulo;
    private String idioma;
    private int descargas;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.idExterno = datosLibro.id();
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.lenguajes().get(0);
        this.descargas = datosLibro.descargas();
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "*-".repeat(20) +
                "\nLibro\n" +
                "*-".repeat(20) +
                "\nTitulo: " + titulo + "\n" +
                "Autor: " + autor.getNombre() +"\n"+
                "Idioma: " + idioma + "\n" +
                "Descargas: " + descargas;
    }
}



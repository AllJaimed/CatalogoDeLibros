package com.LiterAlura.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private int anhoNacimiento;
    private int anhoFallecimiento;
    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Libro> libro;

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.anhoNacimiento = datosAutor.annoNacimiento();
        this.anhoFallecimiento = datosAutor.annoFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnhoNacimiento() {
        return anhoNacimiento;
    }

    public void setAnhoNacimiento(int anhoNacimiento) {
        this.anhoNacimiento = anhoNacimiento;
    }

    public int getAnhoFallecimiento() {
        return anhoFallecimiento;
    }

    public void setAnhoFallecimiento(int anhoFallecimiento) {
        this.anhoFallecimiento = anhoFallecimiento;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "\nAutor: " + nombre +
                "\nFecha de nacimiento: " + anhoNacimiento +
                "\nFecha de fallecimiento: " + anhoFallecimiento +
                "\nLibros: " + libro.stream().map(Libro::getTitulo).toList().toString();
    }
}

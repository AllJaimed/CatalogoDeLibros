package com.LiterAlura.LiterAlura.principal;

import com.LiterAlura.LiterAlura.model.*;
import com.LiterAlura.LiterAlura.repository.AutorRepository;
import com.LiterAlura.LiterAlura.repository.LibroRepository;
import com.LiterAlura.LiterAlura.service.Conexion;
import com.LiterAlura.LiterAlura.service.ConvertirDatos;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private Conexion conector = new Conexion();
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    private final String URL_BUSQUEDA = "https://gutendex.com/books/";
    private LibroRepository repositorio;
    private AutorRepository autorRepository;
    private List<Libro> listaLibros;
    private List<Autor> listaAutores;


    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepository = autorRepository;
    }

    public void menu(){

        System.out.println("*-".repeat(55));
        System.out.println("\nBienvenidos a LiterAlura\n");
        System.out.println("*-".repeat(55));

        int i = -1;

        while(i != 0){
            System.out.println("""
                Ingrese el número de la opción:
                
                1. Buscar libro por titulo
                2. Listar libros registrados
                3. Listar autores registrados
                4. Listar autores vivos en un determinado año
                5. Listar libros por idioma
                
                0. Salir
                
                """);
            i = teclado.nextInt();
            teclado.nextLine();
            switch (i){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listaAutoresVivosPorAnho();
                    break;
                case 5:
                    listarIdiomas();
                    break;
                case 0:
                    i = 0;
                    break;
            }
        }

    }

    private DatosLibro getDatosLibros() {

        System.out.println("Por favor ingrese un libro");
        String busquedaLibro = teclado.nextLine().strip();
        var json = conector.conexionAPI(URL_BUSQUEDA + "?search=" + busquedaLibro.replace(" ", "%20").toLowerCase());
        var datosLibrosBusqueda = convertirDatos.obtenerDAtos(json, Datos.class);

        if (datosLibrosBusqueda.librosEncontrados() != 0){
            System.out.println("Libro encontrado!");
            DatosLibro datosLibro = datosLibrosBusqueda.listaLibros().stream()
                    .sorted(Comparator.comparing(DatosLibro::descargas).reversed())
                    .limit(1)
                    .findFirst()
                    .orElse(null);


            Optional<Libro> libroBuscado = repositorio.findByIdExternoEquals(datosLibro.id());

            if (libroBuscado.isPresent()){
                System.out.println("Libro ya registrado!");
                return null;
            } else {
                return datosLibro;
            }

        } else {
            System.out.println("No se encontro el libro");
            return null;
        }
    }

    private void buscarLibro() {
        // Se obtiene la información del libro desde una fuente externa (API, archivo, etc.)
        DatosLibro datosLibro = getDatosLibros();

        // Verifica que se haya recibido un libro válido
        if (datosLibro != null) {

            // Se crea una nueva instancia de Libro usando los datos obtenidos
            Libro libro = new Libro(datosLibro);

            // Toma el primer autor de la lista de autores del libro
            // Si no hay autores disponibles, se crea uno por defecto con el nombre "Autor Desconocido"
            DatosAutor datosAutor = datosLibro.autor().stream()
                    .findFirst()
                    .orElse(new DatosAutor("Autor Desconocido", null, null));

            // Se intenta buscar un autor en la base de datos que ya tenga ese nombre
            // Si no existe, se crea uno nuevo y se guarda en la base de datos
            Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                    .orElseGet(() -> {
                        // Si no se encuentra, se crea una nueva instancia con los datos del autor
                        Autor nuevoAutor = new Autor(datosAutor);
                        // Y se guarda en la base de datos
                        return autorRepository.save(nuevoAutor);
                    });

            // Se establece el autor del libro (ya sea existente o recién creado)
            libro.setAutor(autor);

            // Se guarda el libro en la base de datos
            repositorio.save(libro);

            // Se imprime en consola el libro que se guardó, útil para depuración o verificación
            System.out.println(libro);
        }
    }

    private void listarLibrosRegistrados() {

        listaLibros = repositorio.findAll();

        listaLibros.stream()
                .forEach(System.out::println);
    }

    private void listarAutores() {
        listaAutores = autorRepository.findAllWithLibros();
        listaAutores.stream()
                .forEach(System.out::println);
    }

    private void listaAutoresVivosPorAnho() {
        System.out.println("Por favor ingrese un año: ");
        var anhoIngresado = teclado.nextInt();
        teclado.nextLine();
        listaAutores = autorRepository.findAutoresVivos(anhoIngresado);

        if (listaAutores.isEmpty() ){
            System.out.println("Ningún autor encontrado que haya estado vivo en el año " + anhoIngresado);
        } else {
            listaAutores.stream()
                    .forEach(System.out::println);
        }
    }

    public void listarIdiomas(){
        System.out.println("""
                es- Español
                en- Ingles
                fr- Frances
                pt- Portugal
                """);
        var opcionIdioma = teclado.nextLine().substring(0, 2).toLowerCase();
        System.out.println(opcionIdioma);
        Optional<Libro> librosEncontrados = repositorio.findLibrosIdiomas(opcionIdioma);
        if (librosEncontrados.isPresent()){
            librosEncontrados.stream()
                    .forEach(System.out::println);
        } else {
            System.out.println("No se encontraton libros");
        }
    }
}

package com.distribuida.test;

import com.distribuida.entities.Libro;
import com.distribuida.entities.Categoria;
import com.distribuida.entities.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {

    private Libro libro;
    private Categoria categoria;
    private Autor autor;
    private Date fechaPublicacion;

    @BeforeEach
    public void setup() {
        categoria = new Categoria(1, "Tecnología", "Libros sobre tecnología");
        autor = new Autor(1, "Paul", "Silvana", "Ecuador", "Av. de los Andes", "0947538635", "pauli@ejemplo.com");
        fechaPublicacion = new Date();

        libro = new Libro(1, "Java Básico", "McGraw-Hill", 350, "3ra", "Español", fechaPublicacion,
                "Introducción al lenguaje Java", "Tapa dura", "1234567890123", 10, "portada.jpg",
                "Presentación física", 25.50, categoria, autor);
    }

    @Test
    public void testLibroConstructorAndGetters() {
        assertAll("Validar datos Libro, Constructor y Getters",
                () -> assertEquals(1, libro.getIdLibro()),
                () -> assertEquals("Java Básico", libro.getTitulo()),
                () -> assertEquals("McGraw-Hill", libro.getEditorial()),
                () -> assertEquals(350, libro.getNumPaginas()),
                () -> assertEquals("3ra", libro.getEdicion()),
                () -> assertEquals("Español", libro.getIdioma()),
                () -> assertEquals(fechaPublicacion, libro.getFechaPublicacion()),
                () -> assertEquals("Introducción al lenguaje Java", libro.getDescripcion()),
                () -> assertEquals("Tapa dura", libro.getTipoPasta()),
                () -> assertEquals("1234567890123", libro.getISBN()),
                () -> assertEquals(10, libro.getNumEjemplares()),
                () -> assertEquals("portada.jpg", libro.getPortada()),
                () -> assertEquals("Presentación física", libro.getPresentacion()),
                () -> assertEquals(25.50, libro.getPrecio()),
                () -> assertEquals(categoria, libro.getCategoria()),
                () -> assertEquals(autor, libro.getAutor())
        );
    }

    @Test
    public void testLibroSetters() {
        Date nuevaFecha = new Date();

        libro.setIdLibro(2);
        libro.setTitulo("Python Avanzado");
        libro.setEditorial("Pearson");
        libro.setNumPaginas(500);
        libro.setEdicion("2da");
        libro.setIdioma("Inglés");
        libro.setFechaPublicacion(nuevaFecha);
        libro.setDescripcion("Guía avanzada de Python");
        libro.setTipoPasta("Tapa blanda");
        libro.setISBN("9876543210987");
        libro.setNumEjemplares(20);
        libro.setPortada("nueva_portada.jpg");
        libro.setPresentacion("Presentación digital");
        libro.setPrecio(30.75);

        Categoria nuevaCategoria = new Categoria(2, "Ciencia", "Libros de ciencia");
        Autor nuevoAutor = new Autor(2, "Ana", "Perez", "Perú", "Calle Lima", "0981234567", "ana@ejemplo.com");

        libro.setCategoria(nuevaCategoria);
        libro.setAutor(nuevoAutor);

        assertAll("Validar datos Libro, Setters",
                () -> assertEquals(2, libro.getIdLibro()),
                () -> assertEquals("Python Avanzado", libro.getTitulo()),
                () -> assertEquals("Pearson", libro.getEditorial()),
                () -> assertEquals(500, libro.getNumPaginas()),
                () -> assertEquals("2da", libro.getEdicion()),
                () -> assertEquals("Inglés", libro.getIdioma()),
                () -> assertEquals(nuevaFecha, libro.getFechaPublicacion()),
                () -> assertEquals("Guía avanzada de Python", libro.getDescripcion()),
                () -> assertEquals("Tapa blanda", libro.getTipoPasta()),
                () -> assertEquals("9876543210987", libro.getISBN()),
                () -> assertEquals(20, libro.getNumEjemplares()),
                () -> assertEquals("nueva_portada.jpg", libro.getPortada()),
                () -> assertEquals("Presentación digital", libro.getPresentacion()),
                () -> assertEquals(30.75, libro.getPrecio()),
                () -> assertEquals(nuevaCategoria, libro.getCategoria()),
                () -> assertEquals(nuevoAutor, libro.getAutor())
        );
    }

    @Test
    public void testLibroToString() {
        String str = libro.toString();
        assertAll("Validar Datos Libro - ToString",
                () -> assertTrue(str.contains("Java Básico")),
                () -> assertTrue(str.contains("McGraw-Hill")),
                () -> assertTrue(str.contains("3ra")),
                () -> assertTrue(str.contains("Español")),
                () -> assertTrue(str.contains("Introducción al lenguaje Java")),
                () -> assertTrue(str.contains("Tapa dura")),
                () -> assertTrue(str.contains("1234567890123")),
                () -> assertTrue(str.contains("portada.jpg")),
                () -> assertTrue(str.contains("Presentación física")),
                () -> assertTrue(str.contains("25.5")),
                () -> assertTrue(str.contains("Tecnología")),
                () -> assertTrue(str.contains("Paul"))
        );
    }
}

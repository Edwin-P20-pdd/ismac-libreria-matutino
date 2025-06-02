package com.distribuida.test;

import com.distribuida.entities.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTest {

    private Categoria categoria;

    @BeforeEach
    public void setup() {
        categoria = new Categoria(100, "Tecnología", "Libros relacionados con tecnología y computación.");
    }

    @Test
    public void testCategoriaConstructorAndGetters() {

        assertAll("Validar datos Categoria, Constructor y Getters",
                () -> assertEquals(100, categoria.getIdCategoria()),
                () -> assertEquals("Tecnología", categoria.getCategoria()),
                () -> assertEquals("Libros relacionados con tecnología y computación.", categoria.getDescripcion())
        );
    }

    @Test
    public void testCategoriaSetters() {

        categoria.setIdCategoria(200);
        categoria.setCategoria("Ciencia");
        categoria.setDescripcion("Libros científicos y académicos.");

        assertAll("Validar datos Categoria, Setters",
                () -> assertEquals(200, categoria.getIdCategoria()),
                () -> assertEquals("Ciencia", categoria.getCategoria()),
                () -> assertEquals("Libros científicos y académicos.", categoria.getDescripcion())
        );
    }

    @Test
    public void testCategoriaToString() {
        String str = categoria.toString();
        assertAll("Validar Datos Categoria - ToString",
                () -> assertTrue(str.contains("100")),
                () -> assertTrue(str.contains("Tecnología")),
                () -> assertTrue(str.contains("Libros relacionados con tecnología y computación."))
        );
    }
}

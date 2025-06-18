package com.distribuida.test;

import com.distribuida.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaDetalleTest {

    private FacturaDetalle facturaDetalle;
    private Libro libro;
    private Factura factura;
    private Autor autor;

    @BeforeEach
    public void setup() {
        Categoria categoria = new Categoria(1, "Programación", "Libros sobre programación");
        autor = new Autor(1, "Carlos", "Vera", "Chile", "Calle 123", "099112233", "carlos@ejemplo.com");

        libro = new Libro(1, "Java Intermedio", "Alfaomega", 250, "1ra", "Español", new Date(),
                "Libro intermedio de Java", "Tapa dura", "1111111111111", 5, "portada.jpg",
                "Física", 45.0, categoria, autor);

        factura = new Factura(1, "FAC-001", new Date(), 90.0, 12.0, 100.8, new Cliente());

        facturaDetalle = new FacturaDetalle(1, 2, 90.0, libro, factura, autor);
    }

    @Test
    public void testFacturaDetalleConstructorAndGetters() {
        assertAll("Validar datos FacturaDetalle - Constructor y Getters",
                () -> assertEquals(1, facturaDetalle.getIdFacturaDetalle()),
                () -> assertEquals(2, facturaDetalle.getCantidad()),
                () -> assertEquals(90.0, facturaDetalle.getSubtotal()),
                () -> assertEquals(libro, facturaDetalle.getLibro()),
                () -> assertEquals(factura, facturaDetalle.getFactura()),
                () -> assertEquals(autor, facturaDetalle.getAutor())
        );
    }

    @Test
    public void testFacturaDetalleSetters() {
        Libro nuevoLibro = new Libro(2, "Python Básico", "Pearson", 300, "2da", "Inglés", new Date(),
                "Libro de Python", "Tapa blanda", "2222222222222", 8, "python.jpg", "Digital", 50.0,
                new Categoria(2, "Ciencia", "Libros científicos"), new Autor(2, "Ana", "López", "Perú", "Av. Lima", "098998877", "ana@correo.com"));

        Factura nuevaFactura = new Factura(2, "FAC-002", new Date(), 100.0, 12.0, 112.0, new Cliente());
        Autor nuevoAutor = new Autor(3, "David", "Reyes", "México", "Calle Reforma", "097655443", "david@correo.com");

        facturaDetalle.setIdFacturaDetalle(3);
        facturaDetalle.setCantidad(3);
        facturaDetalle.setSubtotal(150.0);
        facturaDetalle.setLibro(nuevoLibro);
        facturaDetalle.setFactura(nuevaFactura);
        facturaDetalle.setAutor(nuevoAutor);

        assertAll("Validar datos FacturaDetalle - Setters",
                () -> assertEquals(3, facturaDetalle.getIdFacturaDetalle()),
                () -> assertEquals(3, facturaDetalle.getCantidad()),
                () -> assertEquals(150.0, facturaDetalle.getSubtotal()),
                () -> assertEquals(nuevoLibro, facturaDetalle.getLibro()),
                () -> assertEquals(nuevaFactura, facturaDetalle.getFactura()),
                () -> assertEquals(nuevoAutor, facturaDetalle.getAutor())
        );
    }

    @Test
    public void testFacturaDetalleToString() {
        String str = facturaDetalle.toString();
        assertAll("Validar FacturaDetalle - ToString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("2")),
                () -> assertTrue(str.contains("90.0")),
                () -> assertTrue(str.contains("Java Intermedio")),
                () -> assertTrue(str.contains("Carlos")),
                () -> assertTrue(str.contains("carlos@ejemplo.com"))
        );
    }
}

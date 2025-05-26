package com.distribuida.test;

import com.distribuida.entities.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutorTest {

    private Autor autor;

    @BeforeEach
    public  void setup(){
      autor = new Autor(0001, "Paul", "Silvana", "Ecuador", "Av. de los Andes", "0947538635", "pauli@ejemplo.com");
    }

    @Test
    public void TestAutorConstructorAndGetters(){

        assertAll("Validar datos Autor, Constructor, y Getters",
                () -> assertEquals(0001, autor.getIdAutor()),
                () -> assertEquals("Paul", autor.getNombre()),
                () -> assertEquals("Silvana", autor.getApellido()),
                () -> assertEquals("Ecuador", autor.getPais()),
                () -> assertEquals("Av. de los Andes", autor.getDireccion()),
                () -> assertEquals("0947538635", autor.getTelefono()),
                () -> assertEquals("pauli@ejemplo.com", autor.getCorreo())

        );
    }

    @Test
    public void testAutorSetters(){

        autor.setIdAutor(2);
        autor.setNombre("Paulina");
        autor.setApellido("Silva");
        autor.setPais("Colombia");
        autor.setDireccion("Av. Siempre Viva");
        autor.setTelefono("0987654321");
        autor.setCorreo("paulina@ejemplo.com");

        assertAll("Validar datos Autor, Setters",
                () -> assertEquals(2, autor.getIdAutor()),
                () -> assertEquals("Paulina", autor.getNombre()),
                () -> assertEquals("Silva", autor.getApellido()),
                () -> assertEquals("Colombia", autor.getPais()),
                () -> assertEquals("Av. Siempre Viva", autor.getDireccion()),
                () -> assertEquals("0987654321", autor.getTelefono()),
                () -> assertEquals("paulina@ejemplo.com", autor.getCorreo())
        );
    }

    @Test
    public void testAutorToString(){
        String str = autor.toString();
        assertAll("Validar Datos Autor - ToString",
                () -> assertTrue(str.contains("0001")),
                () -> assertTrue(str.contains("Paul")),
                () -> assertTrue(str.contains("Silvana")),
                () -> assertTrue(str.contains("Ecuador")),
                () -> assertTrue(str.contains("Av. de los Andes")),
                () -> assertTrue(str.contains("0947538635")),
                () -> assertTrue(str.contains("pauli@ejemplo.com"))
        );
    }


}

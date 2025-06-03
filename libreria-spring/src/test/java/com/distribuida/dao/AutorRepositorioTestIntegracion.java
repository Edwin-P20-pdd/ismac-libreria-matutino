package com.distribuida.dao;

import com.distribuida.model.Autor;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class AutorRepositorioTestIntegracion {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void findAll() {
        List<Autor> autores = autorRepository.findAll();
        assertNotNull(autores);
        assertTrue(autores.size() >0);
        for (Autor item : autores) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Autor> autor = autorRepository.findById(1); // ajusta el ID si es necesario
        assertTrue(autor.isPresent(), "El autor con id = 1 debería existir.");
        System.out.println(autor.toString());
    }

    @Test
    public void save() {
        Autor autor = new Autor(0, "Laura", "Mendez", "Chile", "Calle Sur", "0999999999", "laura@ejemplo.com");
        autorRepository.save(autor);
        assertNotNull(autor.getIdAutor(), "El autor guardado debe tener un ID.");
        assertEquals("Laura", autor.getNombre());
        assertEquals("Mendez", autor.getApellido());
    }

    @Test
    public void update() {
        Optional<Autor> autor = autorRepository.findById(54); // cambia el ID según tu DB

        assertTrue(autor.isPresent(), "El autor con id = 54 debe existir para ser actualizado.");

        autor.orElse(null).setNombre("Laura Actualizada");
        autor.orElse(null).setApellido("Mendez Modificada");
        autor.orElse(null).setPais("Perú");
        autor.orElse(null).setDireccion("Nueva dirección");
        autor.orElse(null).setTelefono("0123456789");
        autor.orElse(null).setCorreo("nuevo@ejemplo.com");

        Autor autorActualizado = autorRepository.save(autor.orElse(null));

        assertEquals("Laura Actualizada", autorActualizado.getNombre());
        assertEquals("Mendez Modificada", autorActualizado.getApellido());
    }

    @Test
    public void delete() {
        int id = 54; // cambia el ID según la DB
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
        }
        assertFalse(autorRepository.existsById(id), "El autor con id = " + id + " debería haberse eliminado.");
    }
}

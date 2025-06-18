package com.distribuida.dao;

import com.distribuida.model.Categoria;
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
public class CategoriaRepositorioTestIntegracion {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void findAll(){
        List<Categoria> categorias = categoriaRepository.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size() >=0);
        for (Categoria item: categorias){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Categoria> categoria = categoriaRepository.findById(10); // Cambiar por un ID existente
        assertTrue(categoria.isPresent(), "La categoría con id = 10 debería existir");
        System.out.println(categoria.toString());
    }

    @Test
    public void save() {
        Categoria categoria = new Categoria(0, "Ciencia Ficción", "Libros de ciencia ficción y tecnología futurista.");
        categoriaRepository.save(categoria);
        assertNotNull(categoria.getIdCategoria(), "La categoría guardada debe tener un id.");
        assertEquals("Ciencia Ficción", categoria.getCategoria());
        assertEquals("Libros de ciencia ficción y tecnología futurista.", categoria.getDescripcion());
    }

    @Test
    public void update() {
        Optional<Categoria> categoria = categoriaRepository.findById(58); // Cambiar por un ID existente

        assertTrue(categoria.isPresent(), "La categoría con id = 58 debe existir para ser actualizada.");

        categoria.orElse(null).setCategoria("Terror");
        categoria.orElse(null).setDescripcion("Libros del género de terror y suspenso.");

        Categoria categoriaActualizada = categoriaRepository.save(categoria.orElse(null));

        assertEquals("Terror", categoriaActualizada.getCategoria());
        assertEquals("Libros del género de terror y suspenso.", categoriaActualizada.getDescripcion());
    }

    @Test
    public void delete() {
        if (categoriaRepository.existsById(58)) { // Cambiar por un ID existente
            categoriaRepository.deleteById(58);
        }
        assertFalse(categoriaRepository.existsById(58), "El id = 58 debería haberse eliminado.");
    }

}

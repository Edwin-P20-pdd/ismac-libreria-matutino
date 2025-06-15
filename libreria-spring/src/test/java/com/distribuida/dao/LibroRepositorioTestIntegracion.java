package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class LibroRepositorioTestIntegracion {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void findAll() {
        List<Libro> libros = libroRepository.findAll();
        assertNotNull(libros);
        assertTrue(libros.size() >= 0);
        for (Libro item : libros) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Libro> libro = libroRepository.findById(101);
        assertTrue(libro.isPresent(), "El libro con id = 101 debería existir");
        System.out.println(libro.toString());
    }

    @Test
    public void save() {
        Optional<Categoria> categoria = categoriaRepository.findById(1);
        Optional<Autor> autor = autorRepository.findById(1);

        Libro libro = new Libro(
                0, "El Principito", "Editorial Planeta", 120, "3ra", "Español", new Date(), "Un clásico de la literatura", "Dura", "978-1234567890", 10, "portada.jpg", "Tapa dura con ilustraciones", 15.99, categoria.orElse(null), autor.orElse(null)
        );

        libroRepository.save(libro);

        assertNotNull(libro.getIdLibro(), "El libro guardado debe tener un ID.");
        assertEquals("El Principito", libro.getTitulo());
    }

    @Test
    public void update() {
        Optional<Libro> libroExistente = libroRepository.findById(101);
        Optional<Categoria> categoria = categoriaRepository.findById(2);
        Optional<Autor> autor = autorRepository.findById(2);

        assertTrue(libroExistente.isPresent(), "El libro con id = 101 debe existir para ser actualizado.");

        libroExistente.orElse(null).setTitulo("Cien Años de Soledad");
        libroExistente.orElse(null).setEditorial("Editorial Sudamericana");
        libroExistente.orElse(null).setNumPaginas(432);
        libroExistente.orElse(null).setEdicion("5ta");
        libroExistente.orElse(null).setIdioma("Español");
        libroExistente.orElse(null).setFechaPublicacion(new Date());
        libroExistente.orElse(null).setDescripcion("Obra maestra de Gabriel García Márquez");
        libroExistente.orElse(null).setTipoPasta("Blanda");
        libroExistente.orElse(null).setISBN("978-9876543210");
        libroExistente.orElse(null).setNumEjemplares(20);
        libroExistente.orElse(null).setPortada("cien_anos.jpg");
        libroExistente.orElse(null).setPresentacion("Edición ilustrada");
        libroExistente.orElse(null).setPrecio(29.99);
        libroExistente.orElse(null).setCategoria(categoria.orElse(null));
        libroExistente.orElse(null).setAutor(autor.orElse(null));

        Libro libroActualizado = libroRepository.save(libroExistente.orElse(null));

        assertEquals("Cien Años de Soledad", libroActualizado.getTitulo());
    }

    @Test
    public void delete() {
        if (libroRepository.existsById(101)) {
            libroRepository.deleteById(101);
            Optional<Libro> libroEliminado = libroRepository.findById(101);
            assertFalse(libroEliminado.isPresent(), "El libro con id = 101 debería haber sido eliminado");
        }
    }
}

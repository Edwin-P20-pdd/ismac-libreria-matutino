package com.distribuida.controller;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LibroControllerTestUnitaria {

    @InjectMocks
    private LibroController libroController;

    @Mock
    private LibroService libroService;

    private Libro libro;
    private Autor autor;
    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        autor = new Autor();
        autor.setIdAutor(1);
        autor.setNombre("Mario");
        autor.setApellido("Vargas Llosa");
        autor.setPais("Perú");
        autor.setDireccion("Av. del Escritor");
        autor.setTelefono("099112233");
        autor.setCorreo("mario@correo.com");

        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoria("Novela");
        categoria.setDescripcion("Narrativa extensa");

        libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("La ciudad y los perros");
        libro.setEditorial("Alfaguara");
        libro.setNumPaginas(400);
        libro.setEdicion("Primera");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("Una novela sobre la vida militar.");
        libro.setTipoPasta("Dura");
        libro.setISBN("978-3-16-148410-0");
        libro.setNumEjemplares(100);
        libro.setPortada("portada.jpg");
        libro.setPresentacion("Edición especial");
        libro.setPrecio(25.99);
        libro.setAutor(autor);
        libro.setCategoria(categoria);
    }

    @Test
    public void testFindAll() {
        when(libroService.findAll()).thenReturn(List.of(libro));
        ResponseEntity<List<Libro>> respuesta = libroController.findAll();
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(libroService, times(1)).findAll();
    }

    @Test
    public void testFindOneExistente() {
        when(libroService.findOne(1)).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("La ciudad y los perros", respuesta.getBody().getTitulo());
    }

    @Test
    public void testFindOneNoExistente() {
        when(libroService.findOne(2)).thenReturn(null);
        ResponseEntity<Libro> respuesta = libroController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(libroService.save(any(Libro.class))).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.save(libro);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("La ciudad y los perros", respuesta.getBody().getTitulo());
    }

    @Test
    public void testUpdateExistente() {
        when(libroService.update(eq(1), eq(1), eq(1), any(Libro.class))).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.update(1, 1, 1, libro); // id, idAutor, idCategoria
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente() {
        when(libroService.update(eq(2), eq(2), eq(2), any(Libro.class))).thenReturn(null);
        ResponseEntity<Libro> respuesta = libroController.update(2, 2, 2, libro);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        doNothing().when(libroService).delete(1);
        ResponseEntity<Void> respuesta = libroController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(libroService, times(1)).delete(1);
    }
}

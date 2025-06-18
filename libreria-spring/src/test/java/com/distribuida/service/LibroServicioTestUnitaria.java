package com.distribuida.service;

import com.distribuida.dao.AutorRepository;
import com.distribuida.dao.CategoriaRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibroServicioTestUnitaria {

    @Mock
    private LibroRepository libroRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    private Libro libro;
    private Categoria categoria;
    private Autor autor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        categoria = new Categoria(1, "Ficción", "Narraciones imaginarias");
        autor = new Autor(1, "Gabriel", "García Márquez", "Colombia", "Aracataca", "0991234567", "gabriel@correo.com");

        libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("Cien Años de Soledad");
        libro.setEditorial("Sudamericana");
        libro.setNumPaginas(471);
        libro.setEdicion("1ra");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("Novela del realismo mágico");
        libro.setTipoPasta("Dura");
        libro.setISBN("978-3-16-148410-0");
        libro.setNumEjemplares(10);
        libro.setPortada("portada.jpg");
        libro.setPresentacion("Tapa dura con ilustraciones");
        libro.setPrecio(29.99);
        libro.setCategoria(categoria);
        libro.setAutor(autor);
    }

    @Test
    public void testFindAll() {
        when(libroRepository.findAll()).thenReturn(Arrays.asList(libro));
        List<Libro> libros = libroService.findAll();
        assertNotNull(libros);
        assertEquals(1, libros.size());
        verify(libroRepository, times(1)).findAll();
    }

    @Test
    public void testFindOne() {
        when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        Libro libro1 = libroService.findOne(1);
        assertNotNull(libro1);
        assertEquals("Cien Años de Soledad", libro1.getTitulo());
        verify(libroRepository, times(1)).findById(1);
    }

    @Test
    public void testSave() {
        when(libroRepository.save(libro)).thenReturn(libro);
        Libro libro1 = libroService.save(libro);
        assertNotNull(libro1);
        assertEquals("Cien Años de Soledad", libro1.getTitulo());
        verify(libroRepository, times(1)).save(libro);
    }

    @Test
    public void testUpdate() {
        Libro libroActualizado = new Libro();
        libroActualizado.setTitulo("El amor en los tiempos del cólera");
        libroActualizado.setEditorial("Sudamericana");
        libroActualizado.setNumPaginas(400);
        libroActualizado.setEdicion("2da");
        libroActualizado.setIdioma("Español");
        libroActualizado.setFechaPublicacion(new Date());
        libroActualizado.setDescripcion("Otra gran novela de Gabo");
        libroActualizado.setTipoPasta("Blanda");
        libroActualizado.setISBN("978-0-14-243723-0");
        libroActualizado.setNumEjemplares(15);
        libroActualizado.setPortada("portada2.jpg");
        libroActualizado.setPresentacion("Tapa blanda con solapas");
        libroActualizado.setPrecio(34.99);
        libroActualizado.setCategoria(categoria);
        libroActualizado.setAutor(autor);

        when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        when(libroRepository.save(any(Libro.class))).thenReturn(libroActualizado);
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));

        Libro libro1 = libroService.update(1, 1, 1, libroActualizado);
        assertNotNull(libro1);
        assertEquals("El amor en los tiempos del cólera", libro1.getTitulo());
        verify(libroRepository).save(any(Libro.class));
    }

    @Test
    public void testDelete() {
        when(libroRepository.existsById(1)).thenReturn(false);
        libroService.delete(1);
        verify(libroRepository, times(0)).deleteById(1);
    }
}

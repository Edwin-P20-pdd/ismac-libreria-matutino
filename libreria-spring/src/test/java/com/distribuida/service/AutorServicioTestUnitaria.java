package com.distribuida.service;

import com.distribuida.dao.AutorRepository;
import com.distribuida.model.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutorServicioTestUnitaria {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorServiceImpl autorService;

    private Autor autor;

    @BeforeEach
    public void setUp() {
        autor = new Autor();
        autor.setIdAutor(1);
        autor.setNombre("Gabriel");
        autor.setApellido("García Márquez");
        autor.setPais("Colombia");
        autor.setDireccion("Aracataca");
        autor.setTelefono("0991234567");
        autor.setCorreo("gabriel@ejemplo.com");
    }

    @Test
    public void testFindAll() {
        when(autorRepository.findAll()).thenReturn(List.of(autor));
        List<Autor> autores = autorService.findAll();
        assertNotNull(autores);
        assertEquals(1, autores.size());
        verify(autorRepository, times(1)).findAll();
    }

    @Test
    public void testFindOneExistente() {
        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        Autor resultado = autorService.findOne(1);
        assertNotNull(resultado);
        assertEquals("Gabriel", resultado.getNombre());
    }

    @Test
    public void testFindOneNoExistente() {
        when(autorRepository.findById(2)).thenReturn(Optional.empty());
        Autor autorResultado = autorService.findOne(2);
        assertNull(autorResultado);
    }

    @Test
    public void testSave() {
        when(autorRepository.save(autor)).thenReturn(autor);
        Autor autorResultado = autorService.save(autor);
        assertNotNull(autorResultado);
        assertEquals("Gabriel", autorResultado.getNombre());
    }

    @Test
    public void testUpdateExistente() {
        Autor autorActualizado = new Autor();
        autorActualizado.setNombre("Mario");
        autorActualizado.setApellido("Vargas Llosa");
        autorActualizado.setPais("Perú");
        autorActualizado.setDireccion("Lima");
        autorActualizado.setTelefono("0987654321");
        autorActualizado.setCorreo("mario@ejemplo.com");

        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        when(autorRepository.save(any())).thenReturn(autorActualizado);

        Autor autorResultado = autorService.update(1, autorActualizado);
        assertNotNull(autorResultado);
        assertEquals("Mario", autorResultado.getNombre());
        verify(autorRepository, times(1)).save(autor);
    }

    @Test
    public void testUpdateNoExistente() {
        Autor nuevo = new Autor();
        when(autorRepository.findById(2)).thenReturn(Optional.empty());
        Autor autorResultado = autorService.update(2, nuevo);
        assertNull(autorResultado);
        verify(autorRepository, never()).save(any());
    }

    @Test
    public void testDeleteExistente() {
        when(autorRepository.existsById(1)).thenReturn(true);
        autorService.delete(1);
        verify(autorRepository).deleteById(1);
    }

    @Test
    public void testDeleteNoExistente() {
        when(autorRepository.existsById(2)).thenReturn(false);
        autorService.delete(2);
        verify(autorRepository, never()).deleteById(anyInt());
    }
}

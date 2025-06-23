package com.distribuida.controller;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("removal")
@WebMvcTest(LibroController.class)
public class LibroControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindAll() throws Exception {
        Categoria categoria = new Categoria(1, "Novela", "Narrativa extensa");
        Autor autor = new Autor(1, "Gabriel", "García Márquez", "Colombia", "Calle Real", "0999999999", "gabriel@correo.com");

        Libro libro = new Libro(1, "Cien Años de Soledad", "Sudamericana", 471, "Primera", "Español",
                new Date(), "Obra maestra del realismo mágico", "Dura", "978-3-16-148410-0",
                10, "portada.jpg", "Caja", 29.99, categoria, autor);

        Mockito.when(libroService.findAll()).thenReturn(List.of(libro));

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Cien Años de Soledad"));
    }

    @Test
    public void testSave() throws Exception {
        Categoria categoria = new Categoria(1, "Novela", "Narrativa extensa");
        Autor autor = new Autor(1, "Gabriel", "García Márquez", "Colombia", "Calle Real", "0999999999", "gabriel@correo.com");

        Libro libro = new Libro(0, "Cien Años de Soledad", "Sudamericana", 471, "Primera", "Español",
                new Date(), "Obra maestra del realismo mágico", "Dura", "978-3-16-148410-0",
                10, "portada.jpg", "Caja", 29.99, categoria, autor);

        Mockito.when(libroService.save(any(Libro.class))).thenReturn(libro);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Cien Años de Soledad"));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/libros/1"))
                .andExpect(status().isNoContent());
    }
}

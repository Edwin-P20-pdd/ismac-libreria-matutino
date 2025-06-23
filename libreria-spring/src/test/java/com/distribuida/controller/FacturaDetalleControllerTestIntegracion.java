package com.distribuida.controller;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import com.distribuida.service.FacturaDetalleService;
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
@WebMvcTest(FacturaDetalleController.class)
public class FacturaDetalleControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacturaDetalleService facturaDetalleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindAll() throws Exception {
        Categoria categoria = new Categoria(1, "Novela", "Narrativa extensa");
        Autor autor = new Autor(1, "Gabriel", "García Márquez", "Colombia", "Calle Real", "0999999999", "gabriel@correo.com");
        Libro libro = new Libro(1, "Cien Años de Soledad", "Sudamericana", 471, "Primera", "Español",
                new Date(), "Realismo mágico", "Dura", "978-3-16-148410-0", 10, "portada.jpg", "Caja", 29.99, categoria, autor);
        Cliente cliente = new Cliente(1, "17425346547", "Juan", "Taipe", "Av. por ahi", "09346547423", "juant@correo.com");
        Factura factura = new Factura(1, "F-001", new Date(), 100.0, 12.0, 112.0, cliente);

        FacturaDetalle detalle = new FacturaDetalle(1, 2, 59.98, libro, factura);

        Mockito.when(facturaDetalleService.findAll()).thenReturn(List.of(detalle));

        mockMvc.perform(get("/api/facturas-detalle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cantidad").value(2));
    }

    @Test
    public void testSave() throws Exception {
        Categoria categoria = new Categoria(1, "Novela", "Narrativa extensa");
        Autor autor = new Autor(1, "Gabriel", "García Márquez", "Colombia", "Calle Real", "0999999999", "gabriel@correo.com");
        Libro libro = new Libro(1, "Cien Años de Soledad", "Sudamericana", 471, "Primera", "Español",
                new Date(), "Realismo mágico", "Dura", "978-3-16-148410-0", 10, "portada.jpg", "Caja", 29.99, categoria, autor);
        Cliente cliente = new Cliente(1, "17425346547", "Juan", "Taipe", "Av. por ahi", "09346547423", "juant@correo.com");
        Factura factura = new Factura(1, "F-001", new Date(), 100.0, 12.0, 112.0, cliente);

        FacturaDetalle detalle = new FacturaDetalle(0, 2, 59.98, libro, factura);

        Mockito.when(facturaDetalleService.save(any(FacturaDetalle.class))).thenReturn(detalle);

        mockMvc.perform(post("/api/facturas-detalle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detalle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(2));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/facturas-detalle/1"))
                .andExpect(status().isNoContent());
    }
}

package com.distribuida.controller;

import com.distribuida.model.Carrito;
import com.distribuida.service.CarritoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarritoGuestController.class)
public class CarritoGuestControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService carritoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Carrito crearCarritoMock() {
        Carrito carrito = new Carrito();
        carrito.setIdCarrito(1L);
        carrito.setToken("abc123");
        carrito.setSubtotal(BigDecimal.valueOf(100));
        carrito.setDescuento(BigDecimal.ZERO);
        carrito.setImpuestos(BigDecimal.valueOf(12));
        carrito.setTotal(BigDecimal.valueOf(112));
        carrito.setActualizadoEn(LocalDateTime.now());
        return carrito;
    }

    @Test
    public void testCreateOrGet() throws Exception {
        Carrito carrito = crearCarritoMock();

        Mockito.when(carritoService.getOrCreateByToken("abc123")).thenReturn(carrito);

        mockMvc.perform(post("/api/guest/cart")
                        .param("token", "abc123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("abc123"))
                .andExpect(jsonPath("$.total").value(112));
    }

    @Test
    public void testGet() throws Exception {
        Carrito carrito = crearCarritoMock();

        Mockito.when(carritoService.getByToken("abc123")).thenReturn(carrito);

        mockMvc.perform(get("/api/guest/cart")
                        .param("token", "abc123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subtotal").value(100));
    }

    @Test
    public void testAddItem() throws Exception {
        Carrito carrito = crearCarritoMock();

        Map<String, Integer> body = new HashMap<>();
        body.put("libroId", 10);
        body.put("cantidad", 2);

        Mockito.when(carritoService.addItem(eq("abc123"), eq(10), eq(2))).thenReturn(carrito);

        mockMvc.perform(post("/api/guest/cart/items")
                        .param("token", "abc123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("abc123"));
    }

    @Test
    public void testUpdateItem() throws Exception {
        Carrito carrito = crearCarritoMock();

        Map<String, Integer> body = new HashMap<>();
        body.put("cantidad", 3);

        Mockito.when(carritoService.updateItemCantidad(eq("abc123"), eq(5L), eq(3))).thenReturn(carrito);

        mockMvc.perform(put("/api/guest/cart/items/{id}", 5L)
                        .param("token", "abc123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(112));
    }

    @Test
    public void testRemoveItem() throws Exception {
        Mockito.doNothing().when(carritoService).removeItem("abc123", 5L);

        mockMvc.perform(delete("/api/guest/cart/items/{id}", 5L)
                        .param("token", "abc123"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testClearCart() throws Exception {
        Mockito.doNothing().when(carritoService).clearByToken("abc123");

        mockMvc.perform(delete("/api/guest/cart/clear")
                        .param("token", "abc123"))
                .andExpect(status().isNoContent());
    }
}

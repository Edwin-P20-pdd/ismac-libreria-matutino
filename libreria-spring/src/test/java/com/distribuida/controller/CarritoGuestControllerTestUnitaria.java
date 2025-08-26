package com.distribuida.controller;

import com.distribuida.model.Carrito;
import com.distribuida.service.CarritoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarritoGuestControllerTestUnitaria {

    @InjectMocks
    private CarritoGuestController carritoGuestController;

    @Mock
    private CarritoService carritoService;

    private Carrito carrito;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        carrito = new Carrito();
        carrito.setIdCarrito(1L);
        carrito.setToken("abc123");
        carrito.setSubtotal(BigDecimal.valueOf(100));
        carrito.setDescuento(BigDecimal.ZERO);
        carrito.setImpuestos(BigDecimal.valueOf(12));
        carrito.setTotal(BigDecimal.valueOf(112));
        carrito.setActualizadoEn(LocalDateTime.now());
    }

    @Test
    public void testCreateOrGet() {
        when(carritoService.getOrCreateByToken("abc123")).thenReturn(carrito);

        ResponseEntity<Carrito> respuesta = carritoGuestController.createOrGet("abc123");

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("abc123", respuesta.getBody().getToken());
        verify(carritoService, times(1)).getOrCreateByToken("abc123");
    }

    @Test
    public void testGet() {
        when(carritoService.getByToken("abc123")).thenReturn(carrito);

        ResponseEntity<Carrito> respuesta = carritoGuestController.get("abc123");

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(BigDecimal.valueOf(112), respuesta.getBody().getTotal());
        verify(carritoService, times(1)).getByToken("abc123");
    }

    @Test
    public void testAddItem() {
        Map<String, Integer> body = new HashMap<>();
        body.put("libroId", 10);
        body.put("cantidad", 2);

        when(carritoService.addItem("abc123", 10, 2)).thenReturn(carrito);

        ResponseEntity<Carrito> respuesta = carritoGuestController.addItem("abc123", body);

        assertEquals(200, respuesta.getStatusCodeValue());
        verify(carritoService, times(1)).addItem("abc123", 10, 2);
    }

    @Test
    public void testUpdate() {
        Map<String, Integer> body = new HashMap<>();
        body.put("cantidad", 3);

        when(carritoService.updateItemCantidad("abc123", 5L, 3)).thenReturn(carrito);

        ResponseEntity<Carrito> respuesta = carritoGuestController.update("abc123", 5L, body);

        assertEquals(200, respuesta.getStatusCodeValue());
        verify(carritoService, times(1)).updateItemCantidad("abc123", 5L, 3);
    }

    @Test
    public void testRemove() {
        doNothing().when(carritoService).removeItem("abc123", 5L);

        ResponseEntity<Void> respuesta = carritoGuestController.remove("abc123", 5L);

        assertEquals(204, respuesta.getStatusCodeValue());
        verify(carritoService, times(1)).removeItem("abc123", 5L);
    }

    @Test
    public void testClear() {
        doNothing().when(carritoService).clearByToken("abc123");

        ResponseEntity<Void> respuesta = carritoGuestController.clear("abc123");

        assertEquals(204, respuesta.getStatusCodeValue());
        verify(carritoService, times(1)).clearByToken("abc123");
    }
}

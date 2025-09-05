package com.distribuida.controller;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import com.distribuida.service.FacturaService;
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

public class FacturaControllerTestUnitaria {

    @InjectMocks
    private FacturaController facturaController;

    @Mock
    private FacturaService facturaService;

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNombre("Pedro");
        cliente.setApellido("Sanchez");
        cliente.setCedula("0102030405");
        cliente.setDireccion("Av. Principal");
        cliente.setTelefono("0991234567");
        cliente.setCorreo("pedro@correo.com");

        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("F-001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.0);
        factura.setIva(12.0);
        factura.setTotal(112.0);
        factura.setCliente(cliente);
    }

    @Test
    public void testFindAll() {
        when(facturaService.findAll()).thenReturn(List.of(factura));
        ResponseEntity<List<Factura>> respuesta = facturaController.findAll();
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(facturaService, times(1)).findAll();
    }

    @Test
    public void testFindOneExistente() {
        when(facturaService.findOne(1)).thenReturn(factura);
        ResponseEntity<Factura> respuesta = facturaController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("F-001", respuesta.getBody().getNumFactura());
    }

    @Test
    public void testFindOneNoExistente() {
        when(facturaService.findOne(2)).thenReturn(null);
        ResponseEntity<Factura> respuesta = facturaController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(facturaService.save(any(Factura.class))).thenReturn(factura);
        ResponseEntity<Factura> respuesta = facturaController.save(factura);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("F-001", respuesta.getBody().getNumFactura());
    }

    @Test
    public void testUpdateExistente() {
        when(facturaService.update(eq(1), any(Factura.class))).thenReturn(factura);
        ResponseEntity<Factura> respuesta = facturaController.update(1, 1, factura); // Asumiendo que update(id, idCliente, factura)
        assertEquals(200, respuesta.getStatusCodeValue());
    }


    @Test
    public void testUpdateNoExistente() {
        when(facturaService.update(eq(2), any(Factura.class))).thenReturn(null);
        ResponseEntity<Factura> respuesta = facturaController.update(2, 2, factura);
        assertEquals(404, respuesta.getStatusCodeValue());
    }


    @Test
    public void testDelete() {
        doNothing().when(facturaService).delete(1);
        ResponseEntity<Void> respuesta = facturaController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(facturaService, times(1)).delete(1);
    }
}

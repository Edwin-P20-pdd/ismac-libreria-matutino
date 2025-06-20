package com.distribuida.controller;

import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import com.distribuida.service.FacturaDetalleService;
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

public class FacturaDetalleControllerTestUnitaria {

    @InjectMocks
    private FacturaDetalleController facturaDetalleController;

    @Mock
    private FacturaDetalleService facturaDetalleService;

    private FacturaDetalle facturaDetalle;
    private Libro libro;
    private Factura factura;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("Cien Años de Soledad");

        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("F-001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.0);
        factura.setIva(12.0);
        factura.setTotal(112.0);

        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(2);
        facturaDetalle.setSubtotal(50.0);
        facturaDetalle.setLibro(libro);
        facturaDetalle.setFactura(factura);
    }

    @Test
    public void testFindAll() {
        when(facturaDetalleService.findAll()).thenReturn(List.of(facturaDetalle));
        ResponseEntity<List<FacturaDetalle>> respuesta = facturaDetalleController.findAll();
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(facturaDetalleService, times(1)).findAll();
    }

    @Test
    public void testFindOneExistente() {
        when(facturaDetalleService.findOne(1)).thenReturn(facturaDetalle);
        ResponseEntity<FacturaDetalle> respuesta = facturaDetalleController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(2, respuesta.getBody().getCantidad());
    }

    @Test
    public void testFindOneNoExistente() {
        when(facturaDetalleService.findOne(2)).thenReturn(null);
        ResponseEntity<FacturaDetalle> respuesta = facturaDetalleController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(facturaDetalleService.save(any(FacturaDetalle.class))).thenReturn(facturaDetalle);
        ResponseEntity<FacturaDetalle> respuesta = facturaDetalleController.save(facturaDetalle);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(2, respuesta.getBody().getCantidad());
    }

    @Test
    public void testUpdateExistente() {
        when(facturaDetalleService.update(eq(1), eq(1), eq(1), any(FacturaDetalle.class))).thenReturn(facturaDetalle);
        ResponseEntity<FacturaDetalle> respuesta = facturaDetalleController.update(1, 1, 1, facturaDetalle); // id, idLibro, idFactura
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente() {
        when(facturaDetalleService.update(eq(2), eq(2), eq(2), any(FacturaDetalle.class))).thenReturn(null);
        ResponseEntity<FacturaDetalle> respuesta = facturaDetalleController.update(2, 2, 2, facturaDetalle);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        doNothing().when(facturaDetalleService).delete(1);
        ResponseEntity<Void> respuesta = facturaDetalleController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(facturaDetalleService, times(1)).delete(1);
    }
}

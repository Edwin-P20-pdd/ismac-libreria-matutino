package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleRepository;
import com.distribuida.dao.FacturaRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
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
public class FacturaDetalleServicioTestUnitaria {

    @Mock
    private FacturaDetalleRepository facturaDetalleRepository;

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private FacturaDetalleServiceImpl facturaDetalleService;

    private FacturaDetalle facturaDetalle;
    private Factura factura;
    private Libro libro;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("Libro de prueba");

        factura = new Factura(1, "FAC-001", new Date(), 50.00, 10.00, 60.00, null);

        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(2);
        facturaDetalle.setSubtotal(100.00);
        facturaDetalle.setLibro(libro);
        facturaDetalle.setFactura(factura);
    }

    @Test
    public void testFindAll() {
        when(facturaDetalleRepository.findAll()).thenReturn(Arrays.asList(facturaDetalle));
        List<FacturaDetalle> detalles = facturaDetalleService.findAll();
        assertNotNull(detalles);
        assertEquals(1, detalles.size());
        verify(facturaDetalleRepository, times(1)).findAll();
    }

    @Test
    public void testFindOne() {
        when(facturaDetalleRepository.findById(1)).thenReturn(Optional.of(facturaDetalle));
        FacturaDetalle detalle = facturaDetalleService.findOne(1);
        assertNotNull(detalle);
        assertEquals(2, detalle.getCantidad());
        verify(facturaDetalleRepository, times(1)).findById(1);
    }

    @Test
    public void testSave() {
        when(facturaDetalleRepository.save(facturaDetalle)).thenReturn(facturaDetalle);
        FacturaDetalle detalle = facturaDetalleService.save(facturaDetalle);
        assertNotNull(detalle);
        assertEquals(100.00, detalle.getSubtotal());
        verify(facturaDetalleRepository, times(1)).save(facturaDetalle);
    }

    @Test
    public void testUpdate() {
        FacturaDetalle detalleActualizado = new FacturaDetalle();
        detalleActualizado.setCantidad(3);
        detalleActualizado.setSubtotal(150.00);
        detalleActualizado.setLibro(libro);
        detalleActualizado.setFactura(factura);

        when(facturaDetalleRepository.findById(1)).thenReturn(Optional.of(facturaDetalle));
        when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));
        when(facturaDetalleRepository.save(any(FacturaDetalle.class))).thenReturn(detalleActualizado);

        FacturaDetalle resultado = facturaDetalleService.update(1, detalleActualizado);
        assertNotNull(resultado);
        assertEquals(3, resultado.getCantidad());
        assertEquals(150.00, resultado.getSubtotal());
        verify(facturaDetalleRepository).save(any(FacturaDetalle.class));
    }

    @Test
    public void testDelete() {
        when(facturaDetalleRepository.existsById(1)).thenReturn(false);
        facturaDetalleService.delete(1);
        verify(facturaDetalleRepository, times(0)).deleteById(1);
    }
}

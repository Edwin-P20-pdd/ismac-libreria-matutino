package com.distribuida.dao;

import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Factura;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class FacturaDetalleRepositorioTestIntegracion {

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Test
    public void findAll() {
        List<FacturaDetalle> detalles = facturaDetalleRepository.findAll();
        assertNotNull(detalles);
        assertTrue(detalles.size() >= 0);
        for (FacturaDetalle item : detalles) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<FacturaDetalle> detalle = facturaDetalleRepository.findById(51);
        assertTrue(detalle.isPresent(), "El detalle con id = 51 debería existir");
        System.out.println(detalle.toString());
    }

    @Test
    public void save() {
        Optional<Factura> factura = facturaRepository.findById(81);
        Optional<Libro> libro = libroRepository.findById(101);

        FacturaDetalle detalle = new FacturaDetalle();
        detalle.setCantidad(2);
        detalle.setSubtotal(30.00);
        detalle.setFactura(factura.orElse(null));
        detalle.setLibro(libro.orElse(null));

        facturaDetalleRepository.save(detalle);

        assertNotNull(detalle.getIdFacturaDetalle(), "El detalle guardado debe tener un ID.");
        assertEquals(2, detalle.getCantidad());
    }

    @Test
    public void update() {
        Optional<FacturaDetalle> detalleExistente = facturaDetalleRepository.findById(51);
        Optional<Factura> factura = facturaRepository.findById(82);
        Optional<Libro> libro = libroRepository.findById(102);

        assertTrue(detalleExistente.isPresent(), "El detalle con id = 51 debe existir para ser actualizado.");

        detalleExistente.orElse(null).setCantidad(4);
        detalleExistente.orElse(null).setSubtotal(60.00);
        detalleExistente.orElse(null).setFactura(factura.orElse(null));
        detalleExistente.orElse(null).setLibro(libro.orElse(null));

        FacturaDetalle detalleActualizado = facturaDetalleRepository.save(detalleExistente.orElse(null));

        assertEquals(4, detalleActualizado.getCantidad());
    }

    @Test
    public void delete() {
        if (facturaDetalleRepository.existsById(51)) {
            facturaDetalleRepository.deleteById(51);
            Optional<FacturaDetalle> detalleEliminado = facturaDetalleRepository.findById(51);
            assertFalse(detalleEliminado.isPresent(), "El detalle con id = 51 debería haber sido eliminado");
        }
    }
}

package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleRepository;
import com.distribuida.dao.FacturaRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Factura;
import com.distribuida.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaDetalleServiceImpl implements FacturaDetalleService {

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<FacturaDetalle> findAll() {
        return facturaDetalleRepository.findAll();
    }

    @Override
    public FacturaDetalle findOne(int id) {
        Optional<FacturaDetalle> detalle = facturaDetalleRepository.findById(id);
        return detalle.orElse(null);
    }

    @Override
    public FacturaDetalle save(FacturaDetalle detalle) {
        return facturaDetalleRepository.save(detalle);
    }

    @Override
    public FacturaDetalle update(int id, FacturaDetalle detalle) {
        FacturaDetalle detalleExistente = findOne(id);
//        Optional<Libro> libro = libroRepository.findById(idLibro);
//        Optional<Factura> factura = facturaRepository.findById(idFactura);

        if (detalleExistente == null) {
            return null;
        }

        detalleExistente.setCantidad(detalle.getCantidad());
        detalleExistente.setSubtotal(detalle.getSubtotal());
//        detalleExistente.setLibro(libro.orElse(null));
//        detalleExistente.setFactura(factura.orElse(null));

        return facturaDetalleRepository.save(detalleExistente);
    }

    @Override
    public void delete(int id) {
        if (facturaDetalleRepository.existsById(id)) {
            facturaDetalleRepository.deleteById(id);
        }
    }
}

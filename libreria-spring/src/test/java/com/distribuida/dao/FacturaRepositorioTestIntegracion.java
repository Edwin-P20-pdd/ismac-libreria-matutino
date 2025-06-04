package com.distribuida.dao;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import jakarta.transaction.Transactional;
import org.aspectj.apache.bcel.classfile.Module;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class FacturaRepositorioTestIntegracion {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void findAll(){
        List<Factura> facturas = facturaRepository.findAll();
        assertNotNull(facturas);
        assertTrue(facturas.size() >=0);
        for (Factura item: facturas){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Factura> factura = facturaRepository.findById(82);
        assertTrue(factura.isPresent(), "La factura con id = 82, debería existir");
        System.out.println(factura.toString());
    }

    @Test
    public void  save(){
        Optional<Cliente> cliente = clienteRepository.findById(10);
        Factura factura = new Factura(0, "FAC-090", new Date(), 150.00, 50.00, 200.00, cliente.orElse(null));
        facturaRepository.save(factura);
        assertNotNull(factura.getIdFactura(), "La factura guardada debe tener un id.");
        assertEquals("FAC-090", factura.getNumFactura());
    }

    @Test
    public void update(){
        Optional<Factura> facturaExistente = facturaRepository.findById(82);

        Optional<Cliente> cliente = clienteRepository.findById(10);
        assertTrue(facturaExistente.isPresent(), "La factura con id = 82, debe existir para ser actualizada.");

        facturaExistente.orElse(null).setNumFactura("FAC-082");
        facturaExistente.orElse(null).setFecha(new Date());
        facturaExistente.orElse(null).setTotalNeto(200.00);
        facturaExistente.orElse(null).setIva(50.00);
        facturaExistente.orElse(null).setTotal(250.00);
        facturaExistente.orElse(null).setCliente(cliente.orElse(null));

        facturaRepository.save(facturaExistente.orElse(null));

    }


    @Test
    public void delete(){
        if (facturaRepository.existsById(82)){
            facturaRepository.deleteById(82);
        }
    }

}

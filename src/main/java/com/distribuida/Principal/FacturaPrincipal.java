package com.distribuida.Principal;

import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;

import java.util.Date;

public class FacturaPrincipal {

    public static void main(String[] args) {

        Factura factura = new Factura();

        Cliente cliente = new Cliente(1,"1747846987", "Juan", "Caiza", "Av. de las americas", "0946467894", "ejemplo@gmail.com");

        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
            // inyección de dependencias
        factura.setCliente(cliente);

        System.out.println(factura.toString());

    }

}

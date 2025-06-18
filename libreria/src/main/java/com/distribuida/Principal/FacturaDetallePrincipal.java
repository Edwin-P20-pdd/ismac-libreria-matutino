package com.distribuida.Principal;

import com.distribuida.entities.Autor;
import com.distribuida.entities.Factura;
import com.distribuida.entities.FacturaDetalle;
import com.distribuida.entities.Libro;

public class FacturaDetallePrincipal {

    public static void main(String[] args) {

        FacturaDetalle facturaDetalle = new FacturaDetalle();
        Libro libro = new Libro();
        Factura factura = new Factura();
        Autor autor = new Autor();

        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(65);
        facturaDetalle.setSubtotal(650.5);
        // inyección de dependencias
        facturaDetalle.setLibro(libro);
        facturaDetalle.setFactura(factura);
        facturaDetalle.setAutor(autor);

        System.out.println(facturaDetalle.toString());

    }

}

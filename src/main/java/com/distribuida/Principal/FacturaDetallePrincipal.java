package com.distribuida.Principal;

import com.distribuida.entities.FacturaDetalle;

public class FacturaDetallePrincipal {

    public static void main(String[] args) {

        FacturaDetalle facturaDetalle = new FacturaDetalle();

        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(65);
        facturaDetalle.setSubtotal(650);
        facturaDetalle.setFactura(1);
        facturaDetalle.setAutor(1);

    }

}

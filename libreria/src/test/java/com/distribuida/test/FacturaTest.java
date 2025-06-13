package com.distribuida.test;


import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaTest {

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setup() throws Exception {
        // Crear Cliente
        cliente = new Cliente(1, "1736548953", "Juan", "Guarnizo", "Av. Cualquiera", "094576952861", "ejemplo@gmail.com");

        // Crear Factura con datos fijos
        factura = new Factura(1, "F-001", new Date(), 100.0, 12.0, 112.0, cliente);
    }

    @Test
    public void testFacturaConstructorAndGetters() {
        assertAll("Validar datos Factura, Constructor y Getters",
                () -> assertEquals(1, factura.getIdFactura()),
                () -> assertEquals("F-001", factura.getNumFactura()),
                //() -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(100.0, factura.getTotalNeto()),
                () -> assertEquals(12.0, factura.getIva()),
                () -> assertEquals(112.0, factura.getTotal()),
                () -> assertEquals("Juan", factura.getCliente().getNombre())
        );
    }

    @Test
    public void testFacturaSetters() throws Exception {

        factura.setIdFactura(2);
        factura.setNumFactura("F-002");
        //factura.setFecha(new Date());
        factura.setTotalNeto(200.0);
        factura.setIva(24.0);
        factura.setTotal(224.0);

        Cliente nuevoCliente = new Cliente(2, "1234567890", "Ana", "Pérez", "Av. Siempre Viva", "0987654321", "ana@example.com");
        factura.setCliente(nuevoCliente);

        assertAll("Validar datos Factura, Setters",
                () -> assertEquals(2, factura.getIdFactura()),
                () -> assertEquals("F-002", factura.getNumFactura()),
                //() -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(200.0, factura.getTotalNeto()),
                () -> assertEquals(24.0, factura.getIva()),
                () -> assertEquals(224.0, factura.getTotal()),
                () -> assertEquals(nuevoCliente, factura.getCliente())
        );
    }

    @Test
    public void testFacturaToString() {
        String str = factura.toString();
        assertAll("Validar toString de Factura",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("F-001")),
                //() -> assertTrue(str.contains("new Date()")),
                () -> assertTrue(str.contains("100.0")),
                () -> assertTrue(str.contains("12.0")),
                () -> assertTrue(str.contains("112.0")),
                () -> assertTrue(str.contains("Juan")),
                () -> assertTrue(str.contains("ejemplo@gmail.com"))
        );
    }
}

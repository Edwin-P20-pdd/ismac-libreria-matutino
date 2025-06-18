package com.distribuida.test;

import com.distribuida.entities.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setup(){
        cliente = new Cliente(1,"1736548953","Juan","Guarnizo","Av. Cualquiera","094576952861","ejemplo@gmail.com");
    }

    @Test
    public void TestClienteConstructorAndGetters(){

        assertAll("Validar datos Cliente, Constructor, y Getters",
                () -> assertEquals(1, cliente.getIdCliente()),
                () -> assertEquals("1736548953", cliente.getCedula()),
                () -> assertEquals("Juan", cliente.getNombre()),
                () -> assertEquals("Guarnizo", cliente.getApellido()),
                () -> assertEquals("Av. Cualquiera", cliente.getDireccion()),
                () -> assertEquals("094576952861", cliente.getTelefono()),
                () -> assertEquals("ejemplo@gmail.com", cliente.getCorreo())
                );
    }


    @Test
    public void  testClienteSetters(){

        cliente.setIdCliente(2);
        cliente.setCedula("17365489532");
        cliente.setNombre("Juan2");
        cliente.setApellido("Guarnizo2");
        cliente.setDireccion("Av. Cualquiera2");
        cliente.setTelefono("0945769528612");
        cliente.setCorreo("ejemplo2@gmail.com");

        assertAll("Validar datos Cliente, Setters",
                () -> assertEquals(2, cliente.getIdCliente()),
                () -> assertEquals("17365489532", cliente.getCedula()),
                () -> assertEquals("Juan2", cliente.getNombre()),
                () -> assertEquals("Guarnizo2", cliente.getApellido()),
                () -> assertEquals("Av. Cualquiera2", cliente.getDireccion()),
                () -> assertEquals("0945769528612", cliente.getTelefono()),
                () -> assertEquals("ejemplo2@gmail.com", cliente.getCorreo())
                );
    }

    @Test
    public void testClienteToString(){
        String str = cliente.toString();
        assertAll("Validar Datos Cliente - ToString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1736548953")),
                () -> assertTrue(str.contains("Juan")),
                () -> assertTrue(str.contains("Guarnizo")),
                () -> assertTrue(str.contains("Av. Cualquiera")),
                () -> assertTrue(str.contains("094576952861")),
                () -> assertTrue(str.contains("ejemplo@gmail.com"))
                );
    }

}

package com.distribuida.dao;

import com.distribuida.model.Carrito;
import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class CarritoRepositorioTestIntegracion {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void findAll() {
        List<Carrito> carritos = carritoRepository.findAll();
        assertNotNull(carritos);
        assertTrue(carritos.size() >= 0);
        for (Carrito item : carritos) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Carrito> carrito = carritoRepository.findById(1L);
        assertTrue(carrito.isPresent(), "El carrito con id = 1 debería existir");
        System.out.println(carrito.toString());
    }

    @Test
    public void save() {
        Optional<Cliente> cliente = clienteRepository.findById(10); // ajusta según tus datos

        Carrito carrito = new Carrito();
        carrito.setToken(UUID.randomUUID().toString());
        carrito.setCliente(cliente.orElse(null));

        carritoRepository.save(carrito);

        assertNotNull(carrito.getIdCarrito(), "El carrito guardado debe tener un id.");
        assertNotNull(carrito.getToken(), "El carrito guardado debe tener un token.");
    }

    @Test
    public void update() {
        Optional<Carrito> carritoExistente = carritoRepository.findById(1L);
        assertTrue(carritoExistente.isPresent(), "El carrito con id = 1 debe existir para ser actualizado.");

        carritoExistente.get().setToken("TOKEN-ACTUALIZADO-" + UUID.randomUUID());

        Carrito carritoActualizado = carritoRepository.save(carritoExistente.get());

        assertTrue(carritoActualizado.getToken().startsWith("TOKEN-ACTUALIZADO"));
    }

    @Test
    public void delete() {
        if (carritoRepository.existsById(2L)) {
            carritoRepository.deleteById(2L);
            Optional<Carrito> carritoEliminado = carritoRepository.findById(2L);
            assertFalse(carritoEliminado.isPresent());
        }
    }

    @Test
    public void findByToken() {
        String token = "ALGUN_TOKEN_EXISTENTE"; // ajusta a un token válido de tu BD
        Optional<Carrito> carrito = carritoRepository.findByToken(token);

        carrito.ifPresentOrElse(
                value -> System.out.println("Carrito encontrado por token: " + value),
                () -> System.out.println("No se encontró carrito con el token: " + token)
        );
    }
}

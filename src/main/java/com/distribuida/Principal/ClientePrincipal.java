package com.distribuida.Principal;

import com.distribuida.entities.Cliente;

public class ClientePrincipal {

    public static void main(String[] args) {


        Cliente cliente = new Cliente(1,"1747846987", "Juan", "Caiza", "Av. de las americas", "0946467894", "ejemplo@gmail.com");

        System.out.println(cliente.toString());

    }

}

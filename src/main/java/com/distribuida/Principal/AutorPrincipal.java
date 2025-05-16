package com.distribuida.Principal;

import com.distribuida.entities.Autor;

public class AutorPrincipal {

    public static void main(String[] args) {
        Autor autor =  new Autor();

        autor.setIdAutor(0001);
        autor.setNombre("Paul");
        autor.setApellido("Silvana");
        autor.setPais("Ecuador");
        autor.setDireccion("Av. de los Andes");
        autor.setTelefono("0947538635");
        autor.setCorreo("pauli@ejemplo.com");

        System.out.println(autor.toString());

    }


}

package com.distribuida.Principal;

import com.distribuida.entities.Categoria;

public class CategoriaPrincipal {

    public static void main(String[] args) {

        Categoria categoria = new Categoria();

        categoria.setIdCategoria(1);
        categoria.setCategoria("Fantasia");
        categoria.setDescripcion("Libro interactivo");

        System.out.println(categoria.toString());

    }

}

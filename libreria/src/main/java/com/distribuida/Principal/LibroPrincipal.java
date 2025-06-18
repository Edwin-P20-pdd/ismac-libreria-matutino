package com.distribuida.Principal;

import com.distribuida.entities.Autor;
import com.distribuida.entities.Categoria;
import com.distribuida.entities.Libro;

import java.util.Date;

public class LibroPrincipal {

    public static void main(String[] args) {

        Libro libro = new Libro();
        Categoria categoria = new Categoria();
        Autor autor = new Autor();

        libro.setIdLibro(1);
        libro.setTitulo("Ser Empresario");
        libro.setEditorial("EditLibro");
        libro.setNumPaginas(59);
        libro.setEdicion("Edition 2.0");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("Aprende a ser empresario");
        libro.setTipoPasta("Cartón");
        libro.setISBN("simulación");
        libro.setNumEjemplares(20);
        libro.setPortada("Edward Aprend");
        libro.setPresentacion("Acabado con borde dorado");
        libro.setPrecio(18.50);
        // inyección de dependencias
        libro.setCategoria(categoria);
        libro.setAutor(autor);

        System.out.println(libro.toString());

    }

}

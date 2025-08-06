package com.example.app1.domain.entities;

/**
 * Entidad que representa un comunicado dentro de la comunidad.
 * Contiene título y descripción del aviso.
 */
public class Comunicado {
    private String titulo;
    private String descripcion;

    /**
     * Constructor para crear un nuevo comunicado.
     * @param titulo Título del comunicado.
     * @param descripcion Contenido detallado del comunicado.
     */
    public Comunicado(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    /** @return el título del comunicado. */
    public String getTitulo() {
        return titulo;
    }

    /** @param titulo nuevo título a asignar. */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** @return la descripción del comunicado. */
    public String getDescripcion() {
        return descripcion;
    }

    /** @param descripcion nuevo contenido a asignar. */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
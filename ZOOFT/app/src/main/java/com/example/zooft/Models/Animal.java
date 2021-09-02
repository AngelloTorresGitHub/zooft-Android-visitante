package com.example.zooft.Models;

import java.io.Serializable;

public class Animal implements Serializable {

    private int id;
    private String id_zona, nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_zona() {
        return id_zona;
    }

    public void setId_zona(String id_zona) {
        this.id_zona = id_zona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", id_zona='" + id_zona + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

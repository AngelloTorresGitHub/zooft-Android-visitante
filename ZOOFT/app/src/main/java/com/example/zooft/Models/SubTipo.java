package com.example.zooft.Models;

public class SubTipo {

    private int id, id_departamento;
    private String descripcion, nivel, departamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "SubTipo{" +
                "id=" + id +
                ", id_departamento=" + id_departamento +
                ", descripcion='" + descripcion + '\'' +
                ", nivel='" + nivel + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}

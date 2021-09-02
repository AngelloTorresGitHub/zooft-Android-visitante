package com.example.zooft.Models;

import java.io.Serializable;

public class Empleado implements Serializable {

    private int id, id_departamento, id_coordinador;
    private String dni, nombre, apellido, email, zona, departamento, nomCoordinador, apeCoordinador, emailCoordinador;

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

    public int getId_coordinador() {
        return id_coordinador;
    }

    public void setId_coordinador(int id_coordinador) {
        this.id_coordinador = id_coordinador;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNomCoordinador() {
        return nomCoordinador;
    }

    public void setNomCoordinador(String nomCoordinador) {
        this.nomCoordinador = nomCoordinador;
    }

    public String getApeCoordinador() {
        return apeCoordinador;
    }

    public void setApeCoordinador(String apeCoordinador) {
        this.apeCoordinador = apeCoordinador;
    }

    public String getEmailCoordinador() {
        return emailCoordinador;
    }

    public void setEmailCoordinador(String emailCoordinador) {
        this.emailCoordinador = emailCoordinador;
    }

    @Override
    public String toString() {
        return "Empleados{" +
                "id=" + id +
                ", id_coordinador=" + id_coordinador +
                ", id_departamento=" + id_departamento +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", zona='" + zona + '\'' +
                ", departamento='" + departamento + '\'' +
                ", nomCoordinador='" + nomCoordinador + '\'' +
                ", apeCoordinador='" + apeCoordinador + '\'' +
                ", emailCoordinador='" + emailCoordinador + '\'' +
                '}';
    }
}

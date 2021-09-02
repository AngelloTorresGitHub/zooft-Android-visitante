package com.example.zooft.Models;

public class NewIncidencia {

    private int id_subTipo, id_departamento, id_animal, id_estado, id_empleado, id_coordinador;
    private String id, sub_tipo, nivel, departamento, animal, id_zona, estado, visitNum, nomEmple,
                    apeEmple, emailEmple, nomCoordinador, apeCoordinador, emailCorrdinador, observacion,
                    fecha_creacion, fecha_actualizacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getId_subTipo() {
        return id_subTipo;
    }

    public void setId_subTipo(int id_subTipo) {
        this.id_subTipo = id_subTipo;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_coordinador() {
        return id_coordinador;
    }

    public void setId_coordinador(int id_coordinador) {
        this.id_coordinador = id_coordinador;
    }

    public String getSub_tipo() {
        return sub_tipo;
    }

    public void setSub_tipo(String sub_tipo) {
        this.sub_tipo = sub_tipo;
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

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getId_zona() {
        return id_zona;
    }

    public void setId_zona(String id_zona) {
        this.id_zona = id_zona;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(String visitNum) {
        this.visitNum = visitNum;
    }

    public String getNomEmple() {
        return nomEmple;
    }

    public void setNomEmple(String nomEmple) {
        this.nomEmple = nomEmple;
    }

    public String getApeEmple() {
        return apeEmple;
    }

    public void setApeEmple(String apeEmple) {
        this.apeEmple = apeEmple;
    }

    public String getEmailEmple() {
        return emailEmple;
    }

    public void setEmailEmple(String emailEmple) {
        this.emailEmple = emailEmple;
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

    public String getEmailCorrdinador() {
        return emailCorrdinador;
    }

    public void setEmailCorrdinador(String emailCorrdinador) {
        this.emailCorrdinador = emailCorrdinador;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(String fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    @Override
    public String toString() {
        return "NewIncidencia{" +
                "id_subTipo=" + id_subTipo +
                ", id_departamento=" + id_departamento +
                ", id_animal=" + id_animal +
                ", id_estado=" + id_estado +
                ", id_empleado=" + id_empleado +
                ", id_coordinador=" + id_coordinador +
                ", id='" + id + '\'' +
                ", sub_tipo='" + sub_tipo + '\'' +
                ", nivel='" + nivel + '\'' +
                ", departamento='" + departamento + '\'' +
                ", animal='" + animal + '\'' +
                ", id_zona='" + id_zona + '\'' +
                ", estado='" + estado + '\'' +
                ", visitNum='" + visitNum + '\'' +
                ", nomEmple='" + nomEmple + '\'' +
                ", apeEmple='" + apeEmple + '\'' +
                ", emailEmple='" + emailEmple + '\'' +
                ", nomCoordinador='" + nomCoordinador + '\'' +
                ", apeCoordinador='" + apeCoordinador + '\'' +
                ", emailCorrdinador='" + emailCorrdinador + '\'' +
                ", observacion='" + observacion + '\'' +
                ", fecha_creacion='" + fecha_creacion + '\'' +
                ", fecha_actualizacion='" + fecha_actualizacion + '\'' +
                '}';
    }
}

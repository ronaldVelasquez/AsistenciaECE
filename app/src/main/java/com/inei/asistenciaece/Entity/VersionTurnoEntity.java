package com.inei.asistenciaece.Entity;

public class VersionTurnoEntity {

    private int id;
    private int numero_de_version;
    private String nombre;

    public VersionTurnoEntity(int id, int numero_de_version, String nombre) {
        this.id = id;
        this.numero_de_version = numero_de_version;
        this.nombre = nombre;
    }

    public VersionTurnoEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero_de_version() {
        return numero_de_version;
    }

    public void setNumero_de_version(int numero_de_version) {
        this.numero_de_version = numero_de_version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

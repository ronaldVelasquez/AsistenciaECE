package com.inei.asistenciaece.Entity;

public class VersionTurnoEntity {

    private int id;
    private int numero_version;
    private String nombre;

    public VersionTurnoEntity(int id, int numero_version, String nombre) {
        this.id = id;
        this.numero_version = numero_version;
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

    public int getNumero_version() {
        return numero_version;
    }

    public void setNumero_version(int numero_version) {
        this.numero_version = numero_version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

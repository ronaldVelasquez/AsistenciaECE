package com.inei.asistenciaece.Entity;

public class MarcacionEntity {

    private int id;
    private String nombre;

    public MarcacionEntity(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public MarcacionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

package com.inei.asistenciaece.Entity;

public class LocalEntity {

    private int id_local;
    private String nombre_local;
    private String direccion;

    public LocalEntity(int id_local, String nombre_local, String direccion) {
        this.id_local = id_local;
        this.nombre_local = nombre_local;
        this.direccion = direccion;
    }

    public LocalEntity() {
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public String getNombre_local() {
        return nombre_local;
    }

    public void setNombre_local(String nombre_local) {
        this.nombre_local = nombre_local;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

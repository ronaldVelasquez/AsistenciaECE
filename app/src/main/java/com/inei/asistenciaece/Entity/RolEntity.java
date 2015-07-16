package com.inei.asistenciaece.Entity;

public class RolEntity {

    private int idRol;
    private String rol;
    private String descripcion;

    public RolEntity(int idRol, String rol, String descripcion) {
        this.idRol = idRol;
        this.rol = rol;
        this.descripcion = descripcion;
    }

    public RolEntity() {
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

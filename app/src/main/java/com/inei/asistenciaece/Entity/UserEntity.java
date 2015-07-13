package com.inei.asistenciaece.Entity;

public class UserEntity {
    private String usuario;
    private int idUsu;
    private int idRol;
    private String password;
    private String estado;
    private String rol;
    private String descripcion;

    public UserEntity(String usuario, int idUsu, int idRol, String password, String estado, String rol, String descripcion) {
        this.usuario = usuario;
        this.idUsu = idUsu;
        this.idRol = idRol;
        this.password = password;
        this.estado = estado;
        this.rol = rol;
        this.descripcion = descripcion;
    }

    public UserEntity() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

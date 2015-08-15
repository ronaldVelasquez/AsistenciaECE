package com.inei.asistenciaece.Entity;

public class PostulanteEntity {
    private int id;
    private int nro_version;
    private int local_id;
    private int cargo_id;
    private String sede_id;
    private String dni;
    private String apellidos_nombres;
    private int numero_aula;
    private String numero_bungalow;
    private int status;

    public PostulanteEntity(int id, int nro_version, int local_id, int cargo_id, String sede_id, String dni, String apellidos_nombres, int numero_aula, String numero_bungalow, int status) {
        this.id = id;
        this.nro_version= nro_version;
        this.local_id = local_id;
        this.cargo_id = cargo_id;
        this.sede_id = sede_id;
        this.dni = dni;
        this.apellidos_nombres = apellidos_nombres;
        this.numero_aula = numero_aula;
        this.numero_bungalow = numero_bungalow;
        this.status = status;
    }

    public PostulanteEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNro_version() {
        return nro_version;
    }

    public void setNro_version(int nro_version) {
        this.nro_version = nro_version;
    }

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public int getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(int cargo_id) {
        this.cargo_id = cargo_id;
    }

    public String getSede_id() {
        return sede_id;
    }

    public void setSede_id(String sede_id) {
        this.sede_id = sede_id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos_nombres() {
        return apellidos_nombres;
    }

    public void setApellidos_nombres(String apellidos_nombres) {
        this.apellidos_nombres = apellidos_nombres;
    }

    public int getNumero_aula() {
        return numero_aula;
    }

    public void setNumero_aula(int numero_aula) {
        this.numero_aula = numero_aula;
    }

    public String getNumero_bungalow() {
        return numero_bungalow;
    }

    public void setNumero_bungalow(String numero_bungalow) {
        this.numero_bungalow = numero_bungalow;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}


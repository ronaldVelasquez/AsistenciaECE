package com.inei.asistenciaece.Entity;

public class PostulanteEntity {
    private int id_local;
    private int id_cargo;
    private String dni;
    private String ape_nom;
    private String nro_aula;
    private String lugar_asigna;
    private int m1_estado;
    private String m1_fecha;
    private int m2_estado;
    private String m2_fecha;
    private int state;

    public PostulanteEntity(int id_local, int id_cargo, String dni, String ape_nom, String nro_aula, String lugar_asigna, int m1_estado, String m1_fecha, int m2_estado, String m2_fecha) {
        this.id_local = id_local;
        this.id_cargo = id_cargo;
        this.dni = dni;
        this.ape_nom = ape_nom;
        this.nro_aula = nro_aula;
        this.lugar_asigna = lugar_asigna;
        this.m1_estado = m1_estado;
        this.m1_fecha = m1_fecha;
        this.m2_estado = m2_estado;
        this.m2_fecha = m2_fecha;
    }

    public PostulanteEntity() {
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApe_nom() {
        return ape_nom;
    }

    public void setApe_nom(String ape_nom) {
        this.ape_nom = ape_nom;
    }

    public String getNro_aula() {
        return nro_aula;
    }

    public void setNro_aula(String nro_aula) {
        this.nro_aula = nro_aula;
    }

    public String getLugar_asigna() {
        return lugar_asigna;
    }

    public void setLugar_asigna(String lugar_asigna) {
        this.lugar_asigna = lugar_asigna;
    }

    public int getM1_estado() {
        return m1_estado;
    }

    public void setM1_estado(int m1_estado) {
        this.m1_estado = m1_estado;
    }

    public String getM1_fecha() {
        return m1_fecha;
    }

    public void setM1_fecha(String m1_fecha) {
        this.m1_fecha = m1_fecha;
    }

    public int getM2_estado() {
        return m2_estado;
    }

    public void setM2_estado(int m2_estado) {
        this.m2_estado = m2_estado;
    }

    public String getM2_fecha() {
        return m2_fecha;
    }

    public void setM2_fecha(String m2_fecha) {
        this.m2_fecha = m2_fecha;
    }
}


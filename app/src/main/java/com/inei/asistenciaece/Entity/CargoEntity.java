package com.inei.asistenciaece.Entity;

public class CargoEntity {
    private int id_cargo;
    private String cargo;
    private String cargo_res;
    private String sigla;
    private String nivel;

    public CargoEntity(int id_cargo, String cargo, String cargo_res, String sigla, String nivel) {
        this.id_cargo = id_cargo;
        this.cargo = cargo;
        this.cargo_res = cargo_res;
        this.sigla = sigla;
        this.nivel = nivel;
    }

    public CargoEntity() {
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo_res() {
        return cargo_res;
    }

    public void setCargo_res(String cargo_res) {
        this.cargo_res = cargo_res;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}

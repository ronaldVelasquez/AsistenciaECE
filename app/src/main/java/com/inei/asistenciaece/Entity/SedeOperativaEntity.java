package com.inei.asistenciaece.Entity;

public class SedeOperativaEntity {
    private String cod_sede_operativa;
    private String sede_operativa;

    public SedeOperativaEntity(String cod_sede_operativa, String sede_operativa) {
        this.cod_sede_operativa = cod_sede_operativa;
        this.sede_operativa = sede_operativa;
    }

    public SedeOperativaEntity() {
    }

    public String getCod_sede_operativa() {
        return cod_sede_operativa;
    }

    public void setCod_sede_operativa(String cod_sede_operativa) {
        this.cod_sede_operativa = cod_sede_operativa;
    }

    public String getSede_operativa() {
        return sede_operativa;
    }

    public void setSede_operativa(String sede_operativa) {
        this.sede_operativa = sede_operativa;
    }
}

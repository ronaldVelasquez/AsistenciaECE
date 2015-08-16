package com.inei.asistenciaece.Entity;

public class VersionEntity {

    private int numero_de_version;
    private String fechaCrea;
    private String usuarioCrea;

    public VersionEntity(int numero_de_version, String fechaCrea, String usuarioCrea) {
        this.numero_de_version = numero_de_version;
        this.fechaCrea = fechaCrea;
        this.usuarioCrea = usuarioCrea;
    }

    public VersionEntity() {
    }

    public int getNumero_de_version() {
        return numero_de_version;
    }

    public void setNumero_de_version(int numero_de_version) {
        this.numero_de_version = numero_de_version;
    }

    public String getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }
}

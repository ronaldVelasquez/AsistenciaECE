package com.inei.asistenciaece.Entity;

public class VersionEntity {

    private int nro_version;
    private String fechaCrea;
    private String usuarioCrea;

    public VersionEntity(int nro_version, String fechaCrea, String usuarioCrea) {

        this.nro_version = nro_version;
        this.fechaCrea = fechaCrea;
        this.usuarioCrea = usuarioCrea;
    }

    public VersionEntity() {
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

    public int getNro_version() {
        return nro_version;
    }

    public void setNro_version(int nro_version) {
        this.nro_version = nro_version;
    }
}

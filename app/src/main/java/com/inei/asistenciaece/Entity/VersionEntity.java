package com.inei.asistenciaece.Entity;

public class VersionEntity {

    private int nro_version;

    public VersionEntity(int nro_version) {
        this.nro_version = nro_version;
    }

    public VersionEntity() {
    }

    public int getNro_version() {
        return nro_version;
    }

    public void setNro_version(int nro_version) {
        this.nro_version = nro_version;
    }
}

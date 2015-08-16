package com.inei.asistenciaece.Entity;

import java.util.ArrayList;

public class DataEntity {
    private ArrayList<SendAsistenciaEntity> asistencias;

    public DataEntity(ArrayList<SendAsistenciaEntity> asistencias) {
        this.asistencias = asistencias;
    }

    public DataEntity() {
    }

    public ArrayList<SendAsistenciaEntity> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(ArrayList<SendAsistenciaEntity> asistencias) {
        this.asistencias = asistencias;
    }
}

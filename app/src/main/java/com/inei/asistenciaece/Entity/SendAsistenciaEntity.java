package com.inei.asistenciaece.Entity;

public class SendAsistenciaEntity {
    private int version_turno_id;
    private String fecha;
    private int marcacion_id;
    private int postulante_id;
    private int asistencia;

    public SendAsistenciaEntity(int version_turno_id, String fecha, int marcacion_id, int postulante_id, int asistencia) {
        this.version_turno_id = version_turno_id;
        this.fecha = fecha;
        this.marcacion_id = marcacion_id;
        this.postulante_id = postulante_id;
        this.asistencia = asistencia;
    }

    public SendAsistenciaEntity() {
    }

    public int getVersion_turno_id() {
        return version_turno_id;
    }

    public void setVersion_turno_id(int version_turno_id) {
        this.version_turno_id = version_turno_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMarcacion_id() {
        return marcacion_id;
    }

    public void setMarcacion_id(int marcacion_id) {
        this.marcacion_id = marcacion_id;
    }

    public int getPostulante_id() {
        return postulante_id;
    }

    public void setPostulante_id(int postulante_id) {
        this.postulante_id = postulante_id;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }
}

package com.inei.asistenciaece.Entity;

public class AsistenciaEntity {
    private int id;
    private int postulante_id;
    private int version_id;
    private int asistencia;
    private int version_turno_id;
    private int marcacion_id;
    private String fecha;
    private int status;

    public AsistenciaEntity(int id, int postulante_id, int version_id, int asistencia, int version_turno_id, int marcacion_id, String fecha, int status) {
        this.id = id;
        this.postulante_id = postulante_id;
        this.version_id = version_id;
        this.asistencia = asistencia;
        this.version_turno_id = version_turno_id;
        this.marcacion_id = marcacion_id;
        this.fecha = fecha;
        this.status = status;
    }

    public AsistenciaEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostulante_id() {
        return postulante_id;
    }

    public void setPostulante_id(int postulante_id) {
        this.postulante_id = postulante_id;
    }

    public int getVersion_id() {
        return version_id;
    }

    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    public int getVersion_turno_id() {
        return version_turno_id;
    }

    public void setVersion_turno_id(int version_turno_id) {
        this.version_turno_id = version_turno_id;
    }

    public int getMarcacion_id() {
        return marcacion_id;
    }

    public void setMarcacion_id(int marcacion_id) {
        this.marcacion_id = marcacion_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

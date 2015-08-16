package com.inei.asistenciaece.Entity;

public class HorarioEntity {

    private int id;
    private int version_turno_id;
    private int tipo_capacitacion_id;
    private int marcacion_id;
    private String fecha;
    private String hora_inicio;
    private String hora_fin;

    public HorarioEntity(int id, int version_turno_id, int tipo_capacitacion_id, int marcacion_id, String fecha, String hora_inicio, String hora_fin) {
        this.id = id;
        this.version_turno_id = version_turno_id;
        this.tipo_capacitacion_id = tipo_capacitacion_id;
        this.marcacion_id = marcacion_id;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
    }

    public HorarioEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion_turno_id() {
        return version_turno_id;
    }

    public void setVersion_turno_id(int version_turno_id) {
        this.version_turno_id = version_turno_id;
    }

    public int getTipo_capacitacion_id() {
        return tipo_capacitacion_id;
    }

    public void setTipo_capacitacion_id(int tipo_capacitacion_id) {
        this.tipo_capacitacion_id = tipo_capacitacion_id;
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

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }
}

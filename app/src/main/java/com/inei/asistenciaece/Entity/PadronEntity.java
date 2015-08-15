package com.inei.asistenciaece.Entity;

import java.util.ArrayList;

public class PadronEntity {

    private ArrayList<PostulanteEntity> postulantes;
    private ArrayList<CargoEntity> cargos;
    private ArrayList<LocalEntity> locales;
    private ArrayList<RolEntity> roles;
    private VersionEntity version;
    private ArrayList<VersionTurnoEntity> versiones_turno;
    private ArrayList<HorarioEntity> horarios;
    private ArrayList<TipoCapacitacionEntity> tipos_capacitacion;
    private ArrayList<MarcacionEntity> marcaciones;
    private ArrayList<SedeOperativaEntity> sedes_operativas;

    public PadronEntity(ArrayList<PostulanteEntity> postulantes, ArrayList<CargoEntity> cargos, ArrayList<LocalEntity> locales, ArrayList<RolEntity> roles, VersionEntity version, ArrayList<VersionTurnoEntity> versiones_turno, ArrayList<HorarioEntity> horarios, ArrayList<TipoCapacitacionEntity> tipos_capacitacion, ArrayList<MarcacionEntity> marcaciones, ArrayList<SedeOperativaEntity> sedes_operativas) {
        this.postulantes = postulantes;
        this.cargos = cargos;
        this.locales = locales;
        this.roles = roles;
        this.version = version;
        this.versiones_turno = versiones_turno;
        this.horarios = horarios;
        this.tipos_capacitacion = tipos_capacitacion;
        this.marcaciones = marcaciones;
        this.sedes_operativas = sedes_operativas;
    }

    public PadronEntity() {
    }

    public ArrayList<PostulanteEntity> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(ArrayList<PostulanteEntity> postulantes) {
        this.postulantes = postulantes;
    }

    public ArrayList<CargoEntity> getCargos() {
        return cargos;
    }

    public void setCargos(ArrayList<CargoEntity> cargos) {
        this.cargos = cargos;
    }

    public ArrayList<LocalEntity> getLocales() {
        return locales;
    }

    public void setLocales(ArrayList<LocalEntity> locales) {
        this.locales = locales;
    }

    public ArrayList<RolEntity> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<RolEntity> roles) {
        this.roles = roles;
    }

    public VersionEntity getVersion() {
        return version;
    }

    public void setVersion(VersionEntity version) {
        this.version = version;
    }

    public ArrayList<VersionTurnoEntity> getVersiones_turno() {
        return versiones_turno;
    }

    public void setVersiones_turno(ArrayList<VersionTurnoEntity> versiones_turno) {
        this.versiones_turno = versiones_turno;
    }

    public ArrayList<HorarioEntity> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<HorarioEntity> horarios) {
        this.horarios = horarios;
    }

    public ArrayList<TipoCapacitacionEntity> getTipos_capacitacion() {
        return tipos_capacitacion;
    }

    public void setTipos_capacitacion(ArrayList<TipoCapacitacionEntity> tipos_capacitacion) {
        this.tipos_capacitacion = tipos_capacitacion;
    }

    public ArrayList<MarcacionEntity> getMarcaciones() {
        return marcaciones;
    }

    public void setMarcaciones(ArrayList<MarcacionEntity> marcaciones) {
        this.marcaciones = marcaciones;
    }

    public ArrayList<SedeOperativaEntity> getSedes_operativas() {
        return sedes_operativas;
    }

    public void setSedes_operativas(ArrayList<SedeOperativaEntity> sedes_operativas) {
        this.sedes_operativas = sedes_operativas;
    }
}

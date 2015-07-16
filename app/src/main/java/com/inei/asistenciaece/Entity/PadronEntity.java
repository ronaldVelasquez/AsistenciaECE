package com.inei.asistenciaece.Entity;

import com.inei.asistenciaece.DAO.VersionDao;

import java.util.ArrayList;

public class PadronEntity {

    private ArrayList<PostulanteEntity> postulantes;
    private ArrayList<CargoEntity> cargo;
    private ArrayList<LocalEntity> local;
    private ArrayList<RolEntity> rol;
    private VersionEntity version;

    public PadronEntity(ArrayList<PostulanteEntity> postulantes, ArrayList<CargoEntity> cargo, ArrayList<LocalEntity> local, ArrayList<RolEntity> rol, VersionEntity version) {
        this.postulantes = postulantes;
        this.cargo = cargo;
        this.local = local;
        this.rol = rol;
        this.version = version;
    }

    public PadronEntity() {
    }

    public ArrayList<PostulanteEntity> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(ArrayList<PostulanteEntity> postulantes) {
        this.postulantes = postulantes;
    }

    public ArrayList<CargoEntity> getCargo() {
        return cargo;
    }

    public void setCargo(ArrayList<CargoEntity> cargo) {
        this.cargo = cargo;
    }

    public ArrayList<LocalEntity> getLocal() {
        return local;
    }

    public void setLocal(ArrayList<LocalEntity> local) {
        this.local = local;
    }

    public ArrayList<RolEntity> getRol() {
        return rol;
    }

    public void setRol(ArrayList<RolEntity> rol) {
        this.rol = rol;
    }

    public VersionEntity getVersion() {
        return version;
    }

    public void setVersion(VersionEntity version) {
        this.version = version;
    }
}

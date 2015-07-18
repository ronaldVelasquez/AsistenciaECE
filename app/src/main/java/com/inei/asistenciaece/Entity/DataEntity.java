package com.inei.asistenciaece.Entity;

import java.util.ArrayList;

public class DataEntity {
    private ArrayList<PostulanteEntity> postulantes;

    public DataEntity(ArrayList<PostulanteEntity> postulantes) {
        this.postulantes = postulantes;
    }

    public DataEntity() {
    }

    public ArrayList<PostulanteEntity> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(ArrayList<PostulanteEntity> postulantes) {
        this.postulantes = postulantes;
    }
}

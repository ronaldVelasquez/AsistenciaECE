package com.inei.asistenciaece.Entity;

public class StatusEntity {
    private PostulanteEntity postulanteEntity;
    private int status;

    public StatusEntity(PostulanteEntity postulanteEntity, int status) {
        this.postulanteEntity = postulanteEntity;
        this.status = status;
    }

    public StatusEntity() {
    }

    public PostulanteEntity getPostulanteEntity() {
        return postulanteEntity;
    }

    public void setPostulanteEntity(PostulanteEntity postulanteEntity) {
        this.postulanteEntity = postulanteEntity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

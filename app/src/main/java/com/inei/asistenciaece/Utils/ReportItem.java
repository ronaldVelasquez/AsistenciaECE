package com.inei.asistenciaece.Utils;

public class ReportItem {
    private String nroClasses;
    private int nroAsign;
    private int nroRegister;
    private int nroNoRegister;
    private int nroSync;

    public ReportItem(String nroClasses, int nroAsign, int nroRegister, int nroNoRegister, int nroSync) {
        this.nroClasses = nroClasses;
        this.nroAsign = nroAsign;
        this.nroRegister = nroRegister;
        this.nroNoRegister = nroNoRegister;
        this.nroSync = nroSync;
    }

    public ReportItem() {
    }

    public String getNroClasses() {
        return nroClasses;
    }

    public void setNroClasses(String nroClasses) {
        this.nroClasses = nroClasses;
    }

    public int getNroAsign() {
        return nroAsign;
    }

    public void setNroAsign(int nroAsign) {
        this.nroAsign = nroAsign;
    }

    public int getNroRegister() {
        return nroRegister;
    }

    public void setNroRegister(int nroRegister) {
        this.nroRegister = nroRegister;
    }

    public int getNroNoRegister() {
        return nroNoRegister;
    }

    public void setNroNoRegister(int nroNoRegister) {
        this.nroNoRegister = nroNoRegister;
    }

    public int getNroSync() {
        return nroSync;
    }

    public void setNroSync(int nroSync) {
        this.nroSync = nroSync;
    }
}

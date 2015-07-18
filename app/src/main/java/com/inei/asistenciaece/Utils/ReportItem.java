package com.inei.asistenciaece.Utils;

public class ReportItem {
    private String nroClasses;
    private String nroAsign;
    private String nroRegister;
    private String nroNoRegister;
    private String nroSync;

    public ReportItem(String nroClasses, String nroAsign, String nroRegister, String nroNoRegister, String nroSync) {
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

    public String getNroAsign() {
        return nroAsign;
    }

    public void setNroAsign(String nroAsign) {
        this.nroAsign = nroAsign;
    }

    public String getNroRegister() {
        return nroRegister;
    }

    public void setNroRegister(String nroRegister) {
        this.nroRegister = nroRegister;
    }

    public String getNroNoRegister() {
        return nroNoRegister;
    }

    public void setNroNoRegister(String nroNoRegister) {
        this.nroNoRegister = nroNoRegister;
    }

    public String getNroSync() {
        return nroSync;
    }

    public void setNroSync(String nroSync) {
        this.nroSync = nroSync;
    }
}

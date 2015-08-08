package com.inei.asistenciaece.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.inei.asistenciaece.R;

public class ReportHolder extends RecyclerView.ViewHolder{

    TextView txtNroClasses;
    TextView txtNroAsign;
    TextView txtNroRegister;
    TextView txtNoRegister;
    TextView txtNroSync;

    public ReportHolder(View itemView) {
        super(itemView);
        txtNroClasses = (TextView) itemView.findViewById(R.id.txt_nro_classes);
        txtNroAsign = (TextView) itemView.findViewById(R.id.txt_nro_asign);
        txtNroRegister = (TextView) itemView.findViewById(R.id.txt_nro_register);
        txtNoRegister = (TextView) itemView.findViewById(R.id.txt_no_register);
        txtNroSync = (TextView) itemView.findViewById(R.id.txt_nro_sync);
    }

    public TextView getTxtNroClasses() {
        return txtNroClasses;
    }

    public void setTxtNroClasses(TextView txtNroClasses) {
        this.txtNroClasses = txtNroClasses;
    }

    public TextView getTxtNroAsign() {
        return txtNroAsign;
    }

    public void setTxtNroAsign(TextView txtNroAsign) {
        this.txtNroAsign = txtNroAsign;
    }

    public TextView getTxtNroRegister() {
        return txtNroRegister;
    }

    public void setTxtNroRegister(TextView txtNroRegister) {
        this.txtNroRegister = txtNroRegister;
    }

    public TextView getTxtNoRegister() {
        return txtNoRegister;
    }

    public void setTxtNoRegister(TextView txtNoRegister) {
        this.txtNoRegister = txtNoRegister;
    }

    public TextView getTxtNroSync() {
        return txtNroSync;
    }

    public void setTxtNroSync(TextView txtNroSync) {
        this.txtNroSync = txtNroSync;
    }
}

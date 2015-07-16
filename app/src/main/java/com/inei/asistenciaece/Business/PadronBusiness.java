package com.inei.asistenciaece.Business;

import android.content.Context;
import android.widget.Toast;

import com.inei.asistenciaece.DAO.PadronDao;
import com.inei.asistenciaece.Entity.PadronEntity;

public class PadronBusiness {

    private static final String TAG = PadronBusiness.class.getSimpleName();
    private Context context;
    private PadronDao padronDao;

    public PadronBusiness(Context context) {
        this.context = context;
        padronDao = PadronDao.getInstance(context);
    }

    public void addPadron(PadronEntity padronEntity) {
        if (padronEntity == null) {
            Toast.makeText(context, "Porblemas con datos en la nube", Toast.LENGTH_SHORT).show();
        } else {
            boolean flag = padronDao.addPadron(padronEntity);
        }

    }
}

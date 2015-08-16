package com.inei.asistenciaece.Business;

import android.content.Context;

import com.inei.asistenciaece.DAO.SedeDao;
import com.inei.asistenciaece.Entity.SedeOperativaEntity;

public class SedeBusiness {
    private static final String TAG = SedeBusiness.class.getSimpleName();
    private static SedeDao sedeDao;
    private Context context;



    public SedeBusiness(Context context){
        this.context = context;
        sedeDao = SedeDao.getInstance(context);
    }

    public String getSede(String sede_id){
        SedeOperativaEntity sedeOperativaEntity = sedeDao.getSede(sede_id);
        String sede;
        if(sedeOperativaEntity == null){
            sede = "Sin Sede";
        } else {
            sede = sedeOperativaEntity.getSede_operativa();
        }
        return sede;
    }


}

package com.inei.asistenciaece.Business;

import android.content.Context;

import com.inei.asistenciaece.DAO.HorarioDao;
import com.inei.asistenciaece.Entity.HorarioEntity;
import com.inei.asistenciaece.Utils.DateFormatUtil;


public class HorarioBusiness {

    private static final String TAG = HorarioBusiness.class.getSimpleName();
    private Context context;
    private HorarioDao horarioDao;

    public HorarioBusiness(Context context){
        this.context = context;
        horarioDao = horarioDao.getInstance(context);
    }

    public HorarioEntity getHorario(int marcacionId) {
        String dateTime = DateFormatUtil.getDateTime();
        HorarioEntity horarioEntity = horarioDao.getHorario(dateTime, marcacionId);
        return horarioEntity;
    }
}

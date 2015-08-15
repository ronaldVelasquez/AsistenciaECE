package com.inei.asistenciaece.Business;

import android.content.Context;

import com.inei.asistenciaece.DAO.AsistenciaDao;
import com.inei.asistenciaece.DAO.HorarioDao;
import com.inei.asistenciaece.DAO.PostulanteDao;
import com.inei.asistenciaece.Entity.AsistenciaEntity;
import com.inei.asistenciaece.Entity.HorarioEntity;
import com.inei.asistenciaece.Entity.PostulanteEntity;
import com.inei.asistenciaece.Entity.StatusEntity;
import com.inei.asistenciaece.Utils.DateFormatUtil;

import java.util.Calendar;
import java.util.Date;

public class AsistenciaBusiness {

    private static final String TAG = AsistenciaBusiness.class.getSimpleName();
    private Context context;
    private AsistenciaDao asistenciaDao;
    private PostulanteDao postulanteDao;
    private HorarioDao horarioDao;

    public AsistenciaBusiness(Context context) {
        this.context = context;
        asistenciaDao = AsistenciaDao.getInstance(context);
        postulanteDao = PostulanteDao.getInstance(context);
        horarioDao = HorarioDao.getInstance(context);
    }

    public StatusEntity checkPresence(String dni, int idMarcacion){
        /**
         * 0 = error
         * 1 = new asistencia
         * 2 = asistencia exist
         * 3 = postulante no exist
         * 4 = exist but horario no exist
         */
        StatusEntity statusEntity = new StatusEntity();
        Date dateNow = Calendar.getInstance().getTime();
        String date = DateFormatUtil.getDate(dateNow);
        String time = DateFormatUtil.getTime(dateNow);
        PostulanteEntity postulanteEntity = postulanteDao.checkPostulante(dni);
        if (postulanteEntity != null){
            statusEntity.setPostulanteEntity(postulanteEntity);
            HorarioEntity horarioEntity = horarioDao.getHorario(date, time, idMarcacion);
            if (horarioEntity != null){
                AsistenciaEntity asistenciaEntity = asistenciaDao.checkPresence(dni, horarioEntity, date, time, postulanteEntity.getId());
                statusEntity.setStatus(asistenciaEntity.getStatus());
            } else {
                statusEntity.setStatus(4);
            }
        } else {
            statusEntity.setStatus(3);
            statusEntity.setPostulanteEntity(null);
        }
        return statusEntity;
    }
}

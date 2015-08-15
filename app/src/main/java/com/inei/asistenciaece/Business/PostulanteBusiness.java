package com.inei.asistenciaece.Business;

import android.content.Context;

import com.inei.asistenciaece.DAO.HorarioDao;
import com.inei.asistenciaece.DAO.PostulanteDao;
import com.inei.asistenciaece.Entity.HorarioEntity;
import com.inei.asistenciaece.Entity.PostulanteEntity;
import com.inei.asistenciaece.Utils.DateFormatUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PostulanteBusiness {
    private static final String TAG = PostulanteBusiness.class.getSimpleName();
    private Context context;
    private PostulanteDao postulanteDao;
    private HorarioDao horarioDao;

    public PostulanteBusiness(Context context) {
        this.context = context;
        postulanteDao = PostulanteDao.getInstance(context);
        horarioDao = HorarioDao.getInstance(context);
    }

    public PostulanteEntity checkPresence(String dni){
        Date dateNow = Calendar.getInstance().getTime();
        String date = DateFormatUtil.getDate(dateNow);
        String time = DateFormatUtil.getTime(dateNow);
        int idMarcacion = 1;
        HorarioEntity horarioEntity = horarioDao.getHorario(date, time, idMarcacion);
        if (horarioEntity != null){
            PostulanteEntity postulanteEntity= postulanteDao.checkPresence(dni);
        }

        return postulanteEntity;
    }
    public PostulanteEntity checkPresence(String dni, String aula){
        PostulanteEntity postulanteEntity= postulanteDao.checkPresence(dni, aula);
        return postulanteEntity;
    }

    public ArrayList<String> getClasses() {
        ArrayList<String> classes = postulanteDao.getClasses();
        return classes;
    }
}

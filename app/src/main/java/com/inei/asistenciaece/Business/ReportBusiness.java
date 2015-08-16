package com.inei.asistenciaece.Business;

import android.content.Context;

import com.inei.asistenciaece.DAO.PostulanteDao;
import com.inei.asistenciaece.Entity.HorarioEntity;
import com.inei.asistenciaece.Utils.ReportItem;

import java.util.ArrayList;

public class ReportBusiness {

    private static final String TAG = ReportBusiness.class.getSimpleName();
    private Context context;
    private PostulanteDao postulanteDao;

    public ReportBusiness(Context context){
        this.context = context;
        postulanteDao = PostulanteDao.getInstance(context);
    }

    public ArrayList<ReportItem> getReportLocal(HorarioEntity horarioEntity){
        return postulanteDao.getReportLocal(horarioEntity);
    }
    public ArrayList<ReportItem> getReportClasses(HorarioEntity horarioEntity){
        return postulanteDao.getReportClasses(horarioEntity);
    }
}

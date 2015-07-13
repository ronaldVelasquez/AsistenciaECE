package com.inei.asistenciaece.Business;


import android.content.Context;

import com.inei.asistenciaece.DAO.VersionDao;

public class VersionBusiness {
    private static final String TAG = VersionBusiness.class.getSimpleName();
    private Context context;
    private VersionDao versionDao;

    public VersionBusiness (Context context){
        this.context = context;
        versionDao = VersionDao.getInstance(context);
    }

}

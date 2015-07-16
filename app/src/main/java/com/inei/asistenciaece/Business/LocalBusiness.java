package com.inei.asistenciaece.Business;

import android.content.Context;

import com.inei.asistenciaece.DAO.LocalDao;
import com.inei.asistenciaece.Entity.LocalEntity;

public class LocalBusiness {
    private static final String TAG = LocalBusiness.class.getSimpleName();
    private Context context;
    private LocalDao localDao;

    public LocalBusiness(Context context){
        this.context = context;
        localDao = LocalDao.getInstance(context);
    }

    public String getLocal(int id_local) {
        LocalEntity localEntity = localDao.getLocal(id_local);
        String localName;
        if(!localEntity.getNombre_local().isEmpty()){
            localName = "Sin Local";
        } else {
            localName = localEntity.getNombre_local();
        }
        return localName;
    }
}

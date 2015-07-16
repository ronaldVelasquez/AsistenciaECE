package com.inei.asistenciaece.Business;

import android.content.Context;

import com.inei.asistenciaece.DAO.PostulanteDao;
import com.inei.asistenciaece.Entity.PostulanteEntity;

public class PostulanteBusiness {
    private static final String TAG = PostulanteBusiness.class.getSimpleName();
    private Context context;
    private PostulanteDao postulanteDao;

    public PostulanteBusiness(Context context) {
        this.context = context;
        postulanteDao = PostulanteDao.getInstance(context);
    }

    public PostulanteEntity checkPresence(String dni){
        PostulanteEntity postulanteEntity= postulanteDao.checkPresence(dni);
        return postulanteEntity;
    }
}

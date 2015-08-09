package com.inei.asistenciaece.Business;

import android.content.Context;

import com.inei.asistenciaece.DAO.PostulanteDao;
import com.inei.asistenciaece.Entity.PostulanteEntity;

import java.util.ArrayList;

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
    public PostulanteEntity checkPresence(String dni, String aula){
        PostulanteEntity postulanteEntity= postulanteDao.checkPresence(dni, aula);
        return postulanteEntity;
    }

    public ArrayList<String> getClasses() {
        ArrayList<String> classes = postulanteDao.getClasses();
        return classes;
    }
}

package com.inei.asistenciaece.DAO;

import android.content.Context;

public class PostulanteDao extends BaseDAO{

    private static final String TAG = PostulanteDao.class.getSimpleName();
    private static PostulanteDao postulanteDao;

    public synchronized static PostulanteDao getInstance(Context context){
        if(postulanteDao == null){
            postulanteDao = new PostulanteDao(context);
        }
        return postulanteDao;
    }

    public PostulanteDao(Context context) {
        initDBHelper(context);
    }

}

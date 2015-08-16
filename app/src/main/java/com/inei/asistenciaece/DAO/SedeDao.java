package com.inei.asistenciaece.DAO;

import android.content.Context;
import android.util.Log;

import com.inei.asistenciaece.Entity.SedeOperativaEntity;

public class SedeDao extends BaseDAO{
    private static final String TAG = SedeDao.class.getSimpleName();
    private static SedeDao sedeDao;

    public synchronized static SedeDao getInstance(Context context) {
        if (sedeDao == null) {
            sedeDao = new SedeDao(context);
        }
        return sedeDao;
    }

    public SedeDao(Context context) {
        initDBHelper(context);
    }

    public SedeOperativaEntity getSede(String sede_id){
        Log.v(TAG, "Start getSede");
        SedeOperativaEntity sedeOperativaEntity= new SedeOperativaEntity();
        try{
            openDBHelper();
            SQL = "select * from sede_operativa where cod_sede_operativa = '" + sede_id + "'";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if(cursor.moveToFirst()){
                Log.v(TAG, "Sede found");
                sedeOperativaEntity.setCod_sede_operativa(cursor.getString(cursor.getColumnIndex("cod_sede_operativa")));
                sedeOperativaEntity.setSede_operativa(cursor.getString(cursor.getColumnIndex("sede_operativa")));
            } else {
                sedeOperativaEntity = null;
                Log.v(TAG, "Sede not found");
            }
        } catch (Exception ex){
            ex.printStackTrace();
            sedeOperativaEntity = null;
            Log.e(TAG, "Error when search sede");
        } finally {
            cursor.close();
            closeDBHelper();
            Log.v(TAG, "End getSede");
        }
        return sedeOperativaEntity;
    }

}

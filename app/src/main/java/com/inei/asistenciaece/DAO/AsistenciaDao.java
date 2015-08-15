package com.inei.asistenciaece.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.inei.asistenciaece.Entity.AsistenciaEntity;
import com.inei.asistenciaece.Entity.HorarioEntity;
import com.inei.asistenciaece.Entity.PostulanteEntity;

public class AsistenciaDao extends BaseDAO{

    private static final String TAG = AsistenciaDao.class.getSimpleName();
    private static AsistenciaDao asistenciaDao;

    public synchronized static AsistenciaDao getInstance(Context context){
        if(asistenciaDao == null){
           asistenciaDao = new AsistenciaDao(context);
        }
        return asistenciaDao;
    }

    public AsistenciaDao(Context context) {
        initDBHelper(context);
    }

    public AsistenciaEntity checkPresence(String dni, HorarioEntity horarioEntity, String dateNow, String timeNow, PostulanteEntity postulanteEntity){
        Log.v(TAG, "Start checkPrensence");
        AsistenciaEntity asistenciaEntity = new AsistenciaEntity();
        try{
            openDBHelper();
            String dateTimeStart = horarioEntity.getFecha() + " " + horarioEntity.getHora_inicio();
            String dateTimeFinish = horarioEntity.getFecha() + " " + horarioEntity.getHora_fin();
            SQL = "select * from postulante_asistencia where dni = '" + dni + "' and (fecha >= date('" + dateTimeStart + "') and fecha <= date('"+ dateTimeFinish + "')";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()) {
                asistenciaEntity.setStatus(2);
            } else {
                contentValues = new ContentValues();
                contentValues.put("postulante_id", postulanteEntity.getId());
                contentValues.put("marcacion_id", horarioEntity.getMarcacion_id());
                contentValues.put("version_turno_id", horarioEntity.getVersion_turno_id());
                contentValues.put("asistencia", 1);
                contentValues.put("fecha", dateNow + "" + timeNow);
                dbHelper.getDatabase().insert("postulante_asistencia", null, contentValues);
                dbHelper.setTransactionSuccessful();
                asistenciaEntity.setStatus(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            asistenciaEntity.setStatus(0);
            Log.e(TAG, "Error database");
        } finally {
            Log.v(TAG, "End checkPresence");
            cursor.close();
            closeDBHelper();
        }
        return asistenciaEntity;
    }
}

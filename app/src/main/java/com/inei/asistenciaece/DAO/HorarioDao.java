package com.inei.asistenciaece.DAO;

import android.content.Context;
import android.util.Log;

import com.inei.asistenciaece.Entity.HorarioEntity;

public class HorarioDao extends BaseDAO {

    private static final String TAG = HorarioDao.class.getSimpleName();
    private static HorarioDao horarioDao;

    public synchronized static HorarioDao getInstance(Context context) {
        if (horarioDao == null) {
            horarioDao = new HorarioDao(context);
        }
        return horarioDao;
    }

    public HorarioDao(Context context) {
        initDBHelper(context);
    }

    public HorarioEntity getHorario(String date, String time, int idMarcacion) {
        Log.v(TAG, "Start get horario");
        HorarioEntity horarioEntity = new HorarioEntity();
        try{
            openDBHelper();
            SQL = "select * from horario where fecha like '" + date + "' and (hora_inicio <= time('" + time + "') and hora_fin >= time('" + time + "')) and id_marcacion = " + idMarcacion;
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                horarioEntity.setId(cursor.getInt(cursor.getColumnIndex("id")));
                horarioEntity.setVersion_turno_id(cursor.getInt(cursor.getColumnIndex("version_turno_id")));
                horarioEntity.setTipo_capacitacion_id(cursor.getInt(cursor.getColumnIndex("tipo_capacitacion_tipo")));
                horarioEntity.setMarcacion_id(cursor.getInt(cursor.getColumnIndex("marcacion_id")));
                horarioEntity.setHora_fin(cursor.getString(cursor.getColumnIndex("hora_fin")));
                horarioEntity.setFecha(cursor.getString(cursor.getColumnIndex("fecha")));
                horarioEntity.setHora_inicio(cursor.getString(cursor.getColumnIndex("hora_inicio")));
            } else {
                horarioEntity = null;
                Log.v(TAG, "Horario not found");
            }
        } catch (Exception ex){;
            ex.printStackTrace();
            horarioEntity = null;
            Log.e(TAG, "Error database");
        } finally {
            Log.v(TAG, "End get horario");
            cursor.close();
            closeDBHelper();
        }
        return horarioEntity;
    }
}

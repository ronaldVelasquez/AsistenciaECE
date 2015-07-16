package com.inei.asistenciaece.DAO;

import android.content.Context;
import android.util.Log;

import com.inei.asistenciaece.Entity.LocalEntity;

public class LocalDao extends BaseDAO {
    private static final String TAG = LocalDao.class.getSimpleName();
    private static LocalDao localDao;

    public synchronized static LocalDao getInstance(Context context) {
        if (localDao == null) {
            localDao = new LocalDao(context);
        }
        return localDao;
    }

    public LocalDao(Context context){
        initDBHelper(context);
    }

    public LocalEntity getLocal(int id_local){
        Log.v(TAG, "Start getLocal");
        LocalEntity localEntity = new LocalEntity();
        try{
            openDBHelper();
            SQL = "select * from local where id_local like " + id_local;
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if(cursor.moveToFirst()){
                Log.v(TAG, "Local found");
                localEntity.setId_local(cursor.getInt(cursor.getColumnIndex("id_local")));
                localEntity.setDireccion(cursor.getString(cursor.getColumnIndex("direccion")));
                localEntity.setNombre_local(cursor.getString(cursor.getColumnIndex("nombre_local")));
            } else {
                Log.v(TAG, "Local not found");
            }
        } catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Error when search Local");
        } finally {
            cursor.close();
            closeDBHelper();
            Log.e(TAG, "End getLocal");
        }
        return localEntity;
    }
}

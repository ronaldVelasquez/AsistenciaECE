package com.inei.asistenciaece.DAO;

import android.content.Context;
import android.util.Log;

import com.inei.asistenciaece.Entity.CargoEntity;

public class CargoDao extends BaseDAO {
    private static final String TAG = CargoDao.class.getSimpleName();
    private static CargoDao cargoDao;

    public synchronized static CargoDao getInstance(Context context) {
        if (cargoDao == null) {
            cargoDao = new CargoDao(context);
        }
        return cargoDao;
    }

    public CargoDao(Context context) {
        initDBHelper(context);
    }

    public CargoEntity getCargo(int id_cargo){
        Log.v(TAG, "Start getCargo");
        CargoEntity cargoEntity = new CargoEntity();
        try{
            openDBHelper();
            SQL = "select * from cargo where id_cargo like " + id_cargo;
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if(cursor.moveToFirst()){
                Log.v(TAG, "Cargo found");
                cargoEntity.setId_cargo(cursor.getInt(cursor.getColumnIndex("id_cargo")));
                cargoEntity.setCargo(cursor.getString(cursor.getColumnIndex("cargo")));
                cargoEntity.setCargo_res(cursor.getString(cursor.getColumnIndex("cargo_res")));
                cargoEntity.setSigla(cursor.getString(cursor.getColumnIndex("sigla")));
                cargoEntity.setNivel(cursor.getString(cursor.getColumnIndex("nivel")));
            } else {
                Log.v(TAG, "Cargo not found");
            }
        } catch (Exception ex){
            ex.printStackTrace();
            cargoEntity = null;
        } finally {
            cursor.close();
            closeDBHelper();
            Log.v(TAG, "End getCargo");
        }
        return cargoEntity;
    }

}

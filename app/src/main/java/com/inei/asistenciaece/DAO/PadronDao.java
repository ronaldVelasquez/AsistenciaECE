package com.inei.asistenciaece.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.inei.asistenciaece.Entity.CargoEntity;
import com.inei.asistenciaece.Entity.DataEntity;
import com.inei.asistenciaece.Entity.LocalEntity;
import com.inei.asistenciaece.Entity.PadronEntity;
import com.inei.asistenciaece.Entity.PostulanteEntity;
import com.inei.asistenciaece.Entity.RolEntity;
import com.inei.asistenciaece.Entity.VersionEntity;

import java.util.ArrayList;

public class PadronDao extends BaseDAO {

    private static final String TAG = PadronDao.class.getSimpleName();
    private static PadronDao padronDao;

    public synchronized static PadronDao getInstance(Context context) {
        if (padronDao == null) {
            padronDao = new PadronDao(context);
        }
        return padronDao;
    }

    public PadronDao(Context context) {
        initDBHelper(context);
    }

    public boolean addPadron(PadronEntity padronEntity) {

        Log.v(TAG, "Start addPadron");
        boolean flag;
        try {
            openDBHelper();
            deletePadron();

//            Insert postulantes
            if (!padronEntity.getPostulantes().isEmpty()) {
                Log.v(TAG, "Insert postulantes");
                for (PostulanteEntity postulanteEntity : padronEntity.getPostulantes()) {
                    contentValues = new ContentValues();
                    contentValues.put("id_local", postulanteEntity.getId_local());
                    contentValues.put("id_cargo", postulanteEntity.getId_cargo());
                    contentValues.put("dni", postulanteEntity.getDni());
                    contentValues.put("ape_nom", postulanteEntity.getApe_nom());
                    contentValues.put("nro_aula", postulanteEntity.getNro_aula());
                    contentValues.put("lugar_asigna", postulanteEntity.getLugar_asigna());
                    contentValues.put("m1_estado", postulanteEntity.getM1_estado());
                    contentValues.put("m1_fecha", postulanteEntity.getM1_fecha());
                    contentValues.put("m2_estado", postulanteEntity.getM2_estado());
                    contentValues.put("m2_fecha", postulanteEntity.getM2_fecha());
                    dbHelper.getDatabase().insertWithOnConflict("postulante", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Postulantes null");
            }

//            Insert local
            if (!padronEntity.getLocal().isEmpty()) {
                Log.v(TAG, "Insert local");
                for (LocalEntity localEntity : padronEntity.getLocal()) {
                    contentValues = new ContentValues();
                    contentValues.put("id_local", localEntity.getId_local());
                    contentValues.put("nombre_local", localEntity.getNombre_local());
                    contentValues.put("direccion", localEntity.getDireccion());
                    dbHelper.getDatabase().insertWithOnConflict("local", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Local null");
            }

//            Insert cargo
            if (!padronEntity.getCargo().isEmpty()) {
                Log.v(TAG, "Insert cargo");
                for (CargoEntity cargoEntity : padronEntity.getCargo()) {
                    contentValues = new ContentValues();
                    contentValues.put("id_cargo", cargoEntity.getId_cargo());
                    contentValues.put("cargo", cargoEntity.getCargo());
                    contentValues.put("cargo_res", cargoEntity.getCargo_res());
                    contentValues.put("sigla", cargoEntity.getSigla());
                    contentValues.put("nivel", cargoEntity.getNivel());
                    dbHelper.getDatabase().insertWithOnConflict("cargo", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Cargo null");
            }

//            Insert Rol
            if (!padronEntity.getRol().isEmpty()) {
                Log.v(TAG, "Insert rol");
                for (RolEntity rolEntity : padronEntity.getRol()) {
                    contentValues = new ContentValues();
                    contentValues.put("idRol", rolEntity.getIdRol());
                    contentValues.put("rol", rolEntity.getRol());
                    contentValues.put("descripcion", rolEntity.getDescripcion());
                    dbHelper.getDatabase().insertWithOnConflict("rol", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Rol null");
            }

//            Insert Version
            Log.v(TAG, "Insert version");
            VersionEntity versionEntity = padronEntity.getVersion();
            contentValues = new ContentValues();
            contentValues.put("nro_version", versionEntity.getNro_version());
            contentValues.put("usuarioCrea", versionEntity.getUsuarioCrea());
            contentValues.put("fechaCrea", versionEntity.getFechaCrea());
            dbHelper.getDatabase().insertWithOnConflict("version", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            dbHelper.setTransactionSuccessful();
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(TAG, "Error when save data padron");
            flag = false;
        } finally {
            closeDBHelper();
            Log.v(TAG, "End addPadron");
        }
        return flag;
    }

    public void deletePadron() {
        try {
            Log.v(TAG, "Star delete Padron");
            dbHelper.getDatabase().delete("version", null, null);
            dbHelper.getDatabase().delete("postulante", null, null);
            dbHelper.getDatabase().delete("rol", null, null);
            dbHelper.getDatabase().delete("cargo", null, null);
            dbHelper.getDatabase().delete("local", null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(TAG, "Error when deleted padron");
            closeDBHelper();
        }
    }

    public ArrayList<PostulanteEntity> getPadronSync() {
        ArrayList<PostulanteEntity> arrayPostulates = new ArrayList<>();
        Log.v(TAG, "Start get PadronSync");
        try {
            openDBHelper();
            SQL = "select * from postulante where m1_estado like " + 1;
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    PostulanteEntity postulanteEntity = new PostulanteEntity();
                    postulanteEntity.setId_cargo(cursor.getInt(cursor.getColumnIndex("id_cargo")));
                    postulanteEntity.setId_local(cursor.getInt(cursor.getColumnIndex("id_local")));
                    postulanteEntity.setDni(cursor.getString(cursor.getColumnIndex("dni")));
                    postulanteEntity.setApe_nom(cursor.getString(cursor.getColumnIndex("ape_nom")));
                    postulanteEntity.setNro_aula(cursor.getString(cursor.getColumnIndex("nro_aula")));
                    postulanteEntity.setM1_estado(cursor.getInt(cursor.getColumnIndex("m1_estado")));
                    postulanteEntity.setLugar_asigna(cursor.getString(cursor.getColumnIndex("lugar_asigna")));
                    postulanteEntity.setM1_fecha(cursor.getString(cursor.getColumnIndex("m1_fecha")));
                    arrayPostulates.add(postulanteEntity);
                    cursor.moveToNext();
                }
            } else {
                Log.v(TAG, "Not found postulantes");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(TAG, "Error when search postulante");
        } finally {
            Log.v(TAG, "End getPadronSync");
            cursor.close();
            closeDBHelper();
        }
        return arrayPostulates;
    }

    public void setDataSync(DataEntity dataEntity) {
        Log.v(TAG, "Start setDatasync");
        try{
            openDBHelper();
            if (!dataEntity.getPostulantes().isEmpty()){
                for(PostulanteEntity postulanteEntity : dataEntity.getPostulantes()){
                    contentValues = new ContentValues();
                    contentValues.put("m1_estado", postulanteEntity.getM1_estado());
                    String where = "dni like '" + postulanteEntity.getDni() + "'";
                    dbHelper.getDatabase().updateWithOnConflict("postulante", contentValues, where, null, SQLiteDatabase.CONFLICT_IGNORE);
                }
                dbHelper.setTransactionSuccessful();
            } else {
                Log.v(TAG, "Not found data");
            }

        } catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Error when set data");
        } finally {
            Log.v(TAG, "End setDataSync");
            cursor.close();
            closeDBHelper();
        }
    }
}

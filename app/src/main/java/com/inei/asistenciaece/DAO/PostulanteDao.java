package com.inei.asistenciaece.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.inei.asistenciaece.Entity.HorarioEntity;
import com.inei.asistenciaece.Entity.PostulanteEntity;
import com.inei.asistenciaece.Utils.DateFormatUtil;
import com.inei.asistenciaece.Utils.ReportItem;

import java.util.ArrayList;

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

    /*public PostulanteEntity checkPresence(String dni, HorarioEntity horarioEntity, String dateNow, String timeNow){
        Log.v(TAG, "Star checkPrensence");
        PostulanteEntity postulanteEntity = new PostulanteEntity();
        try{
            openDBHelper();
            String dateTimeStart = horarioEntity.getFecha() + " " + horarioEntity.getHora_inicio();
            String dateTimeFinish = horarioEntity.getFecha() + " " + horarioEntity.getHora_fin();
            SQL = "select * from postulante_asistencia where dni = '" + dni + "' and (fecha >= date('" + dateTimeStart + "') and fecha <= date('"+ dateTimeFinish + "')";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                postulanteEntity.

                if (postulanteEntity.getM1_estado() == 0){
                    Log.v(TAG, "Checking Presence");
                    contentValues = new ContentValues();
                    contentValues.put("m1_estado", 1);
                    contentValues.put("m1_fecha", DateFormatUtil.getDate());
                    String where = "dni like '" + dni + "'";
                    dbHelper.getDatabase().updateWithOnConflict("postulante", contentValues, where, null, SQLiteDatabase.CONFLICT_IGNORE);
                    dbHelper.setTransactionSuccessful();
                } else {
                    Log.v(TAG, "Checked Presence");
                }
            } else {
                postulanteEntity.setM1_estado(3);
                Log.v(TAG, "Postulante not found");
            }
        } catch (Exception ex) {
            postulanteEntity.setM1_estado(4);
            ex.printStackTrace();
            Log.e(TAG, "Error database");
        } finally {
            Log.v(TAG, "End checkPresence");
            cursor.close();
            closeDBHelper();
        }
        return postulanteEntity;
    }*/

   /* public PostulanteEntity checkPresence(String dni, String aula){
        Log.v(TAG, "Star checkPrensence");
        PostulanteEntity postulanteEntity = new PostulanteEntity();
        try{
            openDBHelper();
            SQL = "select * from postulante where dni like '" + dni + "'" + "and nro_aula like '" + aula + "'";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                postulanteEntity.setM2_estado(cursor.getInt(cursor.getColumnIndex("m2_estado")));
                postulanteEntity.setId_cargo(cursor.getInt(cursor.getColumnIndex("id_cargo")));
                postulanteEntity.setId_local(cursor.getInt(cursor.getColumnIndex("id_local")));
                postulanteEntity.setDni(cursor.getString(cursor.getColumnIndex("dni")));
                postulanteEntity.setApe_nom(cursor.getString(cursor.getColumnIndex("ape_nom")));
                postulanteEntity.setNro_aula(cursor.getString(cursor.getColumnIndex("nro_aula")));

                if (postulanteEntity.getM2_estado() == 0){
                    Log.v(TAG, "Checking Presence");
                    contentValues = new ContentValues();
                    contentValues.put("m2_estado", 1);
                    contentValues.put("m2_fecha", DateFormatUtil.getDate());
                    String where = "dni like '" + dni + "'";
                    dbHelper.getDatabase().updateWithOnConflict("postulante", contentValues, where, null, SQLiteDatabase.CONFLICT_IGNORE);
                    dbHelper.setTransactionSuccessful();
                } else {
                    Log.v(TAG, "Checked Presence");
                }
            } else {
                postulanteEntity.setM2_estado(3);
                Log.v(TAG, "Postulante not found");
            }
        } catch (Exception ex) {
            postulanteEntity.setM2_estado(4);
            ex.printStackTrace();
            Log.e(TAG, "Error database");
        } finally {
            Log.v(TAG, "End checkPresence");
            cursor.close();
            closeDBHelper();
        }
        return postulanteEntity;
    }*/

    public ArrayList<ReportItem> getReportLocal() {
        Log.v(TAG, "Start getReport");
        ArrayList<ReportItem> reportItems = new ArrayList<>();
        try {
            openDBHelper();
            SQL = "select distinct " +
                    "pos.nro_aula, " +
                    "(select count(*) from postulante where nro_aula=pos.nro_aula group by nro_aula) AS totales, " +
                    "ifnull((select count(nro_aula) from postulante where m1_estado IN(1,2) AND nro_aula=pos.nro_aula group by nro_aula),0) AS registrados, " +
                    "ifnull((select count(nro_aula)  from postulante where m1_estado=0 AND nro_aula=pos.nro_aula group by nro_aula),0) AS no_registrados, " +
                    "ifnull((select count(nro_aula) from postulante where m1_estado=2 AND nro_aula=pos.nro_aula group by nro_aula),0) AS sincronizados " +
                    "from " +
                    "postulante as pos " +
                    "left join local as loc on pos.id_local=loc.id_local " +
                    "group by pos.m1_estado,pos.nro_aula order by nro_aula";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    ReportItem reportItem = new ReportItem();
                    reportItem.setNroClasses(cursor.getString(cursor.getColumnIndex("nro_aula")));
                    reportItem.setNroAsign(cursor.getInt(cursor.getColumnIndex("totales")));
                    reportItem.setNroRegister(cursor.getInt(cursor.getColumnIndex("registrados")));
                    reportItem.setNroNoRegister(cursor.getInt(cursor.getColumnIndex("no_registrados")));
                    reportItem.setNroSync(cursor.getInt(cursor.getColumnIndex("sincronizados")));
                    reportItems.add(reportItem);
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Error when get Report");
        }finally {
            Log.v(TAG, "End getReport");
            cursor.close();
            closeDBHelper();
        }
        return reportItems;
    }

    public ArrayList<ReportItem> getReportClasses() {
        Log.v(TAG, "Start getReport");
        ArrayList<ReportItem> reportItems = new ArrayList<>();
        try {
            openDBHelper();
            SQL = "select distinct " +
                    "pos.nro_aula, " +
                    "(select count(*) from postulante where nro_aula=pos.nro_aula group by nro_aula) AS totales, " +
                    "ifnull((select count(nro_aula) from postulante where m2_estado IN(1,2) AND nro_aula=pos.nro_aula group by nro_aula),0) AS registrados, " +
                    "ifnull((select count(nro_aula)  from postulante where m2_estado=0 AND nro_aula=pos.nro_aula group by nro_aula),0) AS no_registrados, " +
                    "ifnull((select count(nro_aula) from postulante where m2_estado=2 AND nro_aula=pos.nro_aula group by nro_aula),0) AS sincronizados " +
                    "from " +
                    "postulante as pos " +
                    "left join local as loc on pos.id_local=loc.id_local " +
                    "group by pos.m1_estado,pos.nro_aula order by nro_aula";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    ReportItem reportItem = new ReportItem();
                    reportItem.setNroClasses(cursor.getString(cursor.getColumnIndex("nro_aula")));
                    reportItem.setNroAsign(cursor.getInt(cursor.getColumnIndex("totales")));
                    reportItem.setNroRegister(cursor.getInt(cursor.getColumnIndex("registrados")));
                    reportItem.setNroNoRegister(cursor.getInt(cursor.getColumnIndex("no_registrados")));
                    reportItem.setNroSync(cursor.getInt(cursor.getColumnIndex("sincronizados")));
                    reportItems.add(reportItem);
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Error when get Report");
        }finally {
            Log.v(TAG, "End getReport");
            cursor.close();
            closeDBHelper();
        }
        return reportItems;
    }

    public ArrayList<String> getClasses() {
        Log.v(TAG, "Start getClasses");
        ArrayList<String> classes = new ArrayList<>();
        try {
            openDBHelper();
            SQL = "select distinct nro_aula from postulante";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    classes.add(cursor.getString(cursor.getColumnIndex("nro_aula")));
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Error when get classes");
        } finally {
            Log.v(TAG, "End getClasses");
            cursor.close();
            closeDBHelper();
        }
        return classes;
    }

    public PostulanteEntity checkPostulante(String dni) {

        PostulanteEntity postulanteEntity = new PostulanteEntity();
        Log.v(TAG, "Start check Postulante");
        try{
            openDBHelper();
            SQL = "select * from postulante where dni = '" + dni + "'";
            cursor  = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                postulanteEntity.setDni(cursor.getString(cursor.getColumnIndex("dni")));
                postulanteEntity.setNro_version(cursor.getInt(cursor.getColumnIndex("nro_version")));
                postulanteEntity.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                postulanteEntity.setCargo_id(cursor.getInt(cursor.getColumnIndex("cargo_id")));
                postulanteEntity.setSede_id(cursor.getString(cursor.getColumnIndex("sede_id")));
                postulanteEntity.setApellidos_nombres(cursor.getString(cursor.getColumnIndex("apellidos_nombres")));
                postulanteEntity.setId(cursor.getInt(cursor.getColumnIndex("id")));
                postulanteEntity.setNumero_aula(cursor.getInt(cursor.getColumnIndex("numero_aula")));
                postulanteEntity.setNumero_bungalow(cursor.getString(cursor.getColumnIndex("numero_bungalow")));
            } else  {
                postulanteEntity = null;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            Log.v(TAG, "Error database");
            postulanteEntity = null;
        } finally {
            cursor.close();
            closeDBHelper();
        }
        return postulanteEntity;

    }
}

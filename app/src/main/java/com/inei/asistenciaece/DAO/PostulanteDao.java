package com.inei.asistenciaece.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public PostulanteEntity checkPresence(String dni){
        Log.v(TAG, "Star checkPrensence");
        PostulanteEntity postulanteEntity = new PostulanteEntity();
        try{
            openDBHelper();
            SQL = "select * from postulante where dni like '" + dni + "'";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                postulanteEntity.setM1_estado(cursor.getInt(cursor.getColumnIndex("m1_estado")));
                postulanteEntity.setId_cargo(cursor.getInt(cursor.getColumnIndex("id_cargo")));
                postulanteEntity.setId_local(cursor.getInt(cursor.getColumnIndex("id_local")));
                postulanteEntity.setDni(cursor.getString(cursor.getColumnIndex("dni")));
                postulanteEntity.setApe_nom(cursor.getString(cursor.getColumnIndex("ape_nom")));
                postulanteEntity.setNro_aula(cursor.getString(cursor.getColumnIndex("nro_aula")));

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
    }

    public ArrayList<ReportItem> getReport() {
        ArrayList<ReportItem> reportItems = new ArrayList<>();
        try {
            openDBHelper();
            SQL = "select distinct nro_aula from postulante";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    ReportItem reportItem = new ReportItem();
                    String nroAula = cursor.getString(cursor.getColumnIndex("nro_aula"));
                }
            }
        } catch (Exception ex){

        }finally {
            cursor.close();
            closeDBHelper();
        }
        return reportItems;
    }
}

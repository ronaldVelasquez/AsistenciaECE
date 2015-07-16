package com.inei.asistenciaece.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.inei.asistenciaece.Entity.VersionEntity;

public class VersionDao extends BaseDAO {

    private static final String TAG = VersionDao.class.getSimpleName();
    private static VersionDao versionDao;

    public synchronized static VersionDao getInstance(Context context) {
        if (versionDao == null) {
            versionDao = new VersionDao(context);
        }
        return versionDao;
    }

    public VersionDao(Context context) {
        initDBHelper(context);
    }

    public VersionEntity getVersion() {
        VersionEntity versionEntity = new VersionEntity();
        try {
            openDBHelper();
            Log.v(TAG, "Start getVersion");
            SQL = "select * from version";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if(cursor.moveToFirst()){
                versionEntity.setNro_version(cursor.getInt(cursor.getColumnIndex("nro_version")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            versionEntity = null;
            Log.e(TAG, "Error when getting version");
        } finally {
            Log.v(TAG, "End getVersion");
            cursor.close();
            closeDBHelper();
        }
        return versionEntity;
    }

    public void addVersion(VersionEntity versionEntity){
        deleteVersion();
        try{
            openDBHelper();
            Log.v(TAG, "Start addVersion");
            contentValues = new ContentValues();
            contentValues.put("nro_version", versionEntity.getNro_version());
            contentValues.put("usuarioCrea", versionEntity.getUsuarioCrea());
            contentValues.put("fechaCrea", versionEntity.getFechaCrea());
            dbHelper.getDatabase().insertWithOnConflict("version", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            dbHelper.setTransactionSuccessful();
        } catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Error when adding version");
        } finally {
            Log.v(TAG, "End addVersion");
            cursor.close();
            closeDBHelper();
        }

    }
    public void deleteVersion(){
        try{
            openDBHelper();
            Log.v(TAG, "Start delete Version");
            dbHelper.getDatabase().delete( "version", null, null );
        }catch (Exception e){
            Log.e(TAG, "Error delete Version");
            e.printStackTrace();
        } finally {
            closeDBHelper();
            Log.v(TAG, "End delete Version");
        }
    }
}

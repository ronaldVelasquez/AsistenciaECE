package com.inei.asistenciaece.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.inei.asistenciaece.database.DBHelper;

public class BaseDAO {

    private static final String TAG = BaseDAO.class.getSimpleName();

    public DBHelper dbHelper;

    public String SQL;
    public Cursor cursor = null;
    public ContentValues contentValues = null;

    public void initDBHelper(Context paramContext) {

        dbHelper = DBHelper.getUtilDb( paramContext );

    }

    public synchronized void openDBHelper() {

        try
        {
            dbHelper.openDataBase();
            dbHelper.beginTransaction();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e(TAG, "openDBHelper : " + e.toString());
        }

    }

    public synchronized void closeDBHelper() {

        try
        {
            dbHelper.endTransaction();
            dbHelper.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e(TAG, "closeDBHelper : " + e.toString());
        }

    }


}

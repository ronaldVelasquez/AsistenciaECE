package com.inei.asistenciaece.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = DBHelper.class.getSimpleName();

    private static DBHelper dbHelper;

    public static final String DB_NAME = "ece.sqlite";
    public static final int DB_VERSION = 1;
    private String DB_PATH = null;
    private Context mContext;


    private DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
        DB_PATH = "data/data/" + mContext.getPackageName() + "/databases/";
        //DB_PATH = "storage/sdcard0/" + mContext.getPackageName();
    }

    public static DBHelper getUtilDb( Context context )
    {
        dbHelper = new DBHelper(context);

        if (!dbHelper.isDataBaseExist())
        {
            try
            {
                dbHelper.createDataBase();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        return dbHelper;
    }

    public boolean isDataBaseExist()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        Log.d(TAG, "The database exist: " + String.valueOf(dbFile.exists()));
        return dbFile.exists();
    }

    public void createDataBase() throws IOException
    {
        boolean isExistDB = isDataBaseExist();
        if (!isExistDB)
        {
            this.getReadableDatabase();
            try
            {
                copyDataBase();
            }
            catch (Exception e)
            {
                throw new Error("Error copying database");
            }
        }
    }

    private void copyDataBase() throws IOException
    {
        String outFileName = DB_PATH + DB_NAME;
        InputStream inputStream = mContext.getAssets().open("databases/"+DB_NAME);
        OutputStream outputStream = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;

        while ( (length = inputStream.read(buffer)) > 0 )
        {
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }

    private SQLiteDatabase database;

    public SQLiteDatabase getDatabase()
    {
        return  database;
    }

    public synchronized void openDataBase() throws SQLException
    {
            String myPath = DB_PATH + DB_NAME;
            database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING & SQLiteDatabase.OPEN_READWRITE);
    }

    public void beginTransaction()
    {
        database.beginTransaction();
    }

    public void setTransactionSuccessful()
    {
        database.setTransactionSuccessful();
    }

    public void endTransaction()
    {
        database.endTransaction();
    }

    @Override
    public synchronized void close()
    {
        if (database != null) database.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
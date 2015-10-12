package com.inei.asistenciaece.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.inei.asistenciaece.Entity.UserEntity;

public class UserDao extends BaseDAO {
    private static final String TAG = UserDao.class.getSimpleName();
    private static UserDao userDao;

    public synchronized static UserDao getInstance(Context context) {
        if (userDao == null) {
            userDao = new UserDao(context);
        }
        return userDao;
    }

    public UserDao(Context context) {
        initDBHelper(context);
    }

    public boolean addUser(UserEntity userEntity) {
        deleteUser();
        boolean flag;
        try {
            openDBHelper();
            Log.v(TAG, "Start addUser");
            SQL = "select * from usuario where idUsu like '" + userEntity.getIdUsu() + "'";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()) {
                Log.v(TAG, "User exist");
            } else {
                contentValues = new ContentValues();
                contentValues.put("idUsu", userEntity.getIdUsu());
                contentValues.put("idRol", userEntity.getIdRol());
                contentValues.put("usuario", userEntity.getUsuario());
                contentValues.put("estado", userEntity.getEstado());
                contentValues.put("rol", userEntity.getRol());
                contentValues.put("descripcion", userEntity.getDescripcion());
                contentValues.put("clave", userEntity.getPassword());
                dbHelper.getDatabase().insert("usuario", null, contentValues);
                Log.v(TAG, "add user");
                dbHelper.setTransactionSuccessful();
            }
            flag = true;
        } catch (Exception ex) {
            flag = false;
            ex.printStackTrace();
            Log.e(TAG, "Error when adding user");
        } finally {
            Log.v(TAG, "End addUser");
            cursor.close();
            closeDBHelper();
        }
        return flag;
    }

    public UserEntity searchUser(String password, String username) {
        UserEntity userEntity = new UserEntity();
        try {
            openDBHelper();
            Log.v(TAG, "Start seachUser");
            SQL = "select * from usuario where clave like '" + password + "' and usuario like '" + username + "'";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()) {
                Log.v(TAG, "User found");
                userEntity.setIdUsu(cursor.getInt(cursor.getColumnIndex("idUsu")));
                userEntity.setIdRol(cursor.getInt(cursor.getColumnIndex("idRol")));
                userEntity.setUsuario(cursor.getString(cursor.getColumnIndex("usuario")));
                userEntity.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
                userEntity.setRol(cursor.getString(cursor.getColumnIndex("rol")));
                userEntity.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                userEntity.setPassword(cursor.getString(cursor.getColumnIndex("clave")));
            } else {
                userEntity = null;
            }
        } catch (Exception ex) {
            userEntity = null;
            ex.printStackTrace();
            Log.e(TAG, "Error when searching user");
        } finally {
            Log.v(TAG, "End searchUser");
            cursor.close();
            closeDBHelper();
        }
        return userEntity;
    }

    public void deleteUser() {
        try{
            openDBHelper();
            Log.v(TAG, "Start delete usuario");
            dbHelper.getDatabase().delete( "usuario", null, null );
            dbHelper.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(TAG, "Error delete Usuario");
            e.printStackTrace();
        } finally {
            closeDBHelper();
            Log.v(TAG, "End delete Usuario");
        }
    }


}

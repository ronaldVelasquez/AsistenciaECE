package com.inei.asistenciaece.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashMap;

public class SessionManager {
    public SharedPreferences sharedPreferences;
    public Editor editor;
    public Context context;

    public static final String PREF_NAME = "ECE";
    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_USUARIO = "usuario";
    public static final String KEY_IDUSU = "idUsu";
    public static final String KEY_IDROL = "idRol";
    public static final String KEY_ESTADO = "estado";
    public static final String KEY_PASSWORD = "password";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String usuario, int idUsu, int idRol, String estado, String password){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USUARIO, usuario);
        editor.putInt(KEY_IDUSU, idUsu);
        editor.putInt(KEY_IDROL, idRol);
        editor.putString(KEY_ESTADO, estado);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    /*public boolean checkLogin(){
        if(!this.isLoggedIn()){
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } else {
            return false;
        }
    }*/

    public HashMap<String, Object> getUserDetails(){
        HashMap<String, Object> user = new HashMap<String, Object>();
        user.put(KEY_USUARIO, sharedPreferences.getString(KEY_USUARIO, null));
        user.put(KEY_IDUSU, sharedPreferences.getInt(KEY_IDUSU, 0));
        user.put(KEY_IDROL, sharedPreferences.getInt(KEY_IDROL, 0));
        user.put(KEY_ESTADO, sharedPreferences.getString(KEY_ESTADO, null));
        user.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD, null));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        /*Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);*/
    }

    /*public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }*/
}

package com.inei.asistenciaece.Business;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.inei.asistenciaece.DAO.UserDao;
import com.inei.asistenciaece.Entity.UserEntity;
import com.inei.asistenciaece.MainActivity;
import com.inei.asistenciaece.Utils.SessionManager;

public class UserBusiness {
    private static final String TAG = UserBusiness.class.getSimpleName();
    private Context context;
    private UserDao userDao;
    private SessionManager sessionManager;
    private VersionBusiness versionBusiness;

    public UserBusiness(Context context) {
        this.context = context;
        userDao = UserDao.getInstance(context);
    }

    public void addUser(UserEntity userEntity){
        Log.v(TAG, "Start addUser");
        boolean flag = userDao.addUser(userEntity);
        if(flag){
            sessionManager = new SessionManager(context);
            sessionManager.createLoginSession(userEntity.getUsuario(), userEntity.getIdUsu(), userEntity.getIdRol(), userEntity.getEstado(), userEntity.getPassword());

        }
        Log.v(TAG, "End addUser");
    }

    public void searchUser(String password) {
        UserEntity userEntity = userDao.searchUser(password);
        if(userEntity == null){
            Toast.makeText(context, "El password ingresado es incorrecto", Toast.LENGTH_SHORT).show();
        } else {
            sessionManager = new SessionManager(context);
            sessionManager.createLoginSession(userEntity.getUsuario(), userEntity.getIdUsu(), userEntity.getIdRol(), userEntity.getEstado(), userEntity.getPassword());
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }
}

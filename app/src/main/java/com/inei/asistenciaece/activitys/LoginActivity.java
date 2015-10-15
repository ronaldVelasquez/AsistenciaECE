package com.inei.asistenciaece.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.dd.CircularProgressButton;
import com.google.gson.Gson;
import com.inei.asistenciaece.Business.UserBusiness;
import com.inei.asistenciaece.Entity.UserEntity;
import com.inei.asistenciaece.R;
import com.inei.asistenciaece.Utils.ConstantsUtils;
import com.inei.asistenciaece.Utils.SessionManager;

import org.json.JSONObject;
import java.util.HashMap;

public class LoginActivity extends Activity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText edtxPassword;
    private EditText edtxUsername;
    private CircularProgressButton btnLogin;
    private Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        btnLogin = (CircularProgressButton) findViewById(R.id.btn_login);
        TextView txtVersion = (TextView) findViewById(R.id.txt_app_version);
        edtxPassword = (EditText) findViewById(R.id.edtx_password);
        edtxUsername = (EditText) findViewById(R.id.edtx_username);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        btnLogin.setIndeterminateProgressMode(true);

        snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_LONG)
                .setAction("Deshacer", null)
                .setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Click button Login");

                btnLogin.setClickable(false);
                if (btnLogin.getProgress() == 0){
                    btnLogin.setProgress(50);
                } else if (btnLogin.getProgress() == 100){
                    btnLogin.setProgress(0);
                } else {
                    btnLogin.setProgress(100);
                }


                String password = edtxPassword.getText().toString();
                String username = edtxUsername.getText().toString();
                if (password.isEmpty() || username.isEmpty()) {
                    Log.e(TAG, "Password Empty");
                    btnLogin.setProgress(-1);
//                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Completa todos los campos para iniciar sesión", Toast.LENGTH_SHORT).show();
                    snackbar.setText("Completa todos los campos para iniciar sesión")
                            .show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnLogin.setProgress(0);
                            btnLogin.setClickable(true);
                        }
                    }, 2000);
                } else {
                    Log.v(TAG, "Send Password and Send username");
                    /*progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setIcon(R.drawable.abc_item_background_holo_dark);
                    progressDialog.setTitle("Espere un momento...");
                    progressDialog.setMessage("Validando la sesión");*/
                    sendRequest(password, username);
                }
            }
        });

        try {
            String version = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
            txtVersion.setText("Versión " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendRequest(final String password, final String username) {
        RequestQueue queue = Volley.newRequestQueue(this);

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("username", username);
        parameters.put("password", password);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ConstantsUtils.URL_LOGIN, new JSONObject(parameters), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.v(TAG, "json : " + jsonObject.toString());
                if (jsonObject.equals(new JSONObject(ConstantsUtils.parameterError))) {
                    Log.e(TAG, "Incorrect password");
//                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Password Incorrecto", Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
                    btnLogin.setProgress(-1);
                    snackbar.setText("Password Incorrecto")
                            .show();
                } else {
                    try {

                        Log.v(TAG, "Correct password");
                        Gson gson = new Gson();
                        UserEntity userEntity = gson.fromJson(jsonObject.getJSONObject("usuario").toString(), UserEntity.class);
                        userEntity.setPassword(password);
                        UserBusiness userBusiness = new UserBusiness(getApplicationContext());
                        btnLogin.setProgress(100);
                        userBusiness.addUser(userEntity);
                    } catch (Exception ex) {
//                        Toast.makeText(LoginActivity.this.getApplicationContext(), "Error de sistema", Toast.LENGTH_SHORT).show();
                        snackbar.setText("Error de sistema")
                                .show();
                        Log.e(TAG, "Error parsing json");
//                        progressDialog.dismiss();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                btnLogin.setProgress(-1);
                Log.e(TAG, volleyError.toString());
                if (volleyError instanceof NoConnectionError) {
                    UserBusiness userBusiness = new UserBusiness(LoginActivity.this.getApplicationContext());
//                    progressDialog.dismiss();
                    userBusiness.searchUser(password, username);
                } else {
//                    progressDialog.dismiss();
                    //Toast.makeText(LoginActivity.this.getApplicationContext(), "Los datos ingresados son incorrectos", Toast.LENGTH_SHORT).show();
                    snackbar.setText("Los datos ingresados son incorrectos")
                            .show();
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnLogin.setProgress(0);
                        btnLogin.setClickable(true);
                    }
                }, 2000);
            }
        });
        queue.add(jsonObjectRequest);
//        progressDialog.show();
    }


}

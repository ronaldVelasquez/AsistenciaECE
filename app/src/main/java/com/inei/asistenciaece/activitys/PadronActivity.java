package com.inei.asistenciaece.activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.inei.asistenciaece.Business.PadronBusiness;
import com.inei.asistenciaece.Business.UserBusiness;
import com.inei.asistenciaece.Business.VersionBusiness;
import com.inei.asistenciaece.DAO.PadronDao;
import com.inei.asistenciaece.Entity.PadronEntity;
import com.inei.asistenciaece.R;
import com.inei.asistenciaece.Utils.ConstantsUtils;
import com.inei.asistenciaece.Utils.SessionManager;

import org.json.JSONObject;

import java.util.HashMap;


public class PadronActivity extends Activity {
    private ImageButton imageButton;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private static final String TAG = PadronActivity.class.getSimpleName();
    private int idLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padron);
//        GetSession
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, Object> user = sessionManager.getUserDetails();
        idLocal = (int) user.get(SessionManager.KEY_ID_LOCAL);

//        Instace progress dialog
        progressDialog = new ProgressDialog(PadronActivity.this);
        progressDialog.setMessage("Descargando Padron");

//        declare imagebutton
        imageButton = (ImageButton) findViewById(R.id.img_btn_download);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Start download padron");
//                Create parameters
                HashMap<String, Integer> parameters = new HashMap<String, Integer>();
                parameters.put("idLocal", idLocal);
                Log.e("adas", "" + idLocal);
                RequestQueue requestQueue = Volley.newRequestQueue(PadronActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ConstantsUtils.URL_PADRON, new JSONObject(parameters), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.v(TAG, "json:" + jsonObject.toString());
                        Gson gson = new Gson();
                        try {
                            PadronEntity padronEntity = gson.fromJson(jsonObject.getJSONObject("padron").toString(), PadronEntity.class);
                            PadronBusiness padronBusiness = new PadronBusiness(PadronActivity.this);
                            padronBusiness.addPadron(padronEntity);
                            progressDialog.dismiss();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            Toast.makeText(PadronActivity.this, "Error al almacenar datos de manera local", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Error parsing json");
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Log.e(TAG, volleyError.toString());
                        if (volleyError instanceof NoConnectionError){
                            Toast.makeText(PadronActivity.this, "No hay conexión a Internet", Toast.LENGTH_SHORT).show();
                        } else if (volleyError instanceof NetworkError) {
                            Toast.makeText(PadronActivity.this, "No hay una buena conexión de Internet, Intentelo nuevamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PadronActivity.this, "Error al obtener los datos de la nube ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                requestQueue.add(jsonObjectRequest);
                progressDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sessionManager.logoutUser();
        VersionBusiness versionBusiness = new VersionBusiness(getApplicationContext());
        versionBusiness.deleteVersion();
        UserBusiness userBusiness = new UserBusiness(getApplicationContext());
        userBusiness.deleteUser();
        Intent intent = new Intent(PadronActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

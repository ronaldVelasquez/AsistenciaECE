package com.inei.asistenciaece.Business;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.inei.asistenciaece.DAO.PadronDao;
import com.inei.asistenciaece.Entity.DataEntity;
import com.inei.asistenciaece.Entity.PadronEntity;
import com.inei.asistenciaece.Entity.PostulanteEntity;
import com.inei.asistenciaece.Utils.ConstantsUtils;
import com.inei.asistenciaece.activitys.MainActivity;
import com.inei.asistenciaece.listeners.BudaCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PadronBusiness {

    private static final String TAG = PadronBusiness.class.getSimpleName();
    private Context context;
    private PadronDao padronDao;

    public PadronBusiness(Context context) {
        this.context = context;
        padronDao = PadronDao.getInstance(context);
    }

    public void addPadron(PadronEntity padronEntity) {
        if (padronEntity == null) {
            Toast.makeText(context, "Problemas con datos en la nube", Toast.LENGTH_SHORT).show();
        } else {
            boolean flag = padronDao.addPadron(padronEntity);
            if (flag) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Error al almacenar datos de manera local", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void syncData(final BudaCallback budaCallback) {
        ArrayList<PostulanteEntity> arrayPostulantes = padronDao.getPadronSync();
        if (arrayPostulantes.isEmpty()) {
            Log.v(TAG, "Data Not found");
        } else {
            try {
                DataEntity dataEntity = new DataEntity(arrayPostulantes);
                final Gson gson = new Gson();
                String jsonList = gson.toJson(dataEntity);
                HashMap<String, JSONObject> parameters = new HashMap<>();
                parameters.put("data", new JSONObject(jsonList));
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ConstantsUtils.URL_SYNC_PADRON, new JSONObject(parameters), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.v(TAG, "json: " + jsonObject.toString());
                        DataEntity dataEntity1 = gson.fromJson(jsonObject.toString(), DataEntity.class);
                        padronDao.setDataSync(dataEntity1);
                        if (budaCallback != null){
                            budaCallback.callback();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG, volleyError.toString());
                    }
                });
                requestQueue.add(jsonObjectRequest);
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e(TAG, "Error parsing");
            }
        }
    }

    public void syncDataManual(final BudaCallback budaCallback) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Enviando Datos");
        ArrayList<PostulanteEntity> arrayPostulantes = padronDao.getPadronSync();
        if (arrayPostulantes.isEmpty()) {
            Log.v(TAG, "Data Not found");
            Toast.makeText(context, "No hay datos para sincronizar", Toast.LENGTH_SHORT).show();
        } else {
            try {
                DataEntity dataEntity = new DataEntity(arrayPostulantes);
                final Gson gson = new Gson();
                String jsonList = gson.toJson(dataEntity);
                HashMap<String, JSONObject> parameters = new HashMap<>();
                parameters.put("data", new JSONObject(jsonList));
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ConstantsUtils.URL_SYNC_PADRON, new JSONObject(parameters), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.v(TAG, "json: " + jsonObject.toString());
                        DataEntity dataEntity1 = gson.fromJson(jsonObject.toString(), DataEntity.class);
                        padronDao.setDataSync(dataEntity1);
                        progressDialog.dismiss();
                        Toast.makeText(context, "Datos enviados correctamente", Toast.LENGTH_SHORT).show();

                        if(budaCallback != null)
                            budaCallback.callback();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG, volleyError.toString());
                        progressDialog.dismiss();
                    }
                });
                requestQueue.add(jsonObjectRequest);
                progressDialog.show();
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e(TAG, "Error parsing");
                progressDialog.dismiss();
            }
        }
    }
}

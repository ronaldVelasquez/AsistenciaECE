package com.inei.asistenciaece.Business;


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
import com.inei.asistenciaece.DAO.VersionDao;
import com.inei.asistenciaece.Entity.VersionEntity;
import com.inei.asistenciaece.activitys.MainActivity;
import com.inei.asistenciaece.activitys.PadronActivity;
import com.inei.asistenciaece.Utils.ConstantsUtils;

import org.json.JSONObject;

public class VersionBusiness {
    private static final String TAG = VersionBusiness.class.getSimpleName();
    private Context context;
    private VersionDao versionDao;
    private VersionEntity versionEntity;

    public VersionBusiness (Context context){
        this.context = context;
        versionDao = VersionDao.getInstance(context);
    }

    public void addNewVersion(){
        Log.v(TAG, "Add new version");
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ConstantsUtils.URL_VERSION, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.v(TAG, "json : " + jsonObject.toString());
                try {
                    Gson gson = new Gson();
                    versionEntity = gson.fromJson(jsonObject.getJSONObject("version").toString(), VersionEntity.class);
                    int localVersion = getNroVersionLocal();
                    if(localVersion < versionEntity.getNro_version()){
                        Intent intent = new Intent(context, PadronActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                    Log.e(TAG, "Error when adding version");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);

    }
    public Integer getNroVersionLocal(){
        return versionDao.getVersion().getNro_version();
    }

    public void deleteVersion() {
        versionDao.deleteVersion();
    }
}

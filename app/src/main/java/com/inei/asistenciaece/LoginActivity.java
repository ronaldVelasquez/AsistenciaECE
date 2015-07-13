package com.inei.asistenciaece;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.widgets.ProgressDialog;
import com.google.gson.Gson;
import com.inei.asistenciaece.Business.UserBusiness;
import com.inei.asistenciaece.Entity.UserEntity;
import com.inei.asistenciaece.Utils.ConstantsUtils;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends Activity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private ButtonRectangle btnLogin;
    private EditText edtxPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (ButtonRectangle) findViewById(R.id.btn_login);
        edtxPassword = (EditText) findViewById(R.id.edtx_password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Click button Login");
                String password = edtxPassword.getText().toString();
                if (password.isEmpty()) {
                    Log.v(TAG, "Password Empty");
                } else {
                    Log.v(TAG, "Send Password");
                    ProgressDialog progressDialog = new ProgressDialog(getApplicationContext(), "Validando sesión del usuario");
                    progressDialog.setTitle("Espere un momento...");
                    progressDialog.show();
                    sendRequest(password);
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void sendRequest(final String password) {
        RequestQueue queue = Volley.newRequestQueue(this);

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("password", password);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ConstantsUtils.URL, new JSONObject(parameters), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.v(TAG, "json : " + jsonObject.toString());

                if (jsonObject.equals(new JSONObject(ConstantsUtils.parameterError))) {
                    Log.e(TAG, "Incorrect password");
                    Toast.makeText(getApplicationContext(), "Password Incorrecto", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Log.v(TAG, "Correct password");
                        Gson gson = new Gson();
                        UserEntity userEntity = gson.fromJson(jsonObject.getJSONObject("usuario").toString(), UserEntity.class);
                        userEntity.setPassword(password);
                        UserBusiness userBusiness = new UserBusiness(getApplicationContext());
                        userBusiness.addUser(userEntity);
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), "Error de sistema", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error parsing json");
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError instanceof NoConnectionError){
                    UserBusiness userBusiness = new UserBusiness(getApplicationContext());
                    userBusiness.searchUser(password);
                }
            }
        });
        queue.add(jsonObjectRequest);
    }


}

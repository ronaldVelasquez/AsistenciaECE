package com.inei.asistenciaece;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.inei.asistenciaece.service.SyncService;

public class AsistenciaApplication extends Application{
    private static final String TAG = AsistenciaApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "Start Application");
        startService(new Intent(AsistenciaApplication.this, SyncService.class));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.v(TAG, "End Application");
        stopService(new Intent(AsistenciaApplication.this, SyncService.class));
    }
}

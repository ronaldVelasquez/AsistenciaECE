package com.inei.asistenciaece.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.inei.asistenciaece.Business.PadronBusiness;
import com.inei.asistenciaece.fragments.ReportFragment;
import com.inei.asistenciaece.listeners.BudaCallback;

public class SyncService extends Service {
    private static final String TAG = SyncService.class.getSimpleName();

    static final int DELAY = 1000 * 60 * 2;
    private PadronBusiness padronBusiness;
    private boolean runFlag = false;
    private Update update;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "Create SyncService");
        padronBusiness = new PadronBusiness(this);
        update = new Update();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.v(TAG, "onStartCommand UpdaterService");
        if (!runFlag) {
            runFlag = true;
            update.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "Destroy UpdateService");
        runFlag = false;
        update.interrupt();
        update = null;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class Update extends Thread {
        public Update() {
        }

        @Override
        public void run() {
            SyncService syncService = SyncService.this;
            while (syncService.runFlag) {
                try {
                    Log.v(TAG, "SyncService running");
                    padronBusiness.syncData(new BudaCallback() {
                        @Override
                        public void callback() {
                            try {
                                ReportFragment.showReport();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error sync");
                }
            }
        }
    }
}

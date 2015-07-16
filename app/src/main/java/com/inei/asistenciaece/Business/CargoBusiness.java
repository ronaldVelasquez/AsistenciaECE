package com.inei.asistenciaece.Business;

import android.content.Context;

import com.inei.asistenciaece.DAO.CargoDao;
import com.inei.asistenciaece.Entity.CargoEntity;

public class CargoBusiness {

    private static final String TAG = CargoBusiness.class.getSimpleName();
    private Context context;
    private CargoDao cargoDao;

    public CargoBusiness(Context context) {
        this.context = context;
        cargoDao = CargoDao.getInstance(context);
    }

    public String getCargo(int id_cargo){
        CargoEntity cargoEntity = cargoDao.getCargo(id_cargo);
        String cargoName;
        if (cargoEntity.getCargo_res().isEmpty()){
            cargoName = "Sin Cargo";
        } else {
            cargoName = cargoEntity.getCargo_res();
        }
        return cargoName;
    }
}

package com.inei.asistenciaece.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.inei.asistenciaece.Entity.CargoEntity;
import com.inei.asistenciaece.Entity.SendAsistenciaEntity;
import com.inei.asistenciaece.Entity.TipoCapacitacionEntity;
import com.inei.asistenciaece.Entity.DataEntity;
import com.inei.asistenciaece.Entity.HorarioEntity;
import com.inei.asistenciaece.Entity.LocalEntity;
import com.inei.asistenciaece.Entity.MarcacionEntity;
import com.inei.asistenciaece.Entity.PadronEntity;
import com.inei.asistenciaece.Entity.PostulanteEntity;
import com.inei.asistenciaece.Entity.RolEntity;
import com.inei.asistenciaece.Entity.SedeOperativaEntity;
import com.inei.asistenciaece.Entity.VersionEntity;
import com.inei.asistenciaece.Entity.VersionTurnoEntity;

import java.util.ArrayList;

public class PadronDao extends BaseDAO {

    private static final String TAG = PadronDao.class.getSimpleName();
    private static PadronDao padronDao;

    public synchronized static PadronDao getInstance(Context context) {
        if (padronDao == null) {
            padronDao = new PadronDao(context);
        }
        return padronDao;
    }

    public PadronDao(Context context) {
        initDBHelper(context);
    }

    public boolean addPadron(PadronEntity padronEntity) {

        Log.v(TAG, "Start addPadron");
        boolean flag;
        try {
            openDBHelper();
            deletePadron();

//            Insert postulantes
            if (!padronEntity.getPostulantes().isEmpty()) {
                Log.v(TAG, "Insert postulantes");
                for (PostulanteEntity postulanteEntity : padronEntity.getPostulantes()) {
                    contentValues = new ContentValues();
                    contentValues.put("id", postulanteEntity.getId());
                    contentValues.put("nro_version", postulanteEntity.getNro_version());
                    contentValues.put("tipo_capacitacion_id", postulanteEntity.getTipo_capacitacion_id());
                    contentValues.put("local_id", postulanteEntity.getLocal_id());
                    contentValues.put("cargo_id", postulanteEntity.getCargo_id());
                    contentValues.put("sede_id", postulanteEntity.getSede_id());
                    contentValues.put("dni", postulanteEntity.getDni());
                    contentValues.put("apellidos_nombres", postulanteEntity.getApellidos_nombres());
                    contentValues.put("numero_de_aula", postulanteEntity.getNumero_aula());
                    contentValues.put("numero_de_bungalow", postulanteEntity.getNumero_bungalow());

                    dbHelper.getDatabase().insertWithOnConflict("postulante", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Postulantes null");
            }

//            Insert local
            if (!padronEntity.getLocales().isEmpty()) {
                Log.v(TAG, "Insert local");
                for (LocalEntity localEntity : padronEntity.getLocales()) {
                    contentValues = new ContentValues();
                    contentValues.put("id_local", localEntity.getId_local());
                    contentValues.put("nombre_local", localEntity.getNombre_local());
                    contentValues.put("direccion", localEntity.getDireccion());
                    dbHelper.getDatabase().insertWithOnConflict("local", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Local null");
            }

//            Insert cargo
            if (!padronEntity.getCargos().isEmpty()) {
                Log.v(TAG, "Insert cargo");
                for (CargoEntity cargoEntity : padronEntity.getCargos()) {
                    contentValues = new ContentValues();
                    contentValues.put("id_cargo", cargoEntity.getId_cargo());
                    contentValues.put("cargo", cargoEntity.getCargo());
                    contentValues.put("cargo_res", cargoEntity.getCargo_res());
                    contentValues.put("sigla", cargoEntity.getSigla());
                    contentValues.put("nivel", cargoEntity.getNivel());
                    dbHelper.getDatabase().insertWithOnConflict("cargo", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Cargo null");
            }

//            Insert Rol
            if (!padronEntity.getRoles().isEmpty()) {
                Log.v(TAG, "Insert rol");
                for (RolEntity rolEntity : padronEntity.getRoles()) {
                    contentValues = new ContentValues();
                    contentValues.put("idRol", rolEntity.getIdRol());
                    contentValues.put("rol", rolEntity.getRol());
                    contentValues.put("descripcion", rolEntity.getDescripcion());
                    dbHelper.getDatabase().insertWithOnConflict("rol", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Rol null");
            }

            // Insert version_turno
            if(!padronEntity.getVersiones_turno().isEmpty()){
                Log.v(TAG, "Insert version_turno");
                for (VersionTurnoEntity versionTurnoEntity : padronEntity.getVersiones_turno()){
                    contentValues = new ContentValues();
                    contentValues.put("id", versionTurnoEntity.getId());
                    contentValues.put("numero_de_version", versionTurnoEntity.getNumero_de_version());
                    contentValues.put("nombre", versionTurnoEntity.getNombre());
                    dbHelper.getDatabase().insertWithOnConflict("version_turno", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Version_turno null");
            }

            // Insert horario
            if(!padronEntity.getHorarios().isEmpty()){
                Log.v(TAG, "Insert horarios");
                for (HorarioEntity horarioEntity : padronEntity.getHorarios()){
                    contentValues = new ContentValues();
                    contentValues.put("id", horarioEntity.getId());
                    contentValues.put("version_turno_id", horarioEntity.getVersion_turno_id());
                    contentValues.put("tipo_capacitacion_id", horarioEntity.getTipo_capacitacion_id());
                    contentValues.put("fecha", horarioEntity.getFecha());
                    contentValues.put("hora_inicio", horarioEntity.getHora_inicio());
                    contentValues.put("hora_fin", horarioEntity.getHora_fin());
                    contentValues.put("marcacion_id", horarioEntity.getMarcacion_id());
                    dbHelper.getDatabase().insertWithOnConflict("horario", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "horario null");
            }

            // Insert Tipo capacitacion
            if(!padronEntity.getTipos_capacitacion().isEmpty()){
                Log.v(TAG, "Insert tipo capacitacion");
                for (TipoCapacitacionEntity tipoCapacitacionEntity : padronEntity.getTipos_capacitacion()){
                    contentValues = new ContentValues();
                    contentValues.put("id", tipoCapacitacionEntity.getId());
                    contentValues.put("nombre", tipoCapacitacionEntity.getNombre());
                    contentValues.put("descripcion", tipoCapacitacionEntity.getDescripcion());
                    dbHelper.getDatabase().insertWithOnConflict("tipo_capacitacion", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "TIPO CAPACITACION NULL");
            }

            // Insert marcaciones
            if(!padronEntity.getMarcaciones().isEmpty()){
                Log.v(TAG, "Insert marcaciones");
                for (MarcacionEntity marcacionEntity : padronEntity.getMarcaciones()){
                    contentValues = new ContentValues();
                    contentValues.put("id", marcacionEntity.getId());
                    contentValues.put("nombre", marcacionEntity.getNombre());
                    dbHelper.getDatabase().insertWithOnConflict("marcacion", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Marcaciones null");
            }

            // Insert sedes_operativas
            if(!padronEntity.getSedes_operativas().isEmpty()){
                Log.v(TAG, "Insert sedes operativas");
                for (SedeOperativaEntity sedeOperativaEntity : padronEntity.getSedes_operativas()){
                    contentValues = new ContentValues();
                    contentValues.put("cod_sede_operativa", sedeOperativaEntity.getCod_sede_operativa());
                    contentValues.put("sede_operativa", sedeOperativaEntity.getSede_operativa());
                    dbHelper.getDatabase().insertWithOnConflict("sede_operativa", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
            } else {
                Log.v(TAG, "Marcaciones null");
            }

//            Insert Version
            Log.v(TAG, "Insert version");
            VersionEntity versionEntity = padronEntity.getVersion();
            contentValues = new ContentValues();
            contentValues.put("numero_de_version", versionEntity.getNumero_de_version());
            contentValues.put("usuarioCrea", versionEntity.getUsuarioCrea());
            contentValues.put("fechaCrea", versionEntity.getFechaCrea());
            dbHelper.getDatabase().insertWithOnConflict("version", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            dbHelper.setTransactionSuccessful();
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(TAG, "Error when save data padron");
            flag = false;
        } finally {
            closeDBHelper();
            Log.v(TAG, "End addPadron");
        }
        return flag;
    }

    public void deletePadron() {
        try {
            Log.v(TAG, "Star delete Padron");
            dbHelper.getDatabase().delete("version", null, null);
            dbHelper.getDatabase().delete("postulante", null, null);
            dbHelper.getDatabase().delete("rol", null, null);
            dbHelper.getDatabase().delete("cargo", null, null);
            dbHelper.getDatabase().delete("local", null, null);
            dbHelper.getDatabase().delete("version_turno", null, null);
            dbHelper.getDatabase().delete("horario", null, null);
            dbHelper.getDatabase().delete("tipo_capacitacion", null, null);
            dbHelper.getDatabase().delete("marcacion", null, null);
            dbHelper.getDatabase().delete("sede_operativa", null, null);
            dbHelper.getDatabase().delete("postulante_asistencia", null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(TAG, "Error when deleted padron");
            closeDBHelper();
        }
    }

    public ArrayList<SendAsistenciaEntity> getPadronSync() {
        ArrayList<SendAsistenciaEntity> arrayAsistencia = new ArrayList<>();
        Log.v(TAG, "Start get PadronSync");
        try {
            openDBHelper();
            SQL = "select * from postulante_asistencia where asistencia = " + 1;
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    SendAsistenciaEntity asistenciaEntity = new SendAsistenciaEntity();
                    asistenciaEntity.setPostulante_id(cursor.getInt(cursor.getColumnIndex("postulante_id")));
                    asistenciaEntity.setMarcacion_id(cursor.getInt(cursor.getColumnIndex("marcacion_id")));
                    asistenciaEntity.setAsistencia(cursor.getInt(cursor.getColumnIndex("asistencia")));
                    asistenciaEntity.setFecha(cursor.getString(cursor.getColumnIndex("fecha")));
                    asistenciaEntity.setVersion_turno_id(cursor.getInt(cursor.getColumnIndex("version_turno_id")));
                    arrayAsistencia.add(asistenciaEntity);
                    cursor.moveToNext();
                }
            } else {
                Log.v(TAG, "Not found asistencia");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(TAG, "Error when search asistencia");
        } finally {
            Log.v(TAG, "End getPadronSync");
            cursor.close();
            closeDBHelper();
        }
        return arrayAsistencia;
    }

    public void setDataSync(DataEntity dataEntity) {
        Log.v(TAG, "Start setDatasync");
        try{
            openDBHelper();
            if (!dataEntity.getAsistencias().isEmpty()){
                for(SendAsistenciaEntity asistenciaEntity : dataEntity.getAsistencias()){
                    contentValues = new ContentValues();
                    contentValues.put("asistencia", asistenciaEntity.getAsistencia());
                    String where = "postulante_id = " + asistenciaEntity.getPostulante_id() + " and version_turno_id = " + asistenciaEntity.getVersion_turno_id() + " and marcacion_id = " + asistenciaEntity.getMarcacion_id() + " and fecha = '" + asistenciaEntity.getFecha() + "'";
                    dbHelper.getDatabase().updateWithOnConflict("postulante_asistencia", contentValues, where, null, SQLiteDatabase.CONFLICT_IGNORE);
                }
                dbHelper.setTransactionSuccessful();
            } else {
                Log.v(TAG, "Not found data");
            }

        } catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Error when set data");
        } finally {
            Log.v(TAG, "End setDataSync");
            cursor.close();
            closeDBHelper();
        }
    }
}

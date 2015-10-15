package com.inei.asistenciaece.DAO;

import android.content.Context;
import android.util.Log;

import com.inei.asistenciaece.Entity.HorarioEntity;
import com.inei.asistenciaece.Entity.PostulanteEntity;
import com.inei.asistenciaece.Utils.ReportItem;

import java.util.ArrayList;

public class PostulanteDao extends BaseDAO{

    private static final String TAG = PostulanteDao.class.getSimpleName();
    private static PostulanteDao postulanteDao;

    public synchronized static PostulanteDao getInstance(Context context){
        if(postulanteDao == null){
            postulanteDao = new PostulanteDao(context);
        }
        return postulanteDao;
    }

    public PostulanteDao(Context context) {
        initDBHelper(context);
    }

    public ArrayList<ReportItem> getReportLocal(HorarioEntity horarioEntity) {
        Log.v(TAG, "Start getReport");
        ArrayList<ReportItem> reportItems = new ArrayList<>();
        try {
            openDBHelper();
            SQL = "select distinct " +
                    "p.numero_de_aula as 'nro_aula', " +
                    "(select count(1) from postulante where numero_de_aula = p.numero_de_aula group by numero_de_aula) as 'programados', " +
                    "(select COUNT(1) from postulante p1 inner join postulante_asistencia pa1 on p1.id = pa1.postulante_id where p1.numero_de_aula = p.numero_de_aula and asistencia in(1,2) and marcacion_id = " + 1 + " and version_turno_id = " + horarioEntity.getVersion_turno_id() + " and (date(pa1.fecha) = '" + horarioEntity.getFecha() + "')) as 'asistencia', " +
                    "(select count(1) from postulante where numero_de_aula = p.numero_de_aula group by numero_de_aula) - " +
                    "(select COUNT(1) from postulante p1 inner join postulante_asistencia pa1 on p1.id = pa1.postulante_id where p1.numero_de_aula = p.numero_de_aula and asistencia in(1,2) and marcacion_id = " + 1 + " and version_turno_id = " + horarioEntity.getVersion_turno_id() + " and (date(pa1.fecha) = '" + horarioEntity.getFecha() + "')) as 'no_asistieron', " +
                    "(select COUNT(1) from postulante p1 inner join postulante_asistencia pa1 on p1.id = pa1.postulante_id where p1.numero_de_aula = p.numero_de_aula and asistencia = 2 and marcacion_id = " + 1 + " and version_turno_id = " + horarioEntity.getVersion_turno_id() + " and (date(pa1.fecha) = '" + horarioEntity.getFecha() + "')) as 'sincronizados' " +
                    "from postulante p " +
                    "left join postulante_asistencia pa " +
                    "on p.id = pa.postulante_id order by p.numero_de_aula";
            /*SQL = "select distinct " +
                    "pos.nro_aula, " +
                    "(select count(*) from postulante where nro_aula=pos.nro_aula group by nro_aula) AS totales, " +
                    "ifnull((select count(nro_aula) from postulante where m1_estado IN(1,2) AND nro_aula=pos.nro_aula group by nro_aula),0) AS registrados, " +
                    "ifnull((select count(nro_aula)  from postulante where m1_estado=0 AND nro_aula=pos.nro_aula group by nro_aula),0) AS no_registrados, " +
                    "ifnull((select count(nro_aula) from postulante where m1_estado=2 AND nro_aula=pos.nro_aula group by nro_aula),0) AS sincronizados " +
                    "from " +
                    "postulante as pos " +
                    "left join local as loc on pos.id_local=loc.id_local " +
                    "group by pos.m1_estado,pos.nro_aula order by nro_aula";*/
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    ReportItem reportItem = new ReportItem();
                    reportItem.setNroClasses(cursor.getString(cursor.getColumnIndex("nro_aula")));
                    reportItem.setNroAsign(cursor.getInt(cursor.getColumnIndex("programados")));
                    reportItem.setNroRegister(cursor.getInt(cursor.getColumnIndex("asistencia")));
                    reportItem.setNroNoRegister(cursor.getInt(cursor.getColumnIndex("no_asistieron")));
                    reportItem.setNroSync(cursor.getInt(cursor.getColumnIndex("sincronizados")));
                    reportItems.add(reportItem);
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Error when get Report");
        }finally {
            Log.v(TAG, "End getReport");
            cursor.close();
            closeDBHelper();
        }
        return reportItems;
    }

    public ArrayList<ReportItem> getReportClasses(HorarioEntity horarioEntity) {
        Log.v(TAG, "Start getReport");
        ArrayList<ReportItem> reportItems = new ArrayList<>();
        try {
            openDBHelper();
            SQL = "select distinct " +
                    "p.numero_de_aula as 'nro_aula', " +
                    "(select count(1) from postulante where numero_de_aula = p.numero_de_aula group by numero_de_aula) as 'programados', " +
                    "(select COUNT(1) from postulante p1 inner join postulante_asistencia pa1 on p1.id = pa1.postulante_id where p1.numero_de_aula = p.numero_de_aula and asistencia in(1,2) and marcacion_id = " + 2+ " and version_turno_id = " + horarioEntity.getVersion_turno_id() + " and (date(pa1.fecha) = '" + horarioEntity.getFecha() + "')) as 'asistencia', " +
                    "(select count(1) from postulante where numero_de_aula = p.numero_de_aula group by numero_de_aula) - " +
                    "(select COUNT(1) from postulante p1 inner join postulante_asistencia pa1 on p1.id = pa1.postulante_id where p1.numero_de_aula = p.numero_de_aula and asistencia in(1,2) and marcacion_id = " + 2 + " and version_turno_id = " + horarioEntity.getVersion_turno_id() + " and (date(pa1.fecha) = '" + horarioEntity.getFecha() + "')) as 'no_asistieron', " +
                    "(select COUNT(1) from postulante p1 inner join postulante_asistencia pa1 on p1.id = pa1.postulante_id where p1.numero_de_aula = p.numero_de_aula and asistencia = 2 and marcacion_id = " + 2 + " and version_turno_id = " + horarioEntity.getVersion_turno_id() + " and (date(pa1.fecha) = '" + horarioEntity.getFecha() + "')) as 'sincronizados' " +
                    "from postulante p " +
                    "left join postulante_asistencia pa " +
                    "on p.id = pa.postulante_id order by p.numero_de_aula";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    ReportItem reportItem = new ReportItem();
                    reportItem.setNroClasses(cursor.getString(cursor.getColumnIndex("nro_aula")));
                    reportItem.setNroAsign(cursor.getInt(cursor.getColumnIndex("programados")));
                    reportItem.setNroRegister(cursor.getInt(cursor.getColumnIndex("asistencia")));
                    reportItem.setNroNoRegister(cursor.getInt(cursor.getColumnIndex("no_asistieron")));
                    reportItem.setNroSync(cursor.getInt(cursor.getColumnIndex("sincronizados")));
                    reportItems.add(reportItem);
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Error when get Report");
        }finally {
            Log.v(TAG, "End getReport");
            cursor.close();
            closeDBHelper();
        }
        return reportItems;
    }

    public ArrayList<String> getClasses() {
        Log.v(TAG, "Start getClasses");
        ArrayList<String> classes = new ArrayList<>();
        try {
            openDBHelper();
            SQL = "select distinct numero_de_aula from postulante order by numero_de_aula";
            cursor = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    classes.add(cursor.getString(cursor.getColumnIndex("numero_de_aula")));
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Error when get classes");
        } finally {
            Log.v(TAG, "End getClasses");
            cursor.close();
            closeDBHelper();
        }
        return classes;
    }

    public PostulanteEntity checkPostulante(String dni) {

        PostulanteEntity postulanteEntity = new PostulanteEntity();
        Log.v(TAG, "Start check Postulante");
        try{
            openDBHelper();
            SQL = "select * from postulante where dni = '" + dni + "'";
            Log.v(TAG, SQL);
            cursor  = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                postulanteEntity.setDni(cursor.getString(cursor.getColumnIndex("dni")));
                postulanteEntity.setNro_version(cursor.getInt(cursor.getColumnIndex("nro_version")));
                postulanteEntity.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                postulanteEntity.setCargo_id(cursor.getInt(cursor.getColumnIndex("cargo_id")));
                postulanteEntity.setSede_id(cursor.getString(cursor.getColumnIndex("sede_id")));
                postulanteEntity.setApellidos_nombres(cursor.getString(cursor.getColumnIndex("apellidos_nombres")));
                postulanteEntity.setId(cursor.getInt(cursor.getColumnIndex("id")));
                postulanteEntity.setNumero_aula(cursor.getInt(cursor.getColumnIndex("numero_de_aula")));
                postulanteEntity.setNumero_bungalow(cursor.getString(cursor.getColumnIndex("numero_de_bungalow")));
            } else  {
                postulanteEntity = null;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            Log.v(TAG, "Error database");
            postulanteEntity = null;
        } finally {
            Log.v(TAG, "End check Postulante");
            cursor.close();
            closeDBHelper();
        }
        return postulanteEntity;

    }

    public PostulanteEntity checkPostulante(String dni, int aula) {

        PostulanteEntity postulanteEntity = new PostulanteEntity();
        Log.v(TAG, "Start check Postulante");
        try{
            openDBHelper();
            SQL = "select * from postulante where dni = '" + dni + "' and numero_de_aula =" + aula;
            Log.v(TAG, SQL);
            cursor  = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst()){
                postulanteEntity.setDni(cursor.getString(cursor.getColumnIndex("dni")));
                postulanteEntity.setNro_version(cursor.getInt(cursor.getColumnIndex("nro_version")));
                postulanteEntity.setLocal_id(cursor.getInt(cursor.getColumnIndex("local_id")));
                postulanteEntity.setCargo_id(cursor.getInt(cursor.getColumnIndex("cargo_id")));
                postulanteEntity.setSede_id(cursor.getString(cursor.getColumnIndex("sede_id")));
                postulanteEntity.setApellidos_nombres(cursor.getString(cursor.getColumnIndex("apellidos_nombres")));
                postulanteEntity.setId(cursor.getInt(cursor.getColumnIndex("id")));
                postulanteEntity.setNumero_aula(cursor.getInt(cursor.getColumnIndex("numero_de_aula")));
                postulanteEntity.setNumero_bungalow(cursor.getString(cursor.getColumnIndex("numero_de_bungalow")));
            } else  {
                postulanteEntity = null;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            Log.v(TAG, "Error database");
            postulanteEntity = null;
        } finally {
            Log.v(TAG, "End check Postulante");
            cursor.close();
            closeDBHelper();
        }
        return postulanteEntity;

    }

    public int getAllPostutlantes(){
        int total = 0;
        Log.v(TAG, "Start getAllPostutlantes");
        try{
            openDBHelper();
            SQL = "select count(*) as total from postulante ";
            Log.v(TAG, SQL);
            cursor  = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst())
                total = cursor.getInt(cursor.getColumnIndex("total"));
        } catch (Exception ex){
            ex.printStackTrace();
            Log.v(TAG, "Error database");
        } finally {
            Log.v(TAG, "End getAllPostutlantes");
            cursor.close();
            closeDBHelper();
        }
        return total;
    }

    public int getTotalRegistrado(HorarioEntity horarioEntity){
        int total = 0;
        Log.v(TAG, "Start getTotalRegistrado");
        try{
            openDBHelper();
            SQL = "select count(*) as total from postulante_asistencia where asistencia in(1,2) and marcacion_id = 1 and version_turno_id = " + horarioEntity.getVersion_turno_id() + " and (date(fecha) = '" + horarioEntity.getFecha() + "')";
            Log.v(TAG, SQL);
            cursor  = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst())
                total = cursor.getInt(cursor.getColumnIndex("total"));
        } catch (Exception ex){
            ex.printStackTrace();
            Log.v(TAG, "Error database");
        } finally {
            Log.v(TAG, "End getTotalRegistrado");
            cursor.close();
            closeDBHelper();
        }
        return total;
    }

    public int getTotalSincronizado(HorarioEntity horarioEntity) {
        int total = 0;
        Log.v(TAG, "Start getTotalSincronizado");
        try{
            openDBHelper();
            SQL = "select count(*) as total from postulante_asistencia where asistencia = 2 and marcacion_id = 1 and version_turno_id = " + horarioEntity.getVersion_turno_id() + " and (date(fecha) = '" + horarioEntity.getFecha() + "')";
            Log.v(TAG, SQL);
            cursor  = dbHelper.getDatabase().rawQuery(SQL, null);
            if (cursor.moveToFirst())
                total = cursor.getInt(cursor.getColumnIndex("total"));
        } catch (Exception ex){
            ex.printStackTrace();
            Log.v(TAG, "Error database");
        } finally {
            Log.v(TAG, "End getTotalSincronizado");
            cursor.close();
            closeDBHelper();
        }
        return total;
    }
}

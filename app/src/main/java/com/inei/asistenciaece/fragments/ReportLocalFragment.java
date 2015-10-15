package com.inei.asistenciaece.fragments;

import android.app.Activity;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inei.asistenciaece.Business.HorarioBusiness;
import com.inei.asistenciaece.Business.ReportBusiness;
import com.inei.asistenciaece.DAO.PostulanteDao;
import com.inei.asistenciaece.R;
import com.inei.asistenciaece.ReportAdapter;
import com.inei.asistenciaece.Utils.ReportItem;

import java.util.ArrayList;

public class ReportLocalFragment extends Fragment {
    public static RecyclerView recyclerView;
    public static TextView txtTotalProgramado;
    public static TextView txtTotalRegistrado;
    public static TextView txtTotalNoRegistrado;
    public static TextView txtTotalSincronizado;
    private static Activity activity;

    public static ReportLocalFragment newInstance(){
        return new ReportLocalFragment();
    }

    public ReportLocalFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_report_item);
        txtTotalProgramado = (TextView) view.findViewById(R.id.total_programados);
        txtTotalRegistrado = (TextView) view.findViewById(R.id.total_registrados);
        txtTotalNoRegistrado = (TextView) view.findViewById(R.id.total_no_registrados);
        txtTotalSincronizado = (TextView) view.findViewById(R.id.total_sincronizados);
        activity = getActivity();
        setHasOptionsMenu(true);
        showReport();
        return view;
    }

    public static void showReport(){
        /**
         * Marcacion id = 1 for presence in local
         * horarioBusiness.getHorario(1)
         * */
        ReportBusiness reportBusiness = new ReportBusiness(activity.getApplicationContext());
        HorarioBusiness horarioBusiness = new HorarioBusiness(activity.getApplicationContext());
        PostulanteDao postulanteDao = PostulanteDao.getInstance(activity);
        ArrayList<ReportItem> reportItems = reportBusiness.getReportLocal(horarioBusiness.getHorario(1));
        if (!reportItems.isEmpty()){
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            ReportAdapter reportAdapter = new ReportAdapter(reportItems, activity);
            recyclerView.setAdapter(reportAdapter);


            int totalProgramado = postulanteDao.getAllPostutlantes();
            int totalRegistrado = postulanteDao.getTotalRegistrado(horarioBusiness.getHorario(1));
            int totalNoRegistrado = totalProgramado - totalRegistrado;
            int totalSincronizado = postulanteDao.getTotalSincronizado(horarioBusiness.getHorario(1));
            txtTotalProgramado.setText("" + totalProgramado);
            txtTotalRegistrado.setText("" + totalRegistrado);
            txtTotalNoRegistrado.setText("" + totalNoRegistrado);
            txtTotalSincronizado.setText("" + totalSincronizado);

        }

    }
}

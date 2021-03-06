package com.inei.asistenciaece.fragments;

import android.app.Activity;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.inei.asistenciaece.Business.HorarioBusiness;
import com.inei.asistenciaece.Business.PadronBusiness;
import com.inei.asistenciaece.Business.ReportBusiness;
import com.inei.asistenciaece.R;
import com.inei.asistenciaece.ReportAdapter;
import com.inei.asistenciaece.Utils.ReportItem;
import com.inei.asistenciaece.listeners.BudaCallback;

import java.util.ArrayList;

public class ReportLocalFragment extends Fragment {
    public static final String ARG_SECTION_TITLE = "section_number";
    public static RecyclerView recyclerView;
    private static Activity activity;

    public static ReportLocalFragment newInstance(String sectionTitle){
        ReportLocalFragment fragment = new ReportLocalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sync:
                PadronBusiness padronBusiness = new PadronBusiness(getActivity());
                padronBusiness.syncDataManual(new BudaCallback() {
                    @Override
                    public void callback() {
                        ReportLocalFragment.showReport();
                        /*ReportClassesFragment.showReport();*/
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public ReportLocalFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_report_item);
        activity = getActivity();
        setHasOptionsMenu(true);
        showReport();
        return view;
    }

    public static void showReport(){
        ReportBusiness reportBusiness = new ReportBusiness(activity.getApplicationContext());
        HorarioBusiness horarioBusiness = new HorarioBusiness(activity.getApplicationContext());
        ArrayList<ReportItem> reportItems = reportBusiness.getReportLocal(horarioBusiness.getHorario());
        if (!reportItems.isEmpty()){
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            ReportAdapter reportAdapter = new ReportAdapter(reportItems, activity);
            recyclerView.setAdapter(reportAdapter);
        }
    }
}

package com.inei.asistenciaece.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inei.asistenciaece.Business.ReportBusiness;
import com.inei.asistenciaece.R;
import com.inei.asistenciaece.ReportAdapter;
import com.inei.asistenciaece.Utils.ReportItem;

import java.util.ArrayList;


public class ReportClassesFragment extends Fragment {


    public static RecyclerView recyclerView;
    private static Activity activity;
    public static ReportClassesFragment newInstance() {
        ReportClassesFragment fragment = new ReportClassesFragment();
        return fragment;
    }

    public ReportClassesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report_classes, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_report_classes_item);
        activity = getActivity();
        showReport();
        setHasOptionsMenu(true);
        return view;
    }

    public static void showReport(){
        ReportBusiness reportBusiness = new ReportBusiness(activity.getApplicationContext());
        ArrayList<ReportItem> reportItems = reportBusiness.getReportClasses();
        if (!reportItems.isEmpty()){
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            ReportAdapter reportAdapter = new ReportAdapter(reportItems, activity);
            recyclerView.setAdapter(reportAdapter);
        }
    }
}

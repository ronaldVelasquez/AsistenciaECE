package com.inei.asistenciaece.fragments;


import android.content.Context;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.inei.asistenciaece.Business.ReportBusiness;
import com.inei.asistenciaece.R;
import com.inei.asistenciaece.Utils.ReportItem;

import java.util.ArrayList;

public class ReportFragment extends Fragment {
    public static final String ARG_SECTION_TITLE = "section_number";
    public static ItemAdapter itemAdapter;
    public ListView listViewReportItem;
    public static ReportFragment newInstance(String sectionTitle){
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public ReportFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        listViewReportItem  = (ListView) view.findViewById(R.id.list_report_item);
        /*String title = getArguments().getString(ARG_SECTION_TITLE);
        TextView titulo = (TextView) view.findViewById(R.id.title);
        titulo.setText(title);*/
        showReport();
        return view;
    }
    public void showReport(){
        ReportBusiness reportBusiness = new ReportBusiness(getActivity().getApplicationContext());
        ArrayList<ReportItem> reportItems = reportBusiness.getReport();
        if (!reportItems.isEmpty()){
            itemAdapter = new ItemAdapter(getActivity().getApplicationContext(), reportItems);
            listViewReportItem.setAdapter(itemAdapter);
        }
    }

    public class ItemAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<ReportItem> items;

        public ItemAdapter(Context context, ArrayList<ReportItem> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        @Override
        public Object getItem(int i) {
            return this.items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view = convertView;
            if (convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item, viewGroup, false);
            }

            TextView txtNroClasses = (TextView) view.findViewById(R.id.txt_nro_classes);
            TextView txtNroAsign = (TextView) view.findViewById(R.id.txt_nro_asign);
            TextView txtNroRegister = (TextView) view.findViewById(R.id.txt_nro_register);
            TextView txtNoRegister = (TextView) view.findViewById(R.id.txt_no_register);
            TextView txtNroSync = (TextView) view.findViewById(R.id.txt_nro_sync);

            ReportItem item = this.items.get(i);
            txtNroClasses.setText(item.getNroClasses());
            txtNroAsign.setText(String.valueOf(item.getNroAsign()));
            txtNroRegister.setText(String.valueOf(item.getNroRegister()));
            txtNoRegister.setText(String.valueOf(item.getNroNoRegister()));
            txtNroSync.setText(String.valueOf(item.getNroSync()));

            return view;
        }
    }
}

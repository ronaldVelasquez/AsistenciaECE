package com.inei.asistenciaece;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inei.asistenciaece.Utils.ReportItem;
import com.inei.asistenciaece.holder.ReportHolder;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportHolder> {

    private List<ReportItem> reports;
    private Context context;

    public ReportAdapter(List<ReportItem> reports, Context context) {
        this.reports = reports;
        this.context = context;
    }

    @Override
    public ReportHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return new ReportHolder(v);
    }

    @Override
    public void onBindViewHolder(ReportHolder reportHolder, int i) {
        reportHolder.getTxtNroClasses().setText(reports.get(i).getNroClasses());
        reportHolder.getTxtNroAsign().setText(String.valueOf(reports.get(i).getNroAsign()));
        reportHolder.getTxtNroRegister().setText(String.valueOf(reports.get(i).getNroRegister()));
        reportHolder.getTxtNoRegister().setText(String.valueOf(reports.get(i).getNroNoRegister()));
        reportHolder.getTxtNroSync().setText(String.valueOf(reports.get(i).getNroSync()));
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }
}

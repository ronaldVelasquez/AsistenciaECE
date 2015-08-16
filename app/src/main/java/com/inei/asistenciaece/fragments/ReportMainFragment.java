package com.inei.asistenciaece.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.inei.asistenciaece.Business.PadronBusiness;
import com.inei.asistenciaece.R;
import com.inei.asistenciaece.SectionAdapter;
import com.inei.asistenciaece.listeners.BudaCallback;

public class ReportMainFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "title";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static ReportMainFragment newInstance(String title){
        ReportMainFragment fragment = new ReportMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public ReportMainFragment() {
        // Required empty public constructor
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
                        ReportClassesFragment.showReport();
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_report_main, container, false);
    }

    /*@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        setTabs();
        setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setTabs() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("Asistencia al Local"));
        tabLayout.addTab(tabLayout.newTab().setText("Asistencia en Aula"));
    }

    private void setViewPager(ViewPager viewPager){
        SectionAdapter adapter = new SectionAdapter(getChildFragmentManager());
        adapter.addFragment(ReportLocalFragment.newInstance(), "Asistencia al Local");
        adapter.addFragment(ReportClassesFragment.newInstance(), "Asistencia en Aula");
        viewPager.setAdapter(adapter);
    }*/
}

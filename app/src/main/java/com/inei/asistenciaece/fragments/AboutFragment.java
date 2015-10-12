package com.inei.asistenciaece.fragments;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inei.asistenciaece.DAO.VersionDao;
import com.inei.asistenciaece.R;

public class AboutFragment extends Fragment {

    private TextView versionPadron, versionAplicativo;

    public static final String ARG_SECTION_TITLE = "section number";

    public AboutFragment() {
    }

    public static AboutFragment newInstance(String title) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        versionAplicativo = (TextView) view.findViewById(R.id.version_aplicativo);
        versionPadron = (TextView) view.findViewById(R.id.version_padron);

        try {
            String version = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            versionAplicativo.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        VersionDao versionDao = VersionDao.getInstance(getActivity());
        versionPadron.setText(String.valueOf(versionDao.getVersion().getNumero_de_version()));

    }
}

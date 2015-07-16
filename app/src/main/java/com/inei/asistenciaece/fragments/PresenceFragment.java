package com.inei.asistenciaece.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.inei.asistenciaece.R;

public class PresenceFragment extends Fragment {

    private static final String ARG_SECTION_TITLE = "section title";
    private EditText edtxDni;
    private String dni;

    public static PresenceFragment newInstance(String title) {
        PresenceFragment fragment = new PresenceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public PresenceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_presence, container, false);
        edtxDni = (EditText) view.findViewById(R.id.edtx_dni);
        edtxDni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 8) {
                    dni = charSequence.toString();
                    showPostulante(dni);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 8) {
                    edtxDni.setText("");
                }
            }
        });
        return view;
    }

    private void showPostulante(String dni){


    }

}

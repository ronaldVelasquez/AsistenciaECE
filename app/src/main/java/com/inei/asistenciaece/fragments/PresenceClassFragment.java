package com.inei.asistenciaece.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.inei.asistenciaece.Business.CargoBusiness;
import com.inei.asistenciaece.Business.LocalBusiness;
import com.inei.asistenciaece.Business.PostulanteBusiness;
import com.inei.asistenciaece.Entity.PostulanteEntity;
import com.inei.asistenciaece.R;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;

public class PresenceClassFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section title";
    private EditText edtxDni;
    private TextView txtName;
    private TextView txtCargo;
    private TextView txtLocation;
    private TextView txtDni;
    private TextView txtClassroom;
    private String dni;
    private String classes;
    private Spinner spinner;

    public static PresenceClassFragment newInstance(String title) {
        PresenceClassFragment fragment = new PresenceClassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public PresenceClassFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_presence_class, container, false);
        edtxDni = (EditText) view.findViewById(R.id.edtx_dni);
        txtDni = (TextView) view.findViewById(R.id.txt_dni);
        txtName = (TextView) view.findViewById(R.id.txt_name);
        txtCargo = (TextView) view.findViewById(R.id.txt_cargo);
        txtLocation = (TextView) view.findViewById(R.id.txt_location);
        txtClassroom = (TextView) view.findViewById(R.id.txt_classroom);
        clearDataShow();
        spinner = (Spinner) view.findViewById(R.id.spinner_class);
        setSpinnerData();

        edtxDni.setFocusable(true);
        edtxDni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 8) {
                    classes = spinner.getSelectedItem().toString();
                    dni = charSequence.toString();
                    showPostulante(dni, classes);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 8) {
                    edtxDni.setText("");
                }
            }
        });
        edtxDni.requestFocus();
        return view;
    }

    private void setSpinnerData() {
        PostulanteBusiness postulanteBusiness = new PostulanteBusiness(getActivity().getApplicationContext());
        ArrayList<String> classes = postulanteBusiness.getClasses();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, classes);
        spinner.setAdapter(dataAdapter);
    }

    private void showPostulante(String dni, String aula) {
        PostulanteBusiness postulanteBusiness = new PostulanteBusiness(getActivity().getApplicationContext());
        PostulanteEntity postulanteEntity = postulanteBusiness.checkPresence(dni, aula);
        String message = "";
        View view = getActivity().findViewById(R.id.layout_postulante);
        TextView txtMesssage = (TextView) getActivity().findViewById(R.id.label_message);
        Log.e("asdasdasdasdasd", String.valueOf(postulanteEntity.getM2_estado()));
        switch (postulanteEntity.getM2_estado()) {
            case 0:
                message = "Se registró correctamente";
                view.setBackgroundColor(getResources().getColor(R.color.correct));
                txtMesssage.setVisibility(View.VISIBLE);
                txtMesssage.setTextColor(getResources().getColor(R.color.correct));
                txtMesssage.setText(message);
                fillDataShow(postulanteEntity);
                break;
            case 1:
            case 2:
                message = "El postulante ya fue registrado al aula";
                view.setBackgroundColor(getResources().getColor(R.color.warning));
                txtMesssage.setVisibility(View.VISIBLE);
                txtMesssage.setTextColor(getResources().getColor(R.color.warning));
                txtMesssage.setText(message);
                fillDataShow(postulanteEntity);
                break;
            case 3:
                message = "El postulante no pertenece al aula y/o local";
                view.setBackgroundColor(getResources().getColor(R.color.error_postulante));
                txtMesssage.setVisibility(View.VISIBLE);
                txtMesssage.setTextColor(getResources().getColor(R.color.error_postulante));
                txtMesssage.setText(message);
                clearDataShow();
                break;
            case 4:
                message = "Error";
                view.setBackgroundColor(getResources().getColor(R.color.error_postulante));
                txtMesssage.setVisibility(View.VISIBLE);
                txtMesssage.setTextColor(getResources().getColor(R.color.error_postulante));
                txtMesssage.setText(message);
                clearDataShow();
                break;
        }
    }

    private String getCargo(int id_cargo) {
        CargoBusiness cargoBusiness = new CargoBusiness(getActivity().getApplicationContext());
        return cargoBusiness.getCargo(id_cargo);
    }

    private String getLocal(int id_local) {
        LocalBusiness localBusiness = new LocalBusiness(getActivity().getApplicationContext());
        return localBusiness.getLocal(id_local);
    }

    private void fillDataShow(PostulanteEntity postulanteEntity) {
        txtDni.setText(postulanteEntity.getDni());
        txtName.setText(postulanteEntity.getApe_nom());
        txtCargo.setText(getCargo(postulanteEntity.getId_cargo()));
        txtLocation.setText(getLocal(postulanteEntity.getId_local()));
        txtClassroom.setText(postulanteEntity.getNro_aula());
    }

    private void clearDataShow() {
        txtName.setText("");
        txtDni.setText("");
        txtCargo.setText("");
        txtLocation.setText("");
        txtClassroom.setText("");
    }
}

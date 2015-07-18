package com.inei.asistenciaece.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.inei.asistenciaece.Business.CargoBusiness;
import com.inei.asistenciaece.Business.LocalBusiness;
import com.inei.asistenciaece.Business.PostulanteBusiness;
import com.inei.asistenciaece.Entity.PostulanteEntity;
import com.inei.asistenciaece.R;

public class PresenceFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section title";
    private EditText edtxDni;
    private TextView labelMessage;
    private TextView txtName;
    private TextView txtCargo;
    private TextView txtLocation;
    private TextView txtDni;
    private TextView txtClassroom;
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

        labelMessage = (TextView) view.findViewById(R.id.label_message);
        txtDni = (TextView) view.findViewById(R.id.txt_dni);
        txtName = (TextView) view.findViewById(R.id.txt_name);
        txtCargo = (TextView) view.findViewById(R.id.txt_cargo);
        txtLocation = (TextView) view.findViewById(R.id.txt_location);
        txtClassroom = (TextView) view.findViewById(R.id.txt_classroom);
        clearDataShow();
        return view;
    }

    private void showPostulante(String dni) {
        PostulanteBusiness postulanteBusiness = new PostulanteBusiness(getActivity().getApplicationContext());
        PostulanteEntity postulanteEntity = postulanteBusiness.checkPresence(dni);
        String message = "";
        View view = getActivity().findViewById(R.id.layout_postulante);
        TextView txtMesssage = (TextView) getActivity().findViewById(R.id.label_message);
        switch (postulanteEntity.getM1_estado()){
            case 0:
                message = "Se registró correctamente";
                view.setBackgroundColor(getResources().getColor(R.color.correct));
                txtMesssage.setVisibility(View.VISIBLE);
                txtMesssage.setTextColor(getResources().getColor(R.color.correct));
                txtMesssage.setText(message);
                fillDataShow(postulanteEntity);
                break;
            case 1: case 2:
                message = "El postulante ya fue registrado";
                view.setBackgroundColor(getResources().getColor(R.color.warning));
                txtMesssage.setVisibility(View.VISIBLE);
                txtMesssage.setTextColor(getResources().getColor(R.color.warning));
                txtMesssage.setText(message);
                fillDataShow(postulanteEntity);
                break;
            case 3:
                message = "El postulante no pertenece al local";
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
    private String getCargo(int id_cargo){
        CargoBusiness cargoBusiness = new CargoBusiness(getActivity().getApplicationContext());
        return cargoBusiness.getCargo(id_cargo);
    }

    private String getLocal(int id_local){
        LocalBusiness localBusiness = new LocalBusiness(getActivity().getApplicationContext());
        return localBusiness.getLocal(id_local);
    }

    private void fillDataShow(PostulanteEntity postulanteEntity){
        txtDni.setText(postulanteEntity.getDni());
        txtName.setText(postulanteEntity.getApe_nom());
        txtCargo.setText(getCargo(postulanteEntity.getId_cargo()));
        txtLocation.setText(getLocal(postulanteEntity.getId_local()));
        txtClassroom.setText(postulanteEntity.getNro_aula());
    }

    private void clearDataShow(){
        txtName.setText("");
        txtDni.setText("");
        txtCargo.setText("");
        txtLocation.setText("");
        txtClassroom.setText("");

    }

}

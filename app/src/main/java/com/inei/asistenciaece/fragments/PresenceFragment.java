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

import com.inei.asistenciaece.Business.AsistenciaBusiness;
import com.inei.asistenciaece.Business.CargoBusiness;
import com.inei.asistenciaece.Business.LocalBusiness;
import com.inei.asistenciaece.Business.SedeBusiness;
import com.inei.asistenciaece.Entity.PostulanteEntity;
import com.inei.asistenciaece.Entity.StatusEntity;
import com.inei.asistenciaece.R;

public class PresenceFragment extends Fragment {

    public static final String ARG_SECTION_TITLE = "section title";
    private EditText edtxDni;
    private TextView txtName;
    private TextView txtCargo;
    private TextView txtLocation;
    private TextView txtDni;
    private TextView txtClassroom;
    private TextView txtSede;
    private TextView txtBungalow;
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
        txtDni = (TextView) view.findViewById(R.id.txt_dni);
        txtName = (TextView) view.findViewById(R.id.txt_name);
        txtCargo = (TextView) view.findViewById(R.id.txt_cargo);
        txtLocation = (TextView) view.findViewById(R.id.txt_location);
        txtBungalow = (TextView) view.findViewById(R.id.txt_bungalow);
        txtClassroom = (TextView) view.findViewById(R.id.txt_classroom);
        txtSede = (TextView) view.findViewById(R.id.txt_sede);

        clearDataShow();
        edtxDni.setFocusable(true);
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
        edtxDni.requestFocus();
        return view;
    }

    private void showPostulante(String dni) {
        /**
        idMarcacion:
         1 = local
        2 = aula
         */

        /** Status
         * 0 = error
         * 1 = new asistencia
         * 2 = asistencia exist
         * 3 = postulante no exist
         * 4 = exist but horario no exist
         */
        AsistenciaBusiness asistenciaBusiness = new AsistenciaBusiness(getActivity().getApplicationContext());
        StatusEntity statusEntity = asistenciaBusiness.checkPresence(dni, 1);
        String message = "";
        View view = getActivity().findViewById(R.id.layout_postulante);
        TextView txtMesssage = (TextView) getActivity().findViewById(R.id.label_message);
        switch (statusEntity.getStatus()){
            case 1:
                message = "Se registró correctamente";
                view.setBackgroundColor(getResources().getColor(R.color.correct));
                txtMesssage.setVisibility(View.VISIBLE);
                txtMesssage.setTextColor(getResources().getColor(R.color.correct));
                txtMesssage.setText(message);
                fillDataShow(statusEntity.getPostulanteEntity());
                break;
            case 2:
                message = "El postulante ya fue registrado";
                view.setBackgroundColor(getResources().getColor(R.color.warning));
                txtMesssage.setVisibility(View.VISIBLE);
                txtMesssage.setTextColor(getResources().getColor(R.color.warning));
                txtMesssage.setText(message);
                fillDataShow(statusEntity.getPostulanteEntity());
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
                message = "Fuera del rango de horario de registro";
                view.setBackgroundColor(getResources().getColor(R.color.error_postulante));
                txtMesssage.setVisibility(View.VISIBLE);
                txtMesssage.setTextColor(getResources().getColor(R.color.error_postulante));
                txtMesssage.setText(message);
                fillDataShow(statusEntity.getPostulanteEntity());
                break;
            case 0:
                message = "Error";
                view.setBackgroundColor(getResources().getColor(R.color.error_postulante));
                txtMesssage.setVisibility(View.VISIBLE);
                txtMesssage.setTextColor(getResources().getColor(R.color.error_postulante));
                txtMesssage.setText(message);
                fillDataShow(statusEntity.getPostulanteEntity());
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

    private String getSede(String id_sede){
        SedeBusiness sedeBusiness = new SedeBusiness(getActivity().getApplicationContext());
        return sedeBusiness.getSede(id_sede);
    }

    private void fillDataShow(PostulanteEntity postulanteEntity){
        txtDni.setText(postulanteEntity.getDni());
        txtName.setText(postulanteEntity.getApellidos_nombres());
        txtCargo.setText(getCargo(postulanteEntity.getCargo_id()));
        txtSede.setText(getSede(postulanteEntity.getSede_id()));
        txtBungalow.setText(""+postulanteEntity.getNumero_bungalow());
        txtLocation.setText(getLocal(postulanteEntity.getLocal_id()));
        txtClassroom.setText(""+postulanteEntity.getNumero_aula());
    }

    private void clearDataShow(){
        txtName.setText("");
        txtDni.setText("");
        txtCargo.setText("");
        txtLocation.setText("");
        txtBungalow.setText("");
        txtSede.setText("");
        txtClassroom.setText("");
    }

}

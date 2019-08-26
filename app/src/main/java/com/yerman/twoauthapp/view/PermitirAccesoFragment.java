//Autor: Yerry Aguirre
package com.yerman.twoauthapp.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.yerman.twoauthapp.R;
import com.yerman.twoauthapp.entity.User;
import com.yerman.twoauthapp.view.LoginActivity;


public class PermitirAccesoFragment extends Fragment {
    private Switch simpleSwitch;
    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(User.getUsername() == null || User.getUsername().isEmpty()){
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }

        db = FirebaseFirestore.getInstance();

        simpleSwitch = (Switch) getActivity().findViewById(R.id.swt_confirmar);
        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    changeStatus(true);
                    Toast.makeText(getActivity(), "Autenticacion Encendida", Toast.LENGTH_SHORT).show();
                } else {
                    changeStatus(false);
                    Toast.makeText(getContext(),
                            "Autenticacion Denegada", Toast.LENGTH_LONG).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_permitir_acceso, container, false);
    }

    public boolean changeStatus(boolean band_change){
        boolean band = false;
        try {
            db.collection("UserSession").document(User.getUsername())
                    .update("auth_band", band_change);
            band = true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return band;
    }
}

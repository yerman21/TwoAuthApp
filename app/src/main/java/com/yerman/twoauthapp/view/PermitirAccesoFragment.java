//Autor: Yerry Aguirre
package com.yerman.twoauthapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
    private View viewFragment;
    private Switch simpleSwitch;
    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db;
    private  User user;
    private final String TAG = "(PermitirAccesoFragment) ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        user = (User)getActivity().getIntent().getSerializableExtra("userData");
        if(user == null){
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        viewFragment = inflater.inflate(R.layout.fragment_permitir_acceso, container, false);

        db = FirebaseFirestore.getInstance();

        simpleSwitch = (Switch) viewFragment.findViewById(R.id.swt_confirmar);
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
        return viewFragment;
    }

    @SuppressLint("LongLogTag")
    public boolean changeStatus(boolean band_change){
        Log.d(TAG, "band_change : "+band_change);
        boolean band = false;
        try {
            db.collection("UserSession").document(user.getUsername())
                    .update("auth_band", band_change);
            band = true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return band;
    }
}

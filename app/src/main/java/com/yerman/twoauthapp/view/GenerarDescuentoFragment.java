package com.yerman.twoauthapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yerman.twoauthapp.R;

public class GenerarDescuentoFragment extends Fragment implements View.OnClickListener {
    private EditText producto, descuento;
    private Button btn_generar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        producto = getActivity().findViewById(R.id.etext_producto);
        descuento = getActivity().findViewById(R.id.etext_descuento);
        btn_generar = getActivity().findViewById(R.id.btn_generar);
        btn_generar.setOnClickListener(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generar_descuento, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_generar:

            break;
        }
    }

    public void mostrarResultadosEnNavegador(String imgBase64){
        String body = "<html><body><img src='data:image/jpeg;base64,"+imgBase64+"'></body></html>";
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body).toString());
        startActivity(intent);
    }
}

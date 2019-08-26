package com.yerman.twoauthapp.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;
import com.yerman.twoauthapp.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LeerDescuentoFragment extends Fragment implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity());// inicializando vista de scaner
        getActivity().setContentView(mScannerView);        // configurando la vista del scaner como un content view

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leer_descuento, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera(); // detener la camara
    }

    @Override
    public void handleResult(Result result) {
        Log.v("tag", result.getText()); // print resultado del scaneo
        //Log.v("tag", result.getBarcodeFormat().toString()); // Print el formato del scaner(qrcode, pdf417 etc.)

        mostrarResultados(result.getText());
        getActivity().onBackPressed();

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
    public void mostrarResultados(String barCode){
        Toast.makeText(getActivity(), barCode, Toast.LENGTH_LONG).show();
    }
}

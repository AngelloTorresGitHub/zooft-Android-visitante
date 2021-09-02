package com.example.zooft.cuadroDialogo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.zooft.MainActivity;

@SuppressLint("ValidFragment")
public class CuadroException extends DialogFragment {

    private String mensajeError;

    public CuadroException(String message) {
        this.mensajeError = message;
    }

    public Dialog onCreateDialog (Bundle saveInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setTitle("ERROR")
                .setMessage(this.mensajeError)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                .create();
    }
}

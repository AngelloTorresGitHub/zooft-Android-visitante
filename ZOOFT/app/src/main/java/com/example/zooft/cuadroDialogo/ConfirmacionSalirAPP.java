package com.example.zooft.cuadroDialogo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.app.DialogFragment;

import com.example.zooft.MainActivity;
import com.example.zooft.R;

public class ConfirmacionSalirAPP extends DialogFragment {

    public Dialog onCreateDialog(Bundle saveInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_exitapp)
                .setTitle(R.string.confirmacionSalirAPP)
                .setPositiveButton(R.string.aceptar,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((MainActivity)getActivity()).aceptar();
                            }
                        })
                .setNegativeButton(R.string.cancelar,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                .create();
    }
}
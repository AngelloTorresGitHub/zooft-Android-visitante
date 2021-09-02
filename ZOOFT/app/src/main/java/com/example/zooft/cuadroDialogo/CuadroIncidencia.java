package com.example.zooft.cuadroDialogo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.zooft.Incidencia;
import com.example.zooft.Models.SubTipo;
import com.example.zooft.R;

@SuppressLint("ValidFragment")
public class CuadroIncidencia extends DialogFragment {

    private SubTipo subTipoSelect;

    public CuadroIncidencia(SubTipo subTipoSelect) {
        this.subTipoSelect = subTipoSelect;
    }

    public Dialog onCreateDialog(Bundle saveInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.confirmacionIncidencia)
                .setMessage(subTipoSelect.getDescripcion())
                .setPositiveButton(R.string.aceptar,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((Incidencia)getActivity()).returnEmpleado(subTipoSelect);
                                dialog.cancel();
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

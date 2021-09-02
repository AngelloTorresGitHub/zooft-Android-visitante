package com.example.zooft.cuadroDialogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zooft.Incidencia;
import com.example.zooft.Models.SubTipo;
import com.example.zooft.R;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{

    private List<SubTipo> listaSubtipos;
    int posSeleccionada = -1;
    private Context context;

    public Adaptador(List<SubTipo> listaSubtipos) {

        this.listaSubtipos = listaSubtipos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_sub_tipos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubTipo subTipoSelect = listaSubtipos.get(position);
        holder.txtSubTipo.setText(subTipoSelect.getDescripcion());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Incidencia)holder.itemView.getContext()).llamarCuadroIncidencia(subTipoSelect);
            }
        });
    }

    @Override
    public int getItemCount() {

        return listaSubtipos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtSubTipo;
        private final CardView cardView;

        public ViewHolder(View v) {
            super(v);
            txtSubTipo = (TextView) v.findViewById(R.id.txtSubTipo);
            cardView = (CardView) v.findViewById(R.id.cardSubTipos);
        }
    }
}

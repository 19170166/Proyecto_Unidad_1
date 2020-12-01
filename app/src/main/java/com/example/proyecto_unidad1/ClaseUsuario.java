package com.example.proyecto_unidad1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClaseUsuario extends RecyclerView.Adapter<ClaseUsuario.Viewholder> {

    private final List<ConstUsuario> usuarioList;
    public ClaseUsuario(List<ConstUsuario> usuarioList){this.usuarioList=usuarioList;}

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistausuario = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent,false);
        return new Viewholder(vistausuario);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String contenido = usuarioList.get(position).getTexto();
        holder.setData(contenido);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.mostrar_datos);
        }

        public void setData(String contenido){
            textView.setText(contenido);
        }
    }
}

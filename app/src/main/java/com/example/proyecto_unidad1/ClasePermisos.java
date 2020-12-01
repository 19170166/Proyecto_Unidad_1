package com.example.proyecto_unidad1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClasePermisos extends RecyclerView.Adapter<ClasePermisos.Viewholder> implements View.OnClickListener{

    private List<ConstPermisos> permisosList;
    private View.OnClickListener listener;
    public ClasePermisos(List<ConstPermisos> permisosList){this.permisosList=permisosList;}

    @NonNull
    @Override
    public ClasePermisos.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaPermiso= LayoutInflater.from(parent.getContext()).inflate(R.layout.recylce_permisos,parent,false);
        vistaPermiso.findViewById(R.id.sitch).setOnClickListener(this);
        return new Viewholder(vistaPermiso);
    }

    @Override
    public void onBindViewHolder(@NonNull ClasePermisos.Viewholder holder, int position) {
        String cont=permisosList.get(position).getPermiso();
        Switch per=permisosList.get(position).getSitch();
        holder.setData(cont);
        holder.permitir.setOnClickListener(this);
    }

    @Override
    public int getItemCount() { return permisosList.size(); }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static  class Viewholder extends RecyclerView.ViewHolder{
        private final TextView permiso;
        private final Switch permitir;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            permiso = itemView.findViewById(R.id.mostrar);
            permitir = itemView.findViewById(R.id.sitch);
        }

        public void setData(String contenido){

            permiso.setText(contenido);
        }
    }
}

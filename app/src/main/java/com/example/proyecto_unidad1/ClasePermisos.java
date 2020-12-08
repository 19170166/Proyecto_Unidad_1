package com.example.proyecto_unidad1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.List;

public class ClasePermisos extends RecyclerView.Adapter<ClasePermisos.Viewholder>{

    private final List<ConstPermisos> permisosList;
    private final Activity context;
    private View.OnClickListener listener;
    private final String[] arreglo_permisos;
    private final int REQUEST_CODE;
    public ClasePermisos(List<ConstPermisos> permisosList, Activity context, String[] permisos, int REQUEST_CODE){
        this.permisosList=permisosList;
        this.context=context;
        this.arreglo_permisos=permisos;
        this.REQUEST_CODE=REQUEST_CODE;
    }

    @NonNull
    @Override
    public ClasePermisos.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaPermiso= LayoutInflater.from(parent.getContext()).inflate(R.layout.recylce_permisos,parent,false);

        return new Viewholder(vistaPermiso);
    }

    @Override
    public void onBindViewHolder(@NonNull ClasePermisos.Viewholder holder, int position) {
        String cont=permisosList.get(position).getPermiso();
        holder.permitir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try{
                        Log.d("permiso2",arreglo_permisos[position]);

                        int permisostorage= ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        int permisolocation=ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);

                        //ActivityCompat.requestPermissions(context, new String[]{arreglo_permisos[position]},REQUEST_CODE);
                        context.requestPermissions(new String[]{arreglo_permisos[position]},REQUEST_CODE);

                        Toast.makeText(buttonView.getContext(),arreglo_permisos[position],Toast.LENGTH_SHORT).show();;

                        if(permisolocation== PackageManager.PERMISSION_GRANTED && permisostorage==PackageManager.PERMISSION_GRANTED){
                            Intent i = new Intent(context,Login.class);
                            context.startActivity(i);
                            context.finish();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        holder.setData(cont);

    }

    @Override
    public int getItemCount() { return permisosList.size(); }



    public static  class Viewholder extends RecyclerView.ViewHolder{
        private final TextView permiso;
        private final Switch permitir;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            permiso = itemView.findViewById(R.id.mostrar);
            permitir = itemView.findViewById(R.id.sitch);
        }

        public void setData(String contenido){

            //permiso.setText(contenido);
            permitir.setText(contenido);
        }
    }

}
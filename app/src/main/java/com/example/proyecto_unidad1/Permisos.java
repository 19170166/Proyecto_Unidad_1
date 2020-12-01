package com.example.proyecto_unidad1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Permisos extends AppCompatActivity {

    private final static int REQUEST_CODE_ask_permissions = 1;
    private RecyclerView recyclerView;
    private String[] permisos = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);

        recyclerView=findViewById(R.id.recycle_permiso);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        List<ConstPermisos> permisosList=new ArrayList<>();

        for(int i=0;i<permisos.length;i++){
            permisosList.add(new ConstPermisos(permisos[i]));
            Log.d("permisos",permisos[i]);
        }
        ClasePermisos adapter= new ClasePermisos(permisosList);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Permisos.this,"accion del onclick: ",Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        int permisostorage= ActivityCompat.checkSelfPermission(Permisos.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permisolocation=ActivityCompat.checkSelfPermission(Permisos.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if(permisolocation== PackageManager.PERMISSION_DENIED||permisostorage==PackageManager.PERMISSION_DENIED){
            Toast.makeText(Permisos.this,"Los permisos son necesarios",Toast.LENGTH_SHORT).show();
        }
        /*if(permisolocation!=PackageManager.PERMISSION_GRANTED||permisostorage!=PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                for(int i=0;i<new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION}.length;i++){
                    //permisosList.add(new ConstPermisos())
                }
            }
        }*/



        if(permisolocation==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(Permisos.this,"Permiso 1 concedido",Toast.LENGTH_SHORT).show();
        }
        if(permisostorage==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(Permisos.this,"Permiso 2 concedido",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int resultado=-1;
        if(requestCode==REQUEST_CODE_ask_permissions){
            for(int i=0;i<grantResults.length;i++){
                resultado=grantResults[i];
                if(resultado==-1){
                    i=grantResults.length+1;
                }
            }
            if(resultado==0){
                Intent i = new Intent(Permisos.this,Login.class);
                startActivity(i);
                finish();
            }
        }
    }
}
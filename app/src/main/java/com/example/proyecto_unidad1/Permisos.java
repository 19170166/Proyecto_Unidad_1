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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permisos extends AppCompatActivity {

    ArrayList<ConstPermisos> PermisosList;
    private final static int REQUEST_CODE_ask_permissions = 1;
    private RecyclerView recyclerView;
    Switch SwitchP;
    private String[] permisos = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);

        int permisostorage= ActivityCompat.checkSelfPermission(Permisos.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permisolocation=ActivityCompat.checkSelfPermission(Permisos.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        PermisosList=new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.recyclepermisos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        for(int i=0;i<permisos.length;i++){
            PermisosList.add(new ConstPermisos(permisos[i]));
            Log.d("permisos",permisos[i]);
        }
        ClasePermisos adapter= new ClasePermisos(PermisosList,Permisos.this,permisos,REQUEST_CODE_ask_permissions);



        Button button=(Button) findViewById(R.id.continuar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(permisolocation== PackageManager.PERMISSION_GRANTED && permisostorage==PackageManager.PERMISSION_GRANTED){
                    Intent i = new Intent(Permisos.this,Login.class);
                    startActivity(i);
                    finish();
                }

            }
        });

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        if(permisolocation== PackageManager.PERMISSION_DENIED||permisostorage==PackageManager.PERMISSION_DENIED){
            Toast.makeText(Permisos.this,"Los permisos son necesarios",Toast.LENGTH_SHORT).show();
        }




        if(permisolocation==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(Permisos.this,"Permiso 1 concedido",Toast.LENGTH_SHORT).show();
        }
        if(permisostorage==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(Permisos.this,"Permiso 2 concedido",Toast.LENGTH_SHORT).show();
        }
        Log.d("concede","entre al activity");
        if(permisolocation==PackageManager.PERMISSION_GRANTED && permisostorage==PackageManager.PERMISSION_GRANTED){

            Intent i = new Intent(Permisos.this,Login.class);
            startActivity(i);
            finish();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("conceder",Arrays.toString(permissions));
        Log.d("conceder", Arrays.toString(grantResults));
        int resultado=-1;
        if(requestCode==REQUEST_CODE_ask_permissions){
            for(int i=0;i<grantResults.length;i++){
                resultado=grantResults[i];
                if(resultado==-1){
                    i=grantResults.length+1;
                }
            }
            /*if(resultado==0){
                Intent i = new Intent(Permisos.this,Login.class);
                startActivity(i);
                finish();
            }*/
        }

    }
}
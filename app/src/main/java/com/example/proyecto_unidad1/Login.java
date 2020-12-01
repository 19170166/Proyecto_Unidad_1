package com.example.proyecto_unidad1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.RequestQueue;

public class Login extends AppCompatActivity {

    protected RequestQueue datos;
    private SingleRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        request=SingleRequest.getInstance(this.getApplicationContext());
        datos=request.getMyrequest();

        String url = "http://192.168.137.1:8000/api/login";



    }
}
package com.example.proyecto_unidad1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    protected RequestQueue datos;
    private SingleRequest request;
    public EditText nombreR,correoR,pdR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        findViewById(R.id.B_Registrar).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        request = SingleRequest.getInstance(this.getApplicationContext());
        datos=request.getMyrequest();

        Log.d("Ver","entre en el onclick");

        String url= "http://192.168.1.105:8000/api/registro";

        nombreR=(EditText) findViewById(R.id.NombreR);
        correoR=(EditText) findViewById(R.id.CorreoR);
        pdR=(EditText) findViewById(R.id.ContraseñaR);
        Log.d("datos",nombreR.getText().toString());
        Log.d("datos",correoR.getText().toString());
        Log.d("datos",pdR.getText().toString());
        JSONObject data = new JSONObject();

        try {
            data.put("nombre",nombreR.getText());
            data.put("correo",correoR.getText());
            data.put("password",pdR.getText());
            Log.d("json",data.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("datos",data.toString());
                Toast.makeText(Registro.this,"Usuario Registrado",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        datos.add(jsonObjectRequest);

    }
}
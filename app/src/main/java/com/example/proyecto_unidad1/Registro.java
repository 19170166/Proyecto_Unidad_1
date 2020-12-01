package com.example.proyecto_unidad1;

import androidx.appcompat.app.AppCompatActivity;

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

        String url= "http://192.168.137.1:8000/api/registro";

        nombreR=(EditText) findViewById(R.id.NombreR);
        correoR=(EditText) findViewById(R.id.CorreoR);
        pdR=(EditText) findViewById(R.id.ContraseñaR);
        Log.d("datos",nombreR.getText().toString());
        Log.d("datos",correoR.getText().toString());
        Log.d("datos",pdR.getText().toString());
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("nombre",nombreR.getText());
            jsonObject.put("correo",correoR.getText());
            jsonObject.put("password",pdR.getText());
            Log.d("json",jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest requestjson = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Registro.this,response.toString(),Toast.LENGTH_SHORT);
                Log.i("Request",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        datos.add(requestjson);

    }
}
package com.example.proyecto_unidad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {

    protected RequestQueue datos;
    private SingleRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView reg=findViewById(R.id.registro);

        reg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Login.this,Registro.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.Sesion).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.d("datos","entre en el onclick");
        String url = "http://192.168.1.105:8000/api/login";

        request = SingleRequest.getInstance(this.getApplicationContext());
        datos=request.getMyrequest();

        EditText email = findViewById(R.id.Correo);
        EditText password = findViewById(R.id.Contraseña);

        JSONObject data = new JSONObject();
        try {
            data.put("correo",email.getText());
            data.put("password",password.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("datos",data.toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Login.this,"Usuario Logeado",Toast.LENGTH_SHORT);
                try {

                    Log.d("respuesta",response.toString());
                    int pe=response.getInt("id_usuario");
                    boolean permis=response.getBoolean("Permiso");

                    if (permis){
                        Intent i= new Intent(Login.this,MainActivity.class);
                        i.putExtra("id",pe);
                        startActivity(i);
                        finish();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("datos",data.toString());
                Log.d("datos",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this,"Usuario no Regsitrado",Toast.LENGTH_SHORT).show();
            }
        });
        datos.add(jsonObjectRequest);

    }
}
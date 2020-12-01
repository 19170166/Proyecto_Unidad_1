package com.example.proyecto_unidad1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected RequestQueue datos;
    private SingleRequest request;
    private RecyclerView recyclerView;
    public TextView vistaR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = SingleRequest.getInstance(this.getApplicationContext());
        datos=request.getMyrequest();

        //Log.d("Ver","entre en el onclick");

        String url= "http://192.168.1.105:8000/api/usuario/1";
        List<ConstUsuario> usuarioList = new ArrayList<>();
        vistaR=(TextView) findViewById(R.id.mostrar_datos);


        JsonObjectRequest requestjson = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arreglo = response.getJSONArray("usuario");

                    for (int i=0;i<arreglo.length();i++){
                        JSONObject usuario=arreglo.getJSONObject(i);
                        String nombre = usuario.getString("name");
                        String correo = usuario.getString("email");
                        vistaR.append(nombre + " " + correo);
                        usuarioList.add(new ConstUsuario(nombre + " " + correo));
                    }

                    ClaseUsuario adapter = new ClaseUsuario(usuarioList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    /*final Type userType=new TypeToken<List<ConstUsuario>>(){}.getType();
                    List<ConstUsuario> Listausuario = */
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
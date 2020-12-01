package com.example.proyecto_unidad1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.google.gson.Gson;
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

        //Log.d("Ver","entre en el onclick");

        Bundle b=new Bundle();
        b=getIntent().getExtras();
        int id=b.getInt("id");

        request = SingleRequest.getInstance(this.getApplicationContext());
        datos=request.getMyrequest();

        String url= "http://192.168.1.105:8000/api/usuario/"+id;
        List<ConstUsuario> usuarioList = new ArrayList<>();
        vistaR=(TextView) findViewById(R.id.mostrar_datos);

        Log.d("res",url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("res2",response.toString());

                    String nombre=response.getString("nombre");
                    String correo=response.getString("correo");

                    TextView n=(TextView) findViewById(R.id.nom);
                    n.setText(nombre);
                    TextView c=(TextView) findViewById(R.id.email);
                    c.setText(correo);

                    //Log.d("res",usu);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.i("Request",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        datos.add(jsonObjectRequest);

    }
}
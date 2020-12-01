package com.example.proyecto_unidad1;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingleRequest {

    private static SingleRequest request=null;

    private RequestQueue myrequest;

    private SingleRequest(Context context) {
        myrequest= Volley.newRequestQueue(context);
    }

    public static SingleRequest getInstance(Context context){
        if (request==null){
            request=new SingleRequest(context);
        }
        return request;
    }

    public RequestQueue getMyrequest(){return myrequest;}

}

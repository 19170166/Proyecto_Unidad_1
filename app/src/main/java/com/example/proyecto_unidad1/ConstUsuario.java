package com.example.proyecto_unidad1;

import android.widget.TextView;

public class ConstUsuario {

    public ConstUsuario(TextView cont) {
        this.cont = cont;
    }

    public TextView getCont() {
        return cont;
    }

    public void setCont(TextView cont) {
        this.cont = cont;
    }

    private TextView cont;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public ConstUsuario(TextView cont, String texto) {
        this.cont = cont;
        this.texto = texto;
    }

    public ConstUsuario(String texto) {
        this.texto = texto;
    }

    private String texto;


}

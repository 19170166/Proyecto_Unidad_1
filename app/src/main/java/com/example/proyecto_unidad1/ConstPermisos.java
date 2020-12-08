package com.example.proyecto_unidad1;

import android.widget.Switch;

public class ConstPermisos {
    public ConstPermisos(String texto) {
        this.texto = texto;
    }

    private String texto;

    public Switch getSitch() {
        return sitch;
    }
    public int getIntSwitch(){
        return sitch.getId();
    }

    public void setSitch(Switch sitch) {
        this.sitch = sitch;
    }

    public ConstPermisos(Switch sitch) {
        this.sitch = sitch;
    }

    private Switch sitch;

    public String getPermiso(){
        return texto;
    }
}
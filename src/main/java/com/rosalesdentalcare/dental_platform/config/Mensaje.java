package com.rosalesdentalcare.dental_platform.config;

public class Mensaje {

    private String mensaje;

    protected Mensaje() {
        System.out.println();
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}

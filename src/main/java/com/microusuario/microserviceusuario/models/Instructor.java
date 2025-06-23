package com.microusuario.microserviceusuario.models;


public class Instructor extends Usuario {

    private String cursoAsignado;


    public Instructor() {
        super(); 
    }

    public Instructor(int id, String run, String nombre, String apellido, String correo, String contrasena,
            String cursoAsignado) {
        super();
        this.cursoAsignado = cursoAsignado;
    }

    public Instructor(String cursoAsignado) {
        super();
        this.cursoAsignado = cursoAsignado;
    }

    public String getCursoAsignado() {
        return cursoAsignado;
    }

    public void setCursoAsignado(String cursoAsignado) {
        this.cursoAsignado = cursoAsignado;
    }

    



}

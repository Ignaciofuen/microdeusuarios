package com.microusuario.microserviceusuario.models;

public class Administrador extends Usuario{
    private String adminCode;

    public Administrador(int id, String run, String nombre, String apellido, String correo, String contraseña,
            String adminCode) {
        super(id, run, nombre, apellido, correo, contraseña);
        this.adminCode = adminCode;
    }

    public Administrador(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }


    

   
}

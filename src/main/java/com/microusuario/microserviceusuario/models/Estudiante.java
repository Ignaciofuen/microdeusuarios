package com.microusuario.microserviceusuario.models;



public class Estudiante extends Usuario {
    private String cursoInscrito;

    public Estudiante(int id, String run, String nombre, String apellido, String correo, String contraseña,
            String cursoInscrito) {
        super(id, run, nombre, apellido, correo, contraseña);
        this.cursoInscrito = cursoInscrito;
    }

    public Estudiante(String cursoInscrito) {
        this.cursoInscrito = cursoInscrito;
    }

    public String getCursoInscrito() {
        return cursoInscrito;
    }

    public void setCursoInscrito(String cursoInscrito) {
        this.cursoInscrito = cursoInscrito;
    }
    

    
    
}

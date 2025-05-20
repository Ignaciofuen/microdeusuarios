package com.microusuario.microserviceusuario.models;



public class Estudiante extends Usuario {
    private String cursoInscrito;


    public Estudiante() {
        super(); 
    }

    public Estudiante(int id, String run, String nombre, String apellido, String correo, String contrasena,
            String cursoInscrito) {
        super(id, run, nombre, apellido, correo, contrasena);
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

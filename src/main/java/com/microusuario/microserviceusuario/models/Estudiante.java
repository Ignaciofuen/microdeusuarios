package com.microusuario.microserviceusuario.models;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
public class Estudiante extends Usuario {
    private List<String> cursoInscrito;

    public Estudiante(int id, String run, String nombre, String apellido, String correo, String contrasena, List<String> cursoInscrito) {
        super(id, run, nombre, apellido, correo, contrasena);
        this.cursoInscrito = cursoInscrito;
    } 
    
}

package com.microusuario.microserviceusuario.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    protected int id;
    protected String run;
    protected String nombre;
    protected String apellido;
    protected String correo;
    protected String contrasena;



}

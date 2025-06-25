package com.microusuario.microserviceusuario.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private int id;
    private String run;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
}

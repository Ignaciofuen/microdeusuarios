package com.microusuario.microserviceusuario.models.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int Id;
    private String run; 
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;

    

    
}

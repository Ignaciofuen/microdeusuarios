package com.microusuario.microserviceusuario.models.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InstructorEntity extends UsuarioEntity  {
    private String cursoAsingnado;
    

}

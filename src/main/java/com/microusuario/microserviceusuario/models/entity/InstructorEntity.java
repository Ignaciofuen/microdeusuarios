package com.microusuario.microserviceusuario.models.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("INSTRUCTOR")
public class InstructorEntity extends UsuarioEntity  {
    private String cursoAsignado;
    

}

package com.microusuario.microserviceusuario.models.entity;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("ESTUDIANTE")
public class EstudianteEntity extends UsuarioEntity{

    @ElementCollection
    private List<String> cursoInscrito;


    
}

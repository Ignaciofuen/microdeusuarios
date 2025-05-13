package com.microusuario.microserviceusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microusuario.microserviceusuario.models.entity.InstructorEntity;

@Repository

public interface InstructorRepository extends JpaRepository<InstructorEntity, Integer> {
    // Si querés buscar por curso o correo
    InstructorEntity findBycorreo(String cursoAsingnado);
    Boolean existsByCorreo(String cursoAsingnado);
}
package com.microusuario.microserviceusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microusuario.microserviceusuario.models.entity.InstructorEntity;

@Repository

public interface InstructorRepository extends JpaRepository<InstructorEntity, Integer> {
    InstructorEntity findByCorreo(String cursoAsignado);
    Boolean existsByCorreo(String cursoAsignado);
    long count();
}
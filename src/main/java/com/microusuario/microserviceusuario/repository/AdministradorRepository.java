package com.microusuario.microserviceusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microusuario.microserviceusuario.models.entity.AdministradorEntity;

@Repository
public interface AdministradorRepository extends JpaRepository<AdministradorEntity, Integer> {

    AdministradorEntity findByCorreo(String correo);
    Boolean existsByCorreo(String correo);
    long count();

}

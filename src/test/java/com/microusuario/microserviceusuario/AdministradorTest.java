package com.microusuario.microserviceusuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.microusuario.microserviceusuario.models.Administrador;
import com.microusuario.microserviceusuario.models.Estudiante;
import com.microusuario.microserviceusuario.models.entity.AdministradorEntity;
import com.microusuario.microserviceusuario.models.entity.EstudianteEntity;
import com.microusuario.microserviceusuario.repository.AdministradorRepository;
import com.microusuario.microserviceusuario.repository.EstudianteRepository;
import com.microusuario.microserviceusuario.service.AdministradorService;


public class AdministradorTest {

   

    @Mock
    private AdministradorRepository administradorRepository;
     

    @InjectMocks
    private AdministradorService administradorService;

    private Administrador administrador;
    private AdministradorEntity administradorEntity;
    

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        administrador = new Administrador(3, "19883148-k", "ivett", "ramirez", "ra@gmail.com", "ive123", "123");
        administradorEntity = new AdministradorEntity();
        administradorEntity.setId(3);
        administradorEntity.setRun("19883148-k");
        administradorEntity.setNombre("ivett");
        administradorEntity.setApellido("ramirez");
        administradorEntity.setCorreo("ra@gmail.com");
        administradorEntity.setContrasena("ive123");
        administradorEntity.setAdminCode("123");
    }

     @Test
    public void testAgregarAdministrador_existe(){
        when(administradorRepository.existsByCorreo(any(String.class))).thenReturn(true);
        String result = administradorService.agregarAdministrador(administrador);
        assertEquals("El usuario ya existe", result.trim());

    }


    @Test
    public void testAgregarAdministrador_nuevo(){
        when(administradorRepository.existsById(administrador.getId())).thenReturn(false);
        when(administradorRepository.save(Mockito.any(AdministradorEntity.class))).thenReturn(administradorEntity);
        String result = administradorService.agregarAdministrador(administrador);
        assertEquals("Administrador agregado correctamente", result.trim());
    }

    @Test
    public void borrar_administrador(){
        when(administradorRepository.existsById(3)).thenReturn(true);
        doNothing().when(administradorRepository).deleteById(3);;
        String result = administradorService.borrarAdministrador(3); 
        assertEquals("Administrador eliminado correctamente", result.trim());
    }



    


}

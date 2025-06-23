package com.microusuario.microserviceusuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.microusuario.microserviceusuario.models.Estudiante;
import com.microusuario.microserviceusuario.models.entity.EstudianteEntity;
import com.microusuario.microserviceusuario.repository.EstudianteRepository;
import com.microusuario.microserviceusuario.service.EstudianteService;

import io.micrometer.core.ipc.http.HttpSender.Response;

public class EstudianteTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    private Estudiante estudiante;
    private EstudianteEntity estudianteEntity;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        estudiante = new Estudiante(1, "19883148-4", "juan", "fuentes", "fu@gmailcom", "fu123", List.of("FS1"));
        estudianteEntity = new EstudianteEntity();
        estudianteEntity.setId(1);
        estudianteEntity.setRun("19883148-4");
        estudianteEntity.setNombre("juan");
        estudianteEntity.setApellido("fuentes");
        estudianteEntity.setCorreo("fu@gmail.com");
        estudianteEntity.setContrasena("fu123");
        estudianteEntity.setCursoInscrito(List.of("FS1"));
    }

   @Test
    public void testAgregarEstudiante_existe(){
        when(estudianteRepository.existsByCorreo(any(String.class))).thenReturn(true);
        String result = estudianteService.agregarEstudiante(estudiante);
        assertEquals("El usuario ya existe", result.trim());

    }

    
    @Test
    public void testActualizarEstudiante_noexiste() {
        when(estudianteRepository.existsByCorreo(any(String.class))).thenReturn(false);
        Estudiante nuevo = new Estudiante(3, "774009008-3", "los", "prueba", "pru@gmai.com", "pru123", List.of("FS1"));
        String result = estudianteService.agregarEstudiante(nuevo);

       assertEquals("Estudiante Agregado correctamente", result.trim());
    }


   @Test
   public void testAgregarEstudiante_nuevo(){
    when(estudianteRepository.existsById(estudiante.getId())).thenReturn(false);
    when(estudianteRepository.save(Mockito.any(EstudianteEntity.class))).thenReturn(estudianteEntity);
    String result = estudianteService.agregarEstudiante(estudiante);
    assertEquals("Estudiante Agregado correctamente", result.trim());
   }

 

   @Test
   public void borrar_estudiante(){
    when(estudianteRepository.existsById(1)).thenReturn(true);
    doNothing().when(estudianteRepository).deleteById(1);;
    String result = estudianteService.borrarEstudiante(1); 
    assertEquals("estudiante borrado correctamente", result.trim());
   }


    @Test
    public void tesTraerEstuduanteporCorreo(){
        when(estudianteRepository.findByCorreo("fu@gmailcom")).thenReturn(estudianteEntity);
        Estudiante result = estudianteService.traerEstudiante("fu@gmailcom");
        assertNotNull(result);
        assertEquals("juan", result.getNombre());


    }

    @Test
    public void testActualizarContraseña() {
    
        String correo = "fu@gmail.com";
        String nuevaContraseña = "nuevaPass456";

        EstudianteEntity estudianteEntity = new EstudianteEntity();
        estudianteEntity.setCorreo(correo);
        estudianteEntity.setContrasena("antigua123");

        
        when(estudianteRepository.findByCorreo(correo)).thenReturn(estudianteEntity);

    
        boolean resultado = estudianteService.actualizarContraseña(correo, nuevaContraseña);

    
        assertTrue(resultado);  
        assertEquals(nuevaContraseña, estudianteEntity.getContrasena()); 
        verify(estudianteRepository).save(estudianteEntity);  
    }

    @Test
    public void testAgregarCurso(){
        String correo = "fu@gmail.com";
        String nuevoCurso = "java";

        EstudianteEntity estudianteEntity = new EstudianteEntity();
        estudianteEntity.setCorreo(correo);
        estudianteEntity.setCursoInscrito(List.of("python"));

        when(estudianteRepository.findByCorreo(correo)).thenReturn(estudianteEntity);

        boolean resultado = estudianteService.actualizarContraseña(correo, nuevoCurso);

        assertTrue(resultado); 
        assertEquals(nuevoCurso, estudianteEntity.getContrasena()); 
        verify(estudianteRepository).save(estudianteEntity); 

    }


    








}

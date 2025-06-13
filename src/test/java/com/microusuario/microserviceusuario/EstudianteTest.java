package com.microusuario.microserviceusuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
        estudiante = new Estudiante(1, "19883148-4", "juan", "fuentes", "fu@gmailcom", "fu123", "ingles");
        estudianteEntity = new EstudianteEntity();
        estudianteEntity.setId(1);
        estudianteEntity.setRun("19883148-4");
        estudianteEntity.setNombre("juan");
        estudianteEntity.setApellido("fuentes");
        estudianteEntity.setCorreo("fu@gmail.com");
        estudianteEntity.setContrasena("fu123");
        estudianteEntity.setCursoInscrito("ingles");
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
        Estudiante nuevo = new Estudiante(3, "774009008-3", "los", "prueba", "pru@gmai.com", "pru123", "matematicas");
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
}

package com.microusuario.microserviceusuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.microusuario.microserviceusuario.models.Instructor;
import com.microusuario.microserviceusuario.models.entity.InstructorEntity;
import com.microusuario.microserviceusuario.repository.InstructorRepository;
import com.microusuario.microserviceusuario.service.InstructorService;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public class InstructorTest {
    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorService instructorService;

    private Instructor instructor;
    private InstructorEntity instructorEntity;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        instructor = new Instructor(2, "19889234-1", "pablo", "marmol", "mar@gmail.com", "mar123", "ingles");
        instructorEntity = new InstructorEntity();
        instructorEntity.setId(2);
        instructorEntity.setRun("19889234-1");
        instructorEntity.setNombre("pablo");
        instructorEntity.setApellido("marmol");
        instructorEntity.setCorreo("mar@gmail.com");
        instructorEntity.setContrasena("mar123");
        instructorEntity.setCursoAsignado("ingles");

    }

    @Test
    public void TestAgregarInstructor_existe() {
        when(instructorRepository.existsByCorreo(instructor.getCorreo())).thenReturn(true);
        String result = instructorService.agregarInstructor(instructor);
        assertEquals("El usuario ya existe", result.trim());
        }
    

    @Test
    public void testAgregarInstructor_nuevo(){
        when(instructorRepository.existsByCorreo(instructor.getCorreo())).thenReturn(false);
        when(instructorRepository.save(any(InstructorEntity.class))).thenReturn(instructorEntity);

        String result = instructorService.agregarInstructor(instructor);
        assertEquals("Instructor agregado correctamente", result.trim());
    }

    @Test
    public void borrar_instructor(){
        when(instructorRepository.existsById(2)).thenReturn(true);
        doNothing().when(instructorRepository).deleteById(1);;
        String result = instructorService.borrarInstructor(2); 
        assertEquals("instructor borrado correctamente", result.trim());
    }

    @Test
    public void traerInstructorPorCorreo(){
        when(instructorRepository.findByCorreo("mar@gmail.com")).thenReturn(instructorEntity);
        when(instructorRepository.existsByCorreo("mar@gmail.com")).thenReturn(true);
        Instructor result = instructorService.traerInstructor("mar@gmail.com");
        assertNotNull(result);
        assertEquals("pablo", result.getNombre());
    }
    }




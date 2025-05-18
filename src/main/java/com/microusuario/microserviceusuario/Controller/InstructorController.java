package com.microusuario.microserviceusuario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microusuario.microserviceusuario.models.Estudiante;
import com.microusuario.microserviceusuario.models.Instructor;

import com.microusuario.microserviceusuario.repository.InstructorRepository;
import com.microusuario.microserviceusuario.service.EstudianteService;
import com.microusuario.microserviceusuario.service.InstructorService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class InstructorController {

    private final InstructorRepository instructorRepository;

    @Autowired
    private InstructorService instructorService;
    InstructorService accionesInstructores = new InstructorService(); 

        InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }


   
    @Operation(summary = "agregar instructor")
    @PostMapping("/agregar instructor")
    public ResponseEntity<String> agregarInstructor(@RequestBody Instructor instructor) {
        return ResponseEntity.ok(instructorService.agregarInstructor(instructor));
    }

    @Operation(summary = "traer instructor")
    @GetMapping("/instructor/{correo}")
    public ResponseEntity<Instructor>traerInstructor(@PathVariable String correo){
        return ResponseEntity.ok(instructorService.traerInstructor(correo));
    }

    @Operation(summary = "borrar instructor")
    @DeleteMapping("/instructores/{id}")
    public String borrarInstructor(@PathVariable int id ){
        return accionesInstructores.borrarInstructor(id);
    }
  

}    
package com.microusuario.microserviceusuario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.microusuario.microserviceusuario.models.Instructor;

import com.microusuario.microserviceusuario.repository.InstructorRepository;

import com.microusuario.microserviceusuario.service.InstructorService;

import io.swagger.v3.oas.annotations.Operation;
@RequestMapping("/instructor")
@RestController
public class InstructorController {

    private final InstructorRepository instructorRepository;

    @Autowired
    private InstructorService instructorService;
    InstructorService accionesInstructores = new InstructorService(); 

        InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }


   
    @Operation(summary = "agregar_instructor")
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarInstructor(@RequestBody Instructor instructor) {
        String nuevoInstructor = instructorService.agregarInstructor(instructor);

        if (nuevoInstructor.equals("El usuario ya existe")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(nuevoInstructor);
        }else if (nuevoInstructor.equals("Instructor agregado correctamente")){
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInstructor);

        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(nuevoInstructor);
        }
        

       
    }


        

    @Operation(summary = "traer_instructor")
    @GetMapping("/traer/{correo}")
    public ResponseEntity<Instructor>traerInstructor(@PathVariable String correo){
        return ResponseEntity.ok(instructorService.traerInstructor(correo));
    }


    @Operation(summary = "borrar_instructor")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarInstructor(@PathVariable int id) {
        String resultado = instructorService.borrarInstructor(id);
        if (resultado.equals("instructor borrado correctamente")) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


  

}    
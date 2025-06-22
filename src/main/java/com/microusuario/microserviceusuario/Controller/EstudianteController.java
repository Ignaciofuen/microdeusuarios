package com.microusuario.microserviceusuario.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microusuario.microserviceusuario.models.Estudiante;

import com.microusuario.microserviceusuario.repository.EstudianteRepository;

import com.microusuario.microserviceusuario.service.EstudianteService;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/estudiante")
@RestController
public class EstudianteController{
    @Autowired
    private EstudianteService estudianteService;
    EstudianteService accionesEstudiante = new EstudianteService();

    EstudianteController(EstudianteRepository estudianteRepository) {
    }



    @Operation(summary = "traer_estudiantes")
    @GetMapping("/traer/{correo}")
    public ResponseEntity<Estudiante>traerEstudiante(@PathVariable String correo){
        Estudiante estudiante = estudianteService.traerEstudiante(correo);
        if(estudiante == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(estudiante);
       
    }

    
    @Operation(summary = "agregar_estudiante")
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarEstudiante(@RequestBody Estudiante estudiante) {
        String nuevoEstudiante = estudianteService.agregarEstudiante(estudiante);
        
        if (nuevoEstudiante.equals("El usuario ya existe")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(nuevoEstudiante);
        }else if (nuevoEstudiante.equals("Estudiante Agregado correctamente ")){
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);

        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(nuevoEstudiante);
        }
        
    }


    @Operation(summary = "borrar_estudiante")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarEstudiante(@PathVariable int id) {
        String resultado = estudianteService.borrarEstudiante(id);
        if (resultado.equals("estudiante borrado correctamente")) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

   

    
    @Operation(summary = "inscribir_curso")
    @PutMapping("/inscribir/{correo}/{nuevoCurso}")
    public ResponseEntity<String> inscribirCurso(@PathVariable String correo, @PathVariable("nuevoCurso") String nuevoCurso) {
        String nuevo = estudianteService.inscribirCurso(correo, nuevoCurso);
        
        if (nuevo.equals("Estudiante no encontrado")) {
            return ResponseEntity.status(404).body(nuevo);
        }
        if (nuevo.equals("Ocurrió un error al inscribir el curso")) {
                return ResponseEntity.status(500).body(nuevo); 
        }else{
            return ResponseEntity.status(201).body(nuevo); 
        }
  
    }
    
    
    @Operation(summary = "actualizar_contraseña")
    @PutMapping("/actualizarContraseña/{correo}")
    public ResponseEntity<String> actualizarEstudiante(@PathVariable String correo, @RequestBody String nuevaContraseña) {
    boolean actualizado = estudianteService.actualizarContraseña(correo, nuevaContraseña);
        if (actualizado) {
            return ResponseEntity.status(201).body("Contraseña actualizada correctamente");
        } else {
            return ResponseEntity.status(404).body("Estudiante no encontrado");
        }

    }   



}
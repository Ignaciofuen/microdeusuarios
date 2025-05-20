package com.microusuario.microserviceusuario.Controller;


import org.springframework.beans.factory.annotation.Autowired;
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
    private final EstudianteRepository estudianteRepository;
    @Autowired
    private EstudianteService estudianteService;
    EstudianteService accionesEstudiante = new EstudianteService();

    EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Operation(summary = "traer_estudiantes")
    @GetMapping("/traer/{correo}")
    public ResponseEntity<Estudiante>traerEstudiante(@PathVariable String correo){
        return ResponseEntity.ok(estudianteService.traerEstudiante(correo));
    }
    
    @Operation(summary = "agregar_estudiante")
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarEstudiante(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.agregarEstudiante(estudiante));
    }
    
    @Operation(summary = "borrar_estudiante")
    @DeleteMapping("/borrar/{id}")
    public String borrarEstudiante(@PathVariable int id ){
        return estudianteService.borrarEstudiante(id);
    }

    @Operation(summary = "inscribir_curso")
    @GetMapping("/inscribir/{correo}/{nuevoCurso}")
    public String inscribirCurso(@PathVariable String correo, @PathVariable("nuevoCurso") String nuevoCurso) {
    return estudianteService.inscribirCurso(correo, nuevoCurso);
    }
  

    @Operation(summary = "actualizar_nombre")
    @PutMapping("/actualizarNombre/{correo}")
    public ResponseEntity<String> actualizarEstudiante(@PathVariable String correo, @RequestBody String nuevoNombre) {
    return estudianteService.ActualizarNombre(correo, nuevoNombre);
    }


}

package com.microusuario.microserviceusuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microusuario.microserviceusuario.models.Estudiante;

import com.microusuario.microserviceusuario.repository.EstudianteRepository;

import com.microusuario.microserviceusuario.service.EstudianteService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class EstudianteController{
    private final EstudianteRepository estudianteRepository;
    @Autowired
    private EstudianteService estudianteService;
    EstudianteService accionesEstudiante = new EstudianteService();

    EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }


    @GetMapping("/estudiantes/{correo}")
    public ResponseEntity<Estudiante>traerEstudiante(@PathVariable String correo){
        return ResponseEntity.ok(estudianteService.traerEstudiante(correo));
    }
    
    @Operation(summary = "agregar estudiante")
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarEstudiante(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.agregarEstudiante(estudiante));
    }

    
    @GetMapping("/inscribir/{correo}/{nuevoCurso}")
    public String inscribirCurso(@PathVariable String correo,@PathVariable String neuvoCurso){
        return estudianteService.inscribirCurso(correo, neuvoCurso);
    }    

}

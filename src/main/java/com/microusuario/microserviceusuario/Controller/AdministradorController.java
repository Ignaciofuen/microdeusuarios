package com.microusuario.microserviceusuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microusuario.microserviceusuario.models.Administrador;
import com.microusuario.microserviceusuario.models.Estudiante;
import com.microusuario.microserviceusuario.models.Instructor;
import com.microusuario.microserviceusuario.repository.AdministradorRepository;
import com.microusuario.microserviceusuario.service.AdministradorService;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/administrador")
@RestController
public class AdministradorController {

    private final AdministradorRepository administradorRepository;

    @Autowired
    private AdministradorService administradorService;
    AdministradorService accionesAdmministrador = new AdministradorService();

    AdministradorController(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    
    @Operation(summary = "lista de studiantes")
    @GetMapping("/Estudiantes")
    public ResponseEntity<List<Estudiante>> verEstudiantes() {    
    List<Estudiante> estudiantes = administradorService.obtenerEstudiantesAdm();
    return ResponseEntity.ok(estudiantes);
    }

    @Operation(summary = "lista de instructores")
    @GetMapping("/Instructores")
    public ResponseEntity<List<Instructor>> verInstructores() { 
    List<Instructor> instructores = administradorService.obInstructoresAdm();
    return ResponseEntity.ok(instructores);
    }

    
    @Operation(summary = "lista de instrutores con cursos")
    @GetMapping("/Intructor-curso")
    public List<String> CursoInstructores() {
        return administradorService.obtenerInstructoresConCurso();
    }

    @Operation(summary = "agregar_administrador")
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarAdministrador(@RequestBody Administrador administrador) {
        return ResponseEntity.ok(administradorService.agregarAdministrador(administrador));
    }

    @Operation(summary = "borrar")
    @DeleteMapping("/borrar/{id}")
    public String borrarAdministrador(@PathVariable int id ){
        return administradorService.borrarAdministrador(id);
    }


    

    
    
}
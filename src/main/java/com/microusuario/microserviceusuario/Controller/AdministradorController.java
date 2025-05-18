package com.microusuario.microserviceusuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microusuario.microserviceusuario.models.Estudiante;
import com.microusuario.microserviceusuario.models.Instructor;
import com.microusuario.microserviceusuario.repository.AdministradorRepository;
import com.microusuario.microserviceusuario.service.AdministradorService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class AdministradorController {

    private final AdministradorRepository administradorRepository;

    @Autowired
    private AdministradorService administradorService;
    AdministradorService accionesAdmministrador = new AdministradorService();

    AdministradorController(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    
    @Operation(summary = "lista de estudiantes")
    @GetMapping("/Estudiantes")
    public List<Estudiante> verEstudiantes() {
        return accionesAdmministrador.obtenerEstudiantesAdm();
    }
    @Operation(summary = "lista de instructores")
    @GetMapping("/Instructores")
    public List<Instructor> verInstructores() {
        return accionesAdmministrador.obInstructoresAdm();
    }
    @Operation(summary = "lista de instrutores con cursos")
    @GetMapping("/Intructor-curso")
    public List<String> CursoInstructores() {
        return accionesAdmministrador.obtenerInstructoresConCurso();
    }
}
package com.microusuario.microserviceusuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microusuario.microserviceusuario.models.Estudiante;
import com.microusuario.microserviceusuario.models.Instructor;
import com.microusuario.microserviceusuario.service.AdministradorService;

@RestController
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/Estudiantes")
    public List<Estudiante> verEstudiantes() {
        return administradorService.obtenerEstudiantesAdm();
    }

    @GetMapping("/Instructores")
    public List<Instructor> verInstructores() {
        return administradorService.obInstructoresAdm();
    }

    @GetMapping("/Intructor-curso")
    public List<String> CursoInstructores() {
        return administradorService.obtenerInstructoresConCurso();
    }
}
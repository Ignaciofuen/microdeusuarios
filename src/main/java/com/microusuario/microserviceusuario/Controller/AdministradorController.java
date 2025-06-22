package com.microusuario.microserviceusuario.Controller;

import java.util.List;

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

import com.microusuario.microserviceusuario.models.Administrador;
import com.microusuario.microserviceusuario.models.Estudiante;
import com.microusuario.microserviceusuario.models.Instructor;
import com.microusuario.microserviceusuario.repository.AdministradorRepository;
import com.microusuario.microserviceusuario.service.AdministradorService;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/administrador")
@RestController
public class AdministradorController {



    @Autowired
    private AdministradorService administradorService;
    AdministradorService accionesAdmministrador = new AdministradorService(null, null, null, null, null);
    AdministradorController(AdministradorRepository administradorRepository) {
    }

    
    @Operation(summary = "lista de estudiantes")
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

    
    @Operation(summary = "borrar_administrador")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarAdministrador(@PathVariable int id) {
        String resultado = administradorService.borrarAdministrador(id);
        if (resultado.equals("Administrador eliminado correctamente")) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
  

    
    @Operation(summary = "agregar_administrador")
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarAdministrador(@RequestBody Administrador administrador) {
        String nuevoAdministrador = administradorService.agregarAdministrador(administrador);

        if (nuevoAdministrador.equals("El usuario ya existe")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(nuevoAdministrador);
        }else if (nuevoAdministrador.equals("Administrador agregado correctamente")){
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdministrador);

        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(nuevoAdministrador);
        }
    }

    @Operation(summary = "contar estudiantes")
    @GetMapping("/contar-estudiantes")
    public long contarCursos(){
            return administradorService.contarEstudiantes();
        }
    
    @Operation(summary = "contar instructores")
    @GetMapping("/contar-instructores")
    public long contarInstructores(){
            return administradorService.contarInstructores();
        }

}
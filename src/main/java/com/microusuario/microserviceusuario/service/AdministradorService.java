package com.microusuario.microserviceusuario.service;

import java.util.ArrayList;
import java.util.List;
import com.microusuario.microserviceusuario.repository.EstudianteRepository;
import com.microusuario.microserviceusuario.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microusuario.microserviceusuario.models.Administrador;
import com.microusuario.microserviceusuario.models.Estudiante;
import com.microusuario.microserviceusuario.models.Instructor;
import com.microusuario.microserviceusuario.models.entity.AdministradorEntity;
import com.microusuario.microserviceusuario.repository.AdministradorRepository;


@Service
public class AdministradorService {

    private final InstructorRepository instructorRepository;

    private final EstudianteRepository estudianteRepository;

    @Autowired 
    private AdministradorRepository administradorRepository;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private InstructorService instructorService;

     private final List<Administrador> administradores = new ArrayList<>();

    public AdministradorService(EstudianteRepository estudianteRepository, InstructorRepository instructorRepository){
        administradores.add(new Administrador(343, "20998123-k", "jorge", "fuenzalida", "fuen@gmail.com", "hola454", "adm123"));
        this.estudianteRepository = estudianteRepository;
        this.instructorRepository = instructorRepository;

    }

     
    public List<Administrador>obtenerAdministrador(){
        return administradores;
    }

    public List<Estudiante>obtenerEstudiantesAdm(){
      return estudianteService.obtenerEstudiantes();
    }

    public List<Instructor>obInstructoresAdm(){
      return instructorService.obtenerInstructores();
    }

    public List<String>obtenerInstructoresConCurso(){
         List<String> instructoresConCurso = new ArrayList<>();
         for(Instructor ins: instructorService.obtenerInstructores()){
            String nombreCompleto = ins.getNombre() + " " + ins.getApellido();
            String cursoAsignado = ins.getCursoAsignado();
            instructoresConCurso.add(nombreCompleto + " - " + cursoAsignado);

         }
         return instructoresConCurso;
    }

    public String agregarAdministrador(Administrador adm) {
        try {
            boolean estado = administradorRepository.existsByCorreo(adm.getCorreo());
            if(!estado){
                AdministradorEntity nuevoAdministrador = new AdministradorEntity();
                nuevoAdministrador.setRun(adm.getRun());
                nuevoAdministrador.setNombre(adm.getNombre()); 
                nuevoAdministrador.setApellido(adm.getApellido());
                nuevoAdministrador.setCorreo(adm.getCorreo());
                nuevoAdministrador.setContrasena(adm.getContrasena());
                nuevoAdministrador.setAdminCode(adm.getAdminCode());
                administradorRepository.save(nuevoAdministrador);
                return "Administrador agregado correctamente";
            }
            return "El usuario ya existe";           

        }
        catch(Exception e){
            return "Ha ocurrido un error";
        }
    }

    public String borrarAdministrador(int id) {
        try {
            if(administradorRepository.existsById(id)){
                administradorRepository.deleteById(id);
                return "Administrador eliminado correctamente";
            }
            return "El administrador no existe";
        } catch (Exception e) {
            return "Ha ocurrido un error: " + e.getMessage();
        }
    }

    public long contarEstudiantes(){
        return estudianteRepository.count();
    }

    public long contarInstructores(){
        return instructorRepository.count();
    }
}










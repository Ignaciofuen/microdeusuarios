package com.microusuario.microserviceusuario.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microusuario.microserviceusuario.models.Estudiante;

import com.microusuario.microserviceusuario.models.entity.EstudianteEntity;

import com.microusuario.microserviceusuario.repository.EstudianteRepository;



@Service
public class EstudianteService{

    @Autowired
    private EstudianteRepository estudianteRepository;

    private final List<Estudiante> estudiantes = new ArrayList<>();
    

    public EstudianteService(){
        estudiantes.add(new Estudiante(121, "198883148-4", "juan", "fernandes", "jnandes@gmail.com", "hola123", "ingles"));       
        
    }

    public List<Estudiante>obtenerEstudiantes(){
        return estudiantes;
    }

    


    public String agregarEstudiante(Estudiante est){
        try{
            Boolean estado = estudianteRepository.existsByCorreo(est.getCorreo());
            if (!estado){
                EstudianteEntity nuevoEstudiante = new EstudianteEntity();
                nuevoEstudiante.setId(est.getId());
                nuevoEstudiante.setRun(est.getRun());
                nuevoEstudiante.setNombre(est.getNombre());
                nuevoEstudiante.setApellido(est.getApellido());
                nuevoEstudiante.setCorreo(est.getCorreo());
                nuevoEstudiante.setContraseña(est.getContraseña());
                nuevoEstudiante.setCursoInscrito(est.getCursoInscrito());
                estudianteRepository.save(nuevoEstudiante);
                return "Estudiante Agregado correctamente ";
            }
            return "El usuario ya existe ";
        }
        catch(Exception e){
            return " ha ocurrido un error ";
        }

    }

     public Estudiante traerEstudiante(String correo){
        try{
            EstudianteEntity est = estudianteRepository.findByCorreo(correo);
            if (est!=null){
                Estudiante estudianteNuevo = new Estudiante(
                    est.getId(),
                    est.getRun(),
                    est.getNombre(),
                    est.getApellido(),
                    est.getCorreo(),
                    est.getContraseña(),
                    est.getCursoInscrito()
                );
                return estudianteNuevo;

            }
            return null;


        }
        catch (Exception e){
            return null;
        }
        

    }
 

    public String inscribirCurso(String correo, String nuevoCurso) {
        try {
            EstudianteEntity estudiante = estudianteRepository.findByCorreo(correo);
            if (estudiante != null) {
                estudiante.setCursoInscrito(nuevoCurso);
                estudianteRepository.save(estudiante);
                return "Curso inscrito correctamente";
            } else {
                return "Estudiante no encontrado";
            }
        } catch (Exception e) {
            return "Ocurrió un error al inscribir el curso";
        }

    }
     

      public String borrarInstructor (int id ){
        for (Estudiante est : estudiantes){
            if(est.getId()== id ){
                estudiantes.remove(est);
                return "usuario borrado correctamente ";
            }
        }
        return null;
    }

}


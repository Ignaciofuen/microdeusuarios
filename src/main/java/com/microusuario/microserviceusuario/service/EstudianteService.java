package com.microusuario.microserviceusuario.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.microusuario.microserviceusuario.models.Estudiante;

import com.microusuario.microserviceusuario.models.entity.EstudianteEntity;

import com.microusuario.microserviceusuario.repository.EstudianteRepository;



@Service
public class EstudianteService{

    @Autowired
    private EstudianteRepository estudianteRepository;


    public List<Estudiante> obtenerEstudiantes(){
        List<EstudianteEntity> entities = (List<EstudianteEntity>) estudianteRepository.findAll();

        List<Estudiante> dtos = new ArrayList<>();
        for (EstudianteEntity entity : entities) {
            dtos.add(new Estudiante(
                entity.getId(), 
                entity.getRun(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getCorreo(),
                entity.getContrasena(),
                entity.getCursoInscrito()
            ));
        }
        return dtos;
    }
    


    public String agregarEstudiante(Estudiante est){
        try{
            Boolean estado = estudianteRepository.existsByCorreo(est.getCorreo());
            if (!estado){
                EstudianteEntity nuevoEstudiante = new EstudianteEntity();
                nuevoEstudiante.setRun(est.getRun());
                nuevoEstudiante.setNombre(est.getNombre());
                nuevoEstudiante.setApellido(est.getApellido());
                nuevoEstudiante.setCorreo(est.getCorreo());
                nuevoEstudiante.setContrasena(est.getContrasena());
                nuevoEstudiante.setCursoInscrito(est.getCursoInscrito());
                estudianteRepository.save(nuevoEstudiante);
                return "Estudiante Agregado correctamente ";
            }
            return "El usuario ya existe";
        } catch (ObjectOptimisticLockingFailureException e) {
            return "Error de concurrencia: " + e.getMessage();
        } catch (Exception e) {
            return "Ha ocurrido un error: " + e.getMessage();
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
                    est.getContrasena(),
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
 

    public String inscribirCurso(String correo, List<String> nuevoCurso) {
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
     

    

    public String borrarEstudiante(int id) {    
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
        return "estudiante borrado correctamente";
        }
        return "estudiante no encontrado";
    }

    public boolean actualizarContraseña(String correo, String nuevaContraseña) {
        EstudianteEntity estudiante = estudianteRepository.findByCorreo(correo);
        if (estudiante == null) {
            return false;
        }
        estudiante.setContrasena(nuevaContraseña);;
            estudianteRepository.save(estudiante);
        return true;
    }



}

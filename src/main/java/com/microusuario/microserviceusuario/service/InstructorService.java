package com.microusuario.microserviceusuario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microusuario.microserviceusuario.models.Instructor;

import com.microusuario.microserviceusuario.models.entity.InstructorEntity;
import com.microusuario.microserviceusuario.repository.InstructorRepository;



@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;
    

    private final List<Instructor>instructores = new ArrayList<>();

    


     public List<Instructor> obtenerInstructores(){
        List<InstructorEntity> entities = (List<InstructorEntity>) instructorRepository.findAll();


        List<Instructor> dtos = new ArrayList<>();
        for (InstructorEntity entity : entities) {
             dtos.add(new Instructor(
                 entity.getId(), 
                 entity.getRun(),
                 entity.getNombre(),
                 entity.getApellido(),
                 entity.getCorreo(),
                 entity.getContrasena(),
                 entity.getCursoAsignado()
             ));
        }
        return dtos;


    }

    public String agregarInstructor(Instructor instructor) {
    try {
        if (instructorRepository.existsByCorreo(instructor.getCorreo())) {
            return "El usuario ya existe";
        } else {
            InstructorEntity nuevoInstructor = new InstructorEntity();
            nuevoInstructor.setRun(instructor.getRun());
            nuevoInstructor.setNombre(instructor.getNombre());
            nuevoInstructor.setApellido(instructor.getApellido());
            nuevoInstructor.setCorreo(instructor.getCorreo());
            nuevoInstructor.setContrasena(instructor.getContrasena());
            nuevoInstructor.setCursoAsignado(instructor.getCursoAsignado());
            instructorRepository.save(nuevoInstructor);
            return "Instructor agregado correctamente";
        }
    } catch (Exception e) {
        return "Error al agregar el instructor: " + e.getMessage();
    }
    }

    public Instructor traerInstructor(String correo) {
    if (!instructorRepository.existsByCorreo(correo)) {
        return null;
    }
    InstructorEntity entity = instructorRepository.findByCorreo(correo);
    Instructor instructor = new Instructor();
    instructor.setId(entity.getId());
    instructor.setRun(entity.getRun());
    instructor.setNombre(entity.getNombre());
    instructor.setApellido(entity.getApellido());
    instructor.setCorreo(entity.getCorreo());
    instructor.setContrasena(entity.getContrasena());
    instructor.setCursoAsignado(entity.getCursoAsignado());
    return instructor;
    }

   public String borrarInstructor(int id) {    
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
        return "instructor borrado correctamente";
        }
        return "instructor no encontrado";
    }


    
}

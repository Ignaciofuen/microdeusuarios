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
                 entity.getCursoAsingnado()
             ));
        }
        return dtos;


    }








    public String agregarInstructor(Instructor ins) {
        try {
            boolean estado = instructorRepository.existsByCorreo(ins.getCorreo());
            if(!estado){
                InstructorEntity nuevoInstructor = new InstructorEntity();
                nuevoInstructor.setRun(ins.getRun());   
                nuevoInstructor.setNombre(ins.getNombre());
                nuevoInstructor.setApellido(ins.getApellido());
                nuevoInstructor.setCorreo(ins.getCorreo());
                nuevoInstructor.setContrasena(ins.getContrasena());
                nuevoInstructor.setCursoAsingnado(ins.getCursoAsignado());
                instructorRepository.save(nuevoInstructor);
                return "Instructor agregado correctamente";
            }
            return "El usuario ya existe";           

        }
        catch(Exception e){
            return "Ha ocurrido un error";
        }
    }

 public Instructor traerInstructor(String correo){
        try{
            InstructorEntity inst = instructorRepository.findBycorreo(correo);
            if (inst!=null){
                Instructor instructorNuevo = new Instructor(
                    inst.getId(),
                    inst.getRun(),
                    inst.getNombre(),
                    inst.getApellido(),
                    inst.getCorreo(),
                    inst.getContrasena(),
                    inst.getCursoAsingnado()
                );
                return instructorNuevo;

            }
            return null;


        }
        catch (Exception e){
            return null;
        }
        

    }

   public String borrarInstructor(int id) {    
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
        return "instructor borrado correctamente";
        }
        return "instructor no encontrado";
    }


    
}

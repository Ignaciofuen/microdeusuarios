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

    public InstructorService(){
        instructores.add(new Instructor(232, "198837764", "carlos", "ramirez", "ca.ramirez@gamil.com", "carl123", "ingles"));
    }

    public List<Instructor>obtenerInstructores(){
        return instructores;
    }

    public String agregarInstructor(Instructor ins) {
        try {
            boolean estado = instructorRepository.existsByCorreo(ins.getCorreo());
            if(!estado){
                InstructorEntity nuevoInstructor = new InstructorEntity();
                nuevoInstructor.setId(ins.getId());
                nuevoInstructor.setRun(ins.getRun());   
                nuevoInstructor.setNombre(ins.getNombre());
                nuevoInstructor.setApellido(ins.getApellido());
                nuevoInstructor.setCorreo(ins.getCorreo());
                nuevoInstructor.setContraseña(ins.getContraseña());
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



    public String borrarInstructor (int id ){
        for (Instructor tor : instructores){
            if(tor.getId()== id ){
                instructores.remove(tor);
                return "usuario borrado correctamente ";
            }
        }
        return null;
    }



    
}

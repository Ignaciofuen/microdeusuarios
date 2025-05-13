package com.microusuario.microserviceusuario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microusuario.microserviceusuario.models.Administrador;
import com.microusuario.microserviceusuario.models.Estudiante;
import com.microusuario.microserviceusuario.models.Instructor;
import com.microusuario.microserviceusuario.repository.AdministradorRepository;




@Service
public class AdministradorService {

    @Autowired 
    private AdministradorRepository administradorRepository;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private InstructorService instructorService;

     private final List<Administrador> administradores = new ArrayList<>();

     public AdministradorService(){
        administradores.add(new Administrador(343, "20998123-k", "jorge", "fuenzalida", "fuen@gmail.com", "hola454", "adm123"));

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


   }











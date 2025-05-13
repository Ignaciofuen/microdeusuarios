package com.microusuario.microserviceusuario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microusuario.microserviceusuario.models.Instructor;
import com.microusuario.microserviceusuario.service.InstructorService;

@RestController
public class InstructorController {

    @Autowired
    private InstructorService instructorService; 

    @PostMapping("/agregarInstructor")
    public ResponseEntity<String> agregarInstructor(@RequestBody Instructor instructor) {
        return ResponseEntity.ok(instructorService.agregarInstructor(instructor));  
    }
}    
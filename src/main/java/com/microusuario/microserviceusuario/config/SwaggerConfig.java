package com.microusuario.microserviceusuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info; // Correct import for Swagger's Info class

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
            .title("Microservicio Usuarios")
            .version("1.0")
            .description("Este es el micro servicio de Usuarios "));
    }
}
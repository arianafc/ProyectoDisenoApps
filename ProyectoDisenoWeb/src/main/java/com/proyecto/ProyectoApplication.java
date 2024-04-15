package com.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProyectoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/**")
                    .addResourceLocations("classpath:/static/img/");
        }
    }

    @Controller
    public class IndexController {

        @GetMapping("/index")
        public String mostrarPaginaInicio() {
            return "index";
        }
    }
    
    
}

package com.company.empresaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpresaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmpresaSpringApplication.class, args);
        System.out.println("Hola mundo con Spring Boot");
    }


}

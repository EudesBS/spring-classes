package com.estudos.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//@Profile("development")
@Development
public class Config {

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("RODANDO CONFIGURAÇÂO DE DESENVOLVIMENTO");
        };
    }

    @Bean(name = "applicationName")
    public String applicationName(){
        return "Sistema de vendas";
    }
}

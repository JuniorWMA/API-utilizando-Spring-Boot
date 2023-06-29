package com.estudos.empresafuncionario.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracoes {
    
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}

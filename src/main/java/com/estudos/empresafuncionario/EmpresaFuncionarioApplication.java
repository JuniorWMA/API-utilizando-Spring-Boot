package com.estudos.empresafuncionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
	title = "API Empresa_Funcionario",
	version = "1.0",
	description = "Api simples com a finalidade de praticar meus estudos em Spring Boot",
	contact = @Contact(name = "Wellisson Junior", email = "junior.wmoreira@gmail.com", url = "https://github.com/JuniorWMA")
))
public class EmpresaFuncionarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpresaFuncionarioApplication.class, args);
	}

}

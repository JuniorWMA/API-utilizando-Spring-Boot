package com.estudos.empresafuncionario.dtos;

import com.estudos.empresafuncionario.model.Empresa;
import jakarta.validation.constraints.NotBlank;

public record EmpresaDto(@NotBlank String nome, @NotBlank String email) {
    public EmpresaDto(Empresa empresa){
        this(empresa.getNome(), empresa.getEmail());
    }
    
}

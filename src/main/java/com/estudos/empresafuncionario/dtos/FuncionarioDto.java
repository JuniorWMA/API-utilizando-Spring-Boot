package com.estudos.empresafuncionario.dtos;

import com.estudos.empresafuncionario.enums.Cargo;
import com.estudos.empresafuncionario.model.Funcionario;

public record FuncionarioDto(String nome, Cargo cargo) {
    public FuncionarioDto(Funcionario funcionario){
        this(funcionario.getNome(), funcionario.getCargo());
    }
}

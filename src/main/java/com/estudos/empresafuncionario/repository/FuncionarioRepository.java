package com.estudos.empresafuncionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudos.empresafuncionario.dtos.FuncionarioDto;
import com.estudos.empresafuncionario.model.Funcionario;
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    
    
}

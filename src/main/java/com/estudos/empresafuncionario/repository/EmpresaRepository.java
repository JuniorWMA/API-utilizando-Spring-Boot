package com.estudos.empresafuncionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.estudos.empresafuncionario.dtos.EmpresaDto;
import com.estudos.empresafuncionario.model.Empresa;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

    ResponseEntity<List<EmpresaDto>> findByNome(String nome);
    
}

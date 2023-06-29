package com.estudos.empresafuncionario.model;

import com.estudos.empresafuncionario.dtos.EmpresaDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMPRESAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;
    @NotBlank
    private String nome;
    @NotBlank
    private String cnpj;

    private String email;

    public Empresa(EmpresaDto dto) {
        this.nome = dto.nome();
        this.email = dto.email();
    }
}



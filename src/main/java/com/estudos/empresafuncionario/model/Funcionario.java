package com.estudos.empresafuncionario.model;

import java.math.BigDecimal;

import com.estudos.empresafuncionario.dtos.FuncionarioDto;
import com.estudos.empresafuncionario.enums.Cargo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FUNCIONARIOS")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;
    @NotBlank
    private String nome;
    @Positive
    @NotNull
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private Cargo cargo;


    public Funcionario(FuncionarioDto dto) {
        this.nome = dto.nome();
        this.cargo = dto.cargo();
    }
    

}

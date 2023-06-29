package com.estudos.empresafuncionario.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.estudos.empresafuncionario.model.Funcionario;
import com.estudos.empresafuncionario.service.FuncionarioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("/lista-completa")
    public ResponseEntity<?> listaFuncionarioCompleta() {
        return new ResponseEntity<>(service.listaDeFuncionarios(), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarFuncionarioPaginada(@PageableDefault(size = 10, sort = "nome") Pageable pagina) {
        var lista = service.listaPaginadaFuncionarioDto(pagina);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<?> cadastrarFuncionario(@RequestBody @Valid Funcionario funcionario, UriComponentsBuilder uBuilder) {
        var novoFuncionario = service.cadastrarFuncionario(funcionario);
        URI uri = uBuilder.path("/cadastrar/{idFuncionario}").buildAndExpand(uBuilder).toUri();
        return ResponseEntity.created(uri).body(novoFuncionario);
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario){
        return service.atualizarFuncionario(id, funcionario);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarFuncionarioNome(@RequestParam String nome){
        return service.buscarFuncionarioNome(nome);
    }

    @DeleteMapping("excluir/{id}")
    public ResponseEntity<?> excluirFuncionario(@PathVariable Long id) {
        return service.excluirFuncionario(id);
    }
        
}

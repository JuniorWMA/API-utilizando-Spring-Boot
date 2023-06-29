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
import com.estudos.empresafuncionario.model.Empresa;
import com.estudos.empresafuncionario.service.EmpresaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService service;
    

    @GetMapping("/lista-completa")
    public ResponseEntity<?> listaCompleta() {
        var lista = service.listaDeEmpresas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listaPaginada(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var lista = service.listaPaginadaEmpresaDto(paginacao);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<?> cadastrarEmpresa(@RequestBody @Valid Empresa empresa, UriComponentsBuilder uriComponentsBuilder) {
        var novaEmpresa = service.cadastrarEmpresa(empresa);
        URI uri = uriComponentsBuilder.path("cadastrar/{id}").buildAndExpand(uriComponentsBuilder).toUri();
        return ResponseEntity.created(uri).body(novaEmpresa);
    }

    @PutMapping("atualizar/{id}")
    @Transactional
    public ResponseEntity<?> atualizarCadastro(@PathVariable Long id, @RequestBody @Valid Empresa empresa) {
        return service.atualizarCadastro(id, empresa);
    }

    @DeleteMapping("/excuir/{id}")
    public ResponseEntity<?> excluirEmpresa(@PathVariable Long id) {
        return service.excluirEmpresa(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPeloNome(@RequestParam String nome) {
        return service.buscarPeloNome(nome);
    }
}

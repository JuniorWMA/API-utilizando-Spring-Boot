package com.estudos.empresafuncionario.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.estudos.empresafuncionario.dtos.FuncionarioDto;
import com.estudos.empresafuncionario.mensagem.Mensagem;
import com.estudos.empresafuncionario.model.Funcionario;
import com.estudos.empresafuncionario.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private Mensagem mensagem;


    //listar com todos os dados
    public List<Funcionario> listaDeFuncionarios() {
        return repository.findAll();
    }

    //lista paginada de FuncionarioDto
    public Page<FuncionarioDto> listaPaginadaFuncionarioDto(Pageable paginacao) {
        return repository.findAll(paginacao).map(p -> new FuncionarioDto(p));
    }

    //cadastrar funcionário
    public ResponseEntity<?> cadastrarFuncionario(Funcionario funcionario) {
        Funcionario novoFuncionario = repository.save(funcionario);
        FuncionarioDto funcionarioDto = new FuncionarioDto(novoFuncionario);
        return new ResponseEntity<>(funcionarioDto, HttpStatus.CREATED);
    }

    //atualizar funcionário
    public ResponseEntity<?> atualizarFuncionario(Long id, Funcionario funcionario){
        if (repository.existsById(id)) {
            var funcionarioId = repository.findById(id).get();
            funcionarioId.setNome(funcionario.getNome());
            funcionarioId.setSalario(funcionario.getSalario());
            funcionarioId.setCargo(funcionario.getCargo());
            return new ResponseEntity<>(repository.save(funcionarioId), HttpStatus.OK);

        }
        mensagem.setMensagem("Funcionário não encontrado.");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }

    //buscar funcionario pelo nome
    public ResponseEntity<?> buscarFuncionarioNome(String nome){
        var lista = repository.findAll();
        for (Funcionario funcionario : lista) {
            if (funcionario.getNome().equalsIgnoreCase(nome)) {
                FuncionarioDto dto = new FuncionarioDto(funcionario);
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
            
        }
        mensagem.setMensagem("Funcionário não encontrado");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }

    //excluir funcionário
    public ResponseEntity<?> excluirFuncionario(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            mensagem.setMensagem("Funcionário excluido com sucesso.");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
        mensagem.setMensagem("Funcionário não encontrado.");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
}

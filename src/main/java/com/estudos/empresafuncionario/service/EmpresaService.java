package com.estudos.empresafuncionario.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.estudos.empresafuncionario.dtos.EmpresaDto;
import com.estudos.empresafuncionario.mensagem.Mensagem;
import com.estudos.empresafuncionario.model.Empresa;
import com.estudos.empresafuncionario.repository.EmpresaRepository;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    @Autowired
    private Mensagem mensagem;

    //lista com todos os dados
    public List<Empresa> listaDeEmpresas() {
        return repository.findAll();
    }

    //lista paginada de EmpresaDto
    public Page<EmpresaDto> listaPaginadaEmpresaDto(Pageable paginacao) {
        Page<Empresa> lista = repository.findAll(paginacao);
        Page<EmpresaDto> listaDto = lista.map(p -> new EmpresaDto(p));
        return listaDto;
    }

    //cadastrar empresa
    public ResponseEntity<?> cadastrarEmpresa(Empresa empresa) {
        repository.save(empresa);
        EmpresaDto empDto = new EmpresaDto(empresa);
        return new ResponseEntity<>(empDto, HttpStatus.CREATED);
        
    }

    //atualizar cadastro
    public ResponseEntity<?> atualizarCadastro(Long id, Empresa empresa) {
        if (repository.existsById(id)) {
            //mensagem.setMensagem("Cadastro atualizado com sucesso");
            Empresa empresa2 = repository.findById(id).get();
            empresa2.setNome(empresa.getNome());
            empresa2.setCnpj(empresa.getCnpj());
            empresa2.setEmail(empresa.getEmail());
            repository.save(empresa2);
            var empresaDto = new EmpresaDto(empresa2);
            return ResponseEntity.ok(empresaDto);
        } else{
            mensagem.setMensagem("Cadastro da empresa não localizado");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        
    }

    //excluir empresa
    public ResponseEntity<?> excluirEmpresa(Long id) {
        if (!repository.existsById(id)) {
            mensagem.setMensagem("Empresa não localizada");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else{
            repository.deleteById(id);
            mensagem.setMensagem("Empresa excluida com sucesso.");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }

    }

    //buscar pelo nome
    public ResponseEntity<?> buscarPeloNome(String nome) {
        List<Empresa> lista = repository.findAll();
        for (Empresa empresa : lista) {
            if (empresa.getNome().equalsIgnoreCase(nome)) {
                EmpresaDto dto = new EmpresaDto(empresa);
                
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
        }
        mensagem.setMensagem("Empresa não encontrada");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        
    }
    
}

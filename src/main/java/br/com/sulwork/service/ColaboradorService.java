package br.com.sulwork.service;

import br.com.sulwork.domain.Colaborador;
import br.com.sulwork.exception.InvalidDataException;
import br.com.sulwork.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColaboradorService {

    private final ColaboradorRepository repository;

    public void saveColaborador(Colaborador colaborador) {
        if(colaborador.getCpf().length() != 11){
            throw new InvalidDataException("CPF deve ter 11 caracteres");
        }
        try{
            repository.salvarColaborador(colaborador.getNome(),colaborador.getCpf());
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Já existe um colaborador com esses dados.");
        }
    }

    public void atualizarColaborador(Long id, Colaborador colaborador){
        Colaborador colaboradorEncontrado = repository.findWithId(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado."));

        repository.atualizarColaborador(colaboradorEncontrado.getId(),
                colaborador.getNome(),
                colaborador.getCpf());
    }

    public List<Colaborador> consultar(){
        return repository.findAll();
    }

    public Colaborador consultarPorNome(String nome){
        return  repository.findWithName(nome);
    }

}

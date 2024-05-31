package br.com.sulwork.service;

import br.com.sulwork.domain.Item;
import br.com.sulwork.domain.ItemSelecionado;
import br.com.sulwork.repository.ItemRepository;
import br.com.sulwork.repository.ItemSelecionadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;
    private final ItemSelecionadoRepository itemSelecionadoRepository;


    public void salvar(Item item) {
        repository.salvarItem(item.getNome());
    }

    public Item consultarPorNome(String nome){
        return repository.findWithName(nome);
    }

    public List<Item> consultarItensDisponiveis(String data){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(data,formatter);

        List<ItemSelecionado> itensSelecionados = itemSelecionadoRepository.findByData(date);
        List<Item> todosItens = repository.findAll();
        List<Long> idsSelecionados = itensSelecionados.stream().map(is -> is.getItem().getId()).toList();
        return todosItens.stream().filter(item -> !idsSelecionados.contains(item.getId())).toList();
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}

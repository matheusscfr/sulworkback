package br.com.sulwork.controller;

import br.com.sulwork.domain.Item;
import br.com.sulwork.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/itens")
@CrossOrigin("*")
public class ItemController {

    private final ItemService service;

    @PostMapping()
    public ResponseEntity<String> salvar(@RequestBody @Valid Item item){
        service.salvar(item);
        return ResponseEntity.ok("Item do café cadastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItens(@RequestParam String data) {
       return ResponseEntity.ok(service.consultarItensDisponiveis(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletarItem(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.ok("Item do café deletado com sucesso.");
    }

}

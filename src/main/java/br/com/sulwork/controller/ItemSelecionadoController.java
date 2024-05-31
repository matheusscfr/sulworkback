package br.com.sulwork.controller;

import br.com.sulwork.domain.ItemSelecionado;
import br.com.sulwork.request.ItemRequest;
import br.com.sulwork.response.ColaboradorItemsDTO;
import br.com.sulwork.service.ItemSelecionadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/itens-selecionados")
@CrossOrigin("*")
public class ItemSelecionadoController {

    private final ItemSelecionadoService itemSelecionadoService;

    @PostMapping
    public ResponseEntity<ItemSelecionado> criarItemSelecionado(@Valid @RequestBody ItemRequest request) {
        return ResponseEntity.ok(itemSelecionadoService.salvar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemSelecionado> atualizarItemSelecionado(@PathVariable Long id,
            @Valid @RequestBody ItemRequest request) {
        return ResponseEntity.ok(itemSelecionadoService.atualizar(id,request));
    }

    @GetMapping
    public ResponseEntity<List<ColaboradorItemsDTO>> listarSelecionados(){
        return ResponseEntity.ok(itemSelecionadoService.listar());
    }

}

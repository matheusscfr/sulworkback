package br.com.sulwork.controller;

import br.com.sulwork.domain.Colaborador;
import br.com.sulwork.service.ColaboradorService;
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
@RequestMapping("/colaboradores")
@CrossOrigin("*")
public class ColaboradorController {

    private final ColaboradorService service;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody @Valid Colaborador colaborador){
        service.saveColaborador(colaborador);
        return ResponseEntity.ok("Colaborador cadastrado com Sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id,
            @RequestBody @Valid Colaborador colaborador){
        service.atualizarColaborador(id,colaborador);
        return ResponseEntity.ok("cadastro atualizado com Sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> consultar(){
        return ResponseEntity.ok(service.consultar());
    }

}

package br.com.sulwork.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    @NotBlank(message = "nome do colaborador deve ser informado")
    private String nome;

    @NotBlank(message = "item que do café deve ser informado")
    private String item;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCafe;
}

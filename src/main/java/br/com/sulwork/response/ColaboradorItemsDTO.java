package br.com.sulwork.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorItemsDTO {
    private Long id;
    private String nome;
    private String cpf;
    private List<ItemDTO> items;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemDTO {
        private Long id;
        private String nome;
    }
}

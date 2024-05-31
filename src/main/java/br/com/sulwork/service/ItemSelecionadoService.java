package br.com.sulwork.service;

import br.com.sulwork.domain.Colaborador;
import br.com.sulwork.domain.Item;
import br.com.sulwork.domain.ItemSelecionado;
import br.com.sulwork.repository.ItemSelecionadoRepository;
import br.com.sulwork.request.ItemRequest;
import br.com.sulwork.response.ColaboradorItemsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ItemSelecionadoService {

    private final ItemSelecionadoRepository repository;
    private final ItemService itemService;
    private final ColaboradorService colaboradorService;

    public ItemSelecionado salvar(ItemRequest request){
        Colaborador colaborador = colaboradorService.consultarPorNome(request.getNome());
        Item item = itemService.consultarPorNome(request.getItem());

        List<ItemSelecionado> selecionados = repository.findByData(request.getDataCafe());
        boolean itemJaSelecionado = selecionados.stream()
                .anyMatch(is -> is.getItem().getId().equals(item.getId()));

        if (itemJaSelecionado) {
            throw new IllegalArgumentException("Item já selecionado para esta data");
        }

        ItemSelecionado itemSelecionado = ItemSelecionado.builder()
                .colaborador(colaborador)
                .item(item)
                .data(request.getDataCafe())
                .build();
        return repository.save(itemSelecionado);
    }

    public ItemSelecionado atualizar(Long id, ItemRequest request){
        ItemSelecionado itemSelecionado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("registro não existe."));
        Item item = itemService.consultarPorNome(request.getItem());
        Colaborador colaborador = colaboradorService.consultarPorNome(request.getNome());
        itemSelecionado.setItem(item);
        itemSelecionado.setColaborador(colaborador);
        itemSelecionado.setData(request.getDataCafe());
        return repository.save(itemSelecionado);
    }

    public List<ColaboradorItemsDTO> listar() {
        List<ItemSelecionado> selecionados = repository.findAll();

        Map<Long, Map<LocalDate, ColaboradorItemsDTO>> colaboradorMap = new HashMap<>();

        for (ItemSelecionado selecionado : selecionados) {
            Long colaboradorId = selecionado.getColaborador().getId();
            LocalDate data = selecionado.getData();

            // Mapa para agrupar por data
            Map<LocalDate, ColaboradorItemsDTO> dataMap = colaboradorMap.get(colaboradorId);
            if (dataMap == null) {
                dataMap = new HashMap<>();
                colaboradorMap.put(colaboradorId, dataMap);
            }

            // DTO agrupado por data
            ColaboradorItemsDTO dto = dataMap.get(data);
            if (dto == null) {
                dto = new ColaboradorItemsDTO();
                dto.setId(colaboradorId);
                dto.setNome(selecionado.getColaborador().getNome());
                dto.setCpf(selecionado.getColaborador().getCpf());
                dto.setData(data);
                dto.setItems(new ArrayList<>());
                dataMap.put(data, dto);
            }

            ColaboradorItemsDTO.ItemDTO itemDTO = new ColaboradorItemsDTO.ItemDTO();
            itemDTO.setId(selecionado.getItem().getId());
            itemDTO.setNome(selecionado.getItem().getNome());

            dto.getItems().add(itemDTO);
        }

        // Converter o mapa em uma lista de ColaboradorItemsDTO
        List<ColaboradorItemsDTO> result = new ArrayList<>();
        for (Map<LocalDate, ColaboradorItemsDTO> dataMap : colaboradorMap.values()) {
            result.addAll(dataMap.values());
        }

        return result;
    }
}

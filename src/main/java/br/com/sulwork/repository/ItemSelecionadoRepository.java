package br.com.sulwork.repository;

import br.com.sulwork.domain.ItemSelecionado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemSelecionadoRepository extends JpaRepository<ItemSelecionado,Long> {

    @Query(value = "SELECT * FROM item_selecionado WHERE data = :data", nativeQuery = true)
    List<ItemSelecionado> findByData(@Param("data") LocalDate data);

    @Query(value = "SELECT * FROM item_selecionado WHERE id = :id", nativeQuery = true)
    Optional<ItemSelecionado> findById(@Param("id") Long id);

}

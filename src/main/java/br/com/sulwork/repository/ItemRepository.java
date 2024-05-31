package br.com.sulwork.repository;

import br.com.sulwork.domain.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO item (nome) VALUES (:nome)", nativeQuery = true)
    void salvarItem(String nome);

    @Query(value = "SELECT * FROM item WHERE nome = :nome", nativeQuery = true)
    Item findWithName(@Param("nome") String nome);
}

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

    @Query(value = "SELECT * FROM item WHERE id = :id", nativeQuery = true)
    Item findWithId(@Param("id") Long id);

    @Query(value = "SELECT * FROM item WHERE nome = :nome", nativeQuery = true)
    Item findWithName(@Param("nome") String nome);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM item WHERE id = :id", nativeQuery = true)
    void deletarItem(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE item i SET i.nome = :nome WHERE i.id = :id", nativeQuery = true)
    void updateItem(@Param("id") Long id, @Param("nome") String nome);
}

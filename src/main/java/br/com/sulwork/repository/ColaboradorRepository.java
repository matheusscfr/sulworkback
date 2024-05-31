package br.com.sulwork.repository;

import br.com.sulwork.domain.Colaborador;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador,Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO colaborador (nome, cpf) VALUES (:nome, :cpf)", nativeQuery = true)
    void salvarColaborador(String nome, String cpf);

    @Query(value = "SELECT * FROM colaborador WHERE id = :id", nativeQuery = true)
    Optional<Colaborador> findWithId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE colaborador c SET c.nome = :nome, c.cpf = :cpf WHERE c.id = :id", nativeQuery = true)
    void atualizarColaborador(@Param("id") Long id, @Param("nome") String nome, @Param("cpf") String cpf);

    @Query(value = "SELECT * FROM colaborador", nativeQuery = true)
    List<Colaborador> findAll();

    @Query(value = "SELECT * FROM colaborador WHERE nome = :nome", nativeQuery = true)
    Colaborador findWithName(@Param("nome") String nome);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM colaborador WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}

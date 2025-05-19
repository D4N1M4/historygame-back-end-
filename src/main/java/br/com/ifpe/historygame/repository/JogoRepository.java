package br.com.ifpe.historygame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ifpe.historygame.entity.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {

    // Corrigindo o método para buscar por gênero com nome igual, ignorando maiúsculas e minúsculas
    @Query("SELECT j FROM Jogo j JOIN j.generos g WHERE LOWER(g.nome) = LOWER(:nomeGenero)")
    List<Jogo> findByGenerosNomeIgnoreCase(@Param("nomeGenero") String nomeGenero);

    List<Jogo> findByNomeContainingIgnoreCase(String nome);

}

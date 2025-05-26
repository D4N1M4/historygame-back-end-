package br.com.ifpe.historygame.repository;

import br.com.ifpe.historygame.entity.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

    @Query("SELECT COUNT(a) FROM Acesso a WHERE a.jogo.id = :jogoId")
    Long contarAcessosPorJogo(@Param("jogoId") Long jogoId);
}

package br.com.ifpe.historygame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifpe.historygame.entity.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioUid(String uid); // boa prática incluir aqui, não no JogoRepository
    Optional<Favorito> findByUsuarioUidAndJogoId(String uid, Long jogoId);
    int countByJogoId(Long jogoId);

} 
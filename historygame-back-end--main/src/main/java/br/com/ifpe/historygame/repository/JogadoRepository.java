package br.com.ifpe.historygame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifpe.historygame.entity.Jogado;

public interface JogadoRepository extends JpaRepository<Jogado, Long> {
    List<Jogado> findByUsuarioUid(String uid);
    Optional<Jogado> findByUsuarioUidAndJogoId(String uid, Long jogoId);
    int countByJogoId(Long jogoId);
}

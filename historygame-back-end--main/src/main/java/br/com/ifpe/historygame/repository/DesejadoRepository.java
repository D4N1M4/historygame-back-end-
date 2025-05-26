package br.com.ifpe.historygame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifpe.historygame.entity.Desejado;

public interface DesejadoRepository extends JpaRepository<Desejado, Long> {
    List<Desejado> findByUsuarioUid(String uid);
    Optional<Desejado> findByUsuarioUidAndJogoId(String uid, Long jogoId);
    int countByJogoId(Long jogoId);
}
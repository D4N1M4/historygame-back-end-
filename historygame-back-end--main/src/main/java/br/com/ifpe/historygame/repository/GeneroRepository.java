package br.com.ifpe.historygame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ifpe.historygame.entity.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    Optional<Genero> findByNomeIgnoreCase(String nome);
}

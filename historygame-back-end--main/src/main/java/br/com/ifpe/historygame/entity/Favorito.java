package br.com.ifpe.historygame.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favorito", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "jogo_id"}))
public class Favorito {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Jogo jogo;

    @ManyToOne
    private Usuario usuario;

    public Favorito() {}
    public Favorito(Usuario usuario, Jogo jogo) {
        this.usuario = usuario;
        this.jogo = jogo;
    }
}
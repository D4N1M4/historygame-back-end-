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
@Table(name = "desejado", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "jogo_id"}))
public class Desejado {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Jogo jogo;

    public Desejado() {}
    public Desejado(Usuario usuario, Jogo jogo) {
        this.usuario = usuario;
        this.jogo = jogo;
    }
}
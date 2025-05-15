package br.com.ifpe.historygame.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="jogo")
@Getter
@Setter
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    private Long id;

    @Column(name="nome", nullable=false)
    private String nome;

    @Column(name="resumo", nullable=false)
    private String resumo;

    @Column(name="capa", nullable=false)
    private String capa;

    @Column(name="modoJogo", nullable=false)
    private String modoJogo;

    @Column(name="dataLancamento", nullable=false)
    private LocalDate dataLancamento;

    @ManyToMany
    @JoinTable(
        name = "jogo_genero",
        joinColumns = @JoinColumn(name = "jogo_id"),
        inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private List<Genero> generos;

}

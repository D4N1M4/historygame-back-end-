package br.com.ifpe.historygame.dto;

import br.com.ifpe.historygame.entity.Jogo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoritoJogoDTO {
    private Long id;
    private String nome;
    private String resumo;
    private String capa;
    private String modoJogo;
    private String dataLancamento;
    private Long totalFavoritos; // ✅ Adicionado

    public FavoritoJogoDTO(Jogo jogo, Long totalFavoritos) {
        this.id = jogo.getId();
        this.nome = jogo.getNome();
        this.resumo = jogo.getResumo();
        this.capa = jogo.getCapa();
        this.modoJogo = jogo.getModoJogo();
        this.dataLancamento = jogo.getDataLancamento().toString();
        this.totalFavoritos = totalFavoritos;
    }
}

package br.com.ifpe.historygame.modelo.jogo;

import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.historygame.util.entity.EntidadeAuditavel;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jogo")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jogo extends EntidadeAuditavel{
    private Long idApi;

    private String nome;

    @Column(length = 5000)
    private String resumo;

    private String slug;

    private String url;

    private String dataLancamento; // como string para facilitar exibição

    @ElementCollection
    @CollectionTable(name = "jogo_generos")
    private List<String> generos;

    @ElementCollection
    @CollectionTable(name = "jogo_plataformas")
    private List<String> plataformas;

    @ElementCollection
    @CollectionTable(name = "jogo_modos_jogo")
    private List<String> modosJogo;

    @ElementCollection
    @CollectionTable(name = "jogo_temas")
    private List<String> temas;

    @ElementCollection
    @CollectionTable(name = "jogo_screenshots")
    private List<String> screenshots;

    @ElementCollection
    @CollectionTable(name = "jogo_artworks")
    private List<String> artworks;
}


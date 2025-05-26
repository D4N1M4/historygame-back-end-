package br.com.ifpe.historygame.dto;
import java.time.LocalDate;
import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JogoDTO {
    private Long id;
    private String nome;
    private String resumo;
    private String capa;
    private String modoJogo;
    private LocalDate dataLancamento;
    private List<GeneroDTO> generos;

    private Integer favoritados;
    private Integer jogados;
    private Integer desejados;
}



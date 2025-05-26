package br.com.ifpe.historygame.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class JogoDTO {
    private Long id;
    private String nome;
    private String resumo;
    private String capa;
    private String modoJogo;
    private LocalDate dataLancamento;
    private List<GeneroDTO> generos;
}






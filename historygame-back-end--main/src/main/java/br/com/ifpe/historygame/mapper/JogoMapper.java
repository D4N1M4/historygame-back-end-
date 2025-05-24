package br.com.ifpe.historygame.mapper;

import br.com.ifpe.historygame.dto.*;
import br.com.ifpe.historygame.entity.*;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JogoMapper {


    public JogoDTO toDTO(Jogo jogo) {
        JogoDTO dto = new JogoDTO();
        dto.setId(jogo.getId());
        dto.setNome(jogo.getNome());
        dto.setResumo(jogo.getResumo());
        dto.setCapa(jogo.getCapa());
        dto.setModoJogo(jogo.getModoJogo());
        dto.setDataLancamento(jogo.getDataLancamento());

        dto.setGeneros(
            jogo.getGeneros().stream()
                .map(this::toGeneroDTO)
                .collect(Collectors.toList())
        );

        return dto;
    }

public Jogo toEntity(JogoDTO dto) {
    Jogo jogo = new Jogo();
    jogo.setId(dto.getId());
    jogo.setNome(dto.getNome());
    jogo.setResumo(dto.getResumo());
    jogo.setCapa(dto.getCapa());
    jogo.setModoJogo(dto.getModoJogo());
    jogo.setDataLancamento(dto.getDataLancamento());

    jogo.setGeneros(dto.getGeneros().stream()
         .map(this::toGenero)
         .collect(Collectors.toList()));
    return jogo;
}

    public GeneroDTO toGeneroDTO(Genero genero) {
        GeneroDTO dto = new GeneroDTO();
        dto.setId(genero.getId());
        dto.setNome(genero.getNome());
        return dto;
    }

    public Genero toGenero(GeneroDTO dto) {
        Genero genero = new Genero();
        genero.setId(dto.getId());
        genero.setNome(dto.getNome());
        return genero;
    }

}
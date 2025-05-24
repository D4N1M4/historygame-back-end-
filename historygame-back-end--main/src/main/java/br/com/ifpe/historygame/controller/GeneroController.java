package br.com.ifpe.historygame.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.historygame.dto.GeneroDTO;
import br.com.ifpe.historygame.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroRepository generoRepository;

    @GetMapping
    public List<GeneroDTO> listarTodos() {
        return generoRepository.findAll()
            .stream()
            .map(genero -> {
                GeneroDTO dto = new GeneroDTO();
                dto.setId(genero.getId());
                dto.setNome(genero.getNome());
                return dto;
            })
            .toList();
    }
}

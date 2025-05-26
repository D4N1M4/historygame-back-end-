package br.com.ifpe.historygame.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.historygame.dto.FavoritoJogoDTO;
import br.com.ifpe.historygame.service.FavoritoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jogos/favoritos")
@RequiredArgsConstructor
public class FavoritoJogoController {

    private final FavoritoService favoritoService;

    @GetMapping("/mais")
    public ResponseEntity<List<FavoritoJogoDTO>> buscarMaisFavoritados() {
        List<FavoritoJogoDTO> jogos = favoritoService.buscarMaisFavoritados();
        return ResponseEntity.ok(jogos);
    }
}


package br.com.ifpe.historygame.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.historygame.dto.FavoritoJogoDTO;
import br.com.ifpe.historygame.service.FavoritoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios/{uid}/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService favoritoService;

    @PostMapping("/{jogoId}")
    public ResponseEntity<Void> adicionarFavorito(@PathVariable String uid, @PathVariable Long jogoId) {
        favoritoService.adicionarFavorito(uid, jogoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{jogoId}")
    public ResponseEntity<Void> removerFavorito(@PathVariable String uid, @PathVariable Long jogoId) {
        favoritoService.removerFavorito(uid, jogoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FavoritoJogoDTO>> listarFavoritos(@PathVariable String uid) {
        List<FavoritoJogoDTO> favoritos = favoritoService.listarFavoritos(uid);
        return ResponseEntity.ok(favoritos);
    }

}
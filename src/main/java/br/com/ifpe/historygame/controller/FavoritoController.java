package br.com.ifpe.historygame.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.historygame.entity.Jogo;
import br.com.ifpe.historygame.service.FavoritoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService favoritoService;

    @PostMapping("/{jogoId}")
    public ResponseEntity<Void> adicionarFavorito(@PathVariable Long usuarioId, @PathVariable Long jogoId) {
        favoritoService.adicionarFavorito(usuarioId, jogoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{jogoId}")
    public ResponseEntity<Void> removerFavorito(@PathVariable Long usuarioId, @PathVariable Long jogoId) {
        favoritoService.removerFavorito(usuarioId, jogoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<Jogo>> listarFavoritos(@PathVariable Long usuarioId) {
        Set<Jogo> favoritos = favoritoService.listarFavoritos(usuarioId);
        return ResponseEntity.ok(favoritos);
    }
}

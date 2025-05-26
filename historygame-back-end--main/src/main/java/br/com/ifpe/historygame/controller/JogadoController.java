package br.com.ifpe.historygame.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.historygame.dto.JogadoJogoDTO;
import br.com.ifpe.historygame.service.JogadoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios/{uid}/jogados")
@RequiredArgsConstructor
public class JogadoController {

    private final JogadoService jogadoService;

    @PostMapping("/{jogoId}")
    public ResponseEntity<Void> adicionarJogado(@PathVariable String uid, @PathVariable Long jogoId) {
        jogadoService.adicionarJogado(uid, jogoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{jogoId}")
    public ResponseEntity<Void> removerJogado(@PathVariable String uid, @PathVariable Long jogoId) {
        jogadoService.removerJogado(uid, jogoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<JogadoJogoDTO>> listarJogados(@PathVariable String uid) {
        List<JogadoJogoDTO> jogados = jogadoService.listarJogados(uid);
        return ResponseEntity.ok(jogados);
    }
}


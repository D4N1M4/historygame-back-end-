package br.com.ifpe.historygame.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.historygame.modelo.jogo.JogoService;

@RestController
@RequestMapping("/api/Jogo")
@CrossOrigin

public class JogoController {
    @Autowired
    private JogoService jogoService;

    @PostMapping("/importar")
    public ResponseEntity<String> importarJogos() {
        jogoService.importarJogosIGDB();
        return ResponseEntity.ok("Jogos importados com sucesso.");
    }
}

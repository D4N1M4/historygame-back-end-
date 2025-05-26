package br.com.ifpe.historygame.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ifpe.historygame.dto.JogoDTO;
import br.com.ifpe.historygame.entity.Acesso;
import br.com.ifpe.historygame.entity.Jogo;
import br.com.ifpe.historygame.repository.AcessoRepository;
import br.com.ifpe.historygame.repository.JogoRepository;
import br.com.ifpe.historygame.service.JogoService;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {


    @Autowired
    private JogoService service;

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private AcessoRepository acessoRepository;

    // 🔥 Endpoint para listar todos os jogos
    @GetMapping
    public List<JogoDTO> listar() {
        return service.listar();
    }

    // 🔥 Endpoint para buscar jogo por ID (também registra o acesso)
    @GetMapping("/{id}")
    public ResponseEntity<JogoDTO> buscar(@PathVariable Long id) {
        Optional<Jogo> jogo = jogoRepository.findById(id);
        if (jogo.isPresent()) {
            // Registra o acesso
            Acesso acesso = new Acesso();
            acesso.setJogo(jogo.get());
            acesso.setDataAcesso(LocalDateTime.now());
            acessoRepository.save(acesso);

            return ResponseEntity.ok(service.buscarPorId(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔥 Endpoint para contar acessos de um jogo
    @GetMapping("/{id}/acessos")
    public ResponseEntity<Long> contarAcessos(@PathVariable Long id) {
        Long totalAcessos = acessoRepository.contarAcessosPorJogo(id);
        return ResponseEntity.ok(totalAcessos);
    }

    // 🔥 Endpoint para buscar jogos por nome
    @GetMapping("/buscar")
    public ResponseEntity<List<JogoDTO>> buscarPorNome(@RequestParam String busca) {
        return ResponseEntity.ok(service.buscarPorNome(busca));
    }

    // 🔥 Endpoint para criar um novo jogo
    @PostMapping
    public ResponseEntity<JogoDTO> criar(@RequestBody JogoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    // 🔥 Endpoint para atualizar um jogo
    @PutMapping("/{id}")
    public ResponseEntity<JogoDTO> atualizar(@PathVariable Long id, @RequestBody JogoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    // 🔥 Endpoint para deletar um jogo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔥 Endpoint para listar jogos por gênero
    @GetMapping("/genero/{nomeGenero}")
    public ResponseEntity<List<JogoDTO>> listarPorGenero(@PathVariable String nomeGenero) {
        return ResponseEntity.ok(service.listarPorGenero(nomeGenero));
    }

    @GetMapping("/mais-acessados")
    public ResponseEntity<List<JogoDTO>> listarMaisAcessados() {
        return ResponseEntity.ok(service.listarMaisAcessados());
    }




}

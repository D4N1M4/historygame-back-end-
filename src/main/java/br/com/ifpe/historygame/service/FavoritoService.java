package br.com.ifpe.historygame.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.ifpe.historygame.entity.Jogo;
import br.com.ifpe.historygame.entity.Usuario;
import br.com.ifpe.historygame.repository.JogoRepository;
import br.com.ifpe.historygame.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final UsuarioRepository usuarioRepository;
    private final JogoRepository jogoRepository;

    public void adicionarFavorito(Long usuarioId, Long jogoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Jogo jogo = jogoRepository.findById(jogoId)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        usuario.getFavoritos().add(jogo);
        usuarioRepository.save(usuario);
    }

    public void removerFavorito(Long usuarioId, Long jogoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Jogo jogo = jogoRepository.findById(jogoId)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        usuario.getFavoritos().remove(jogo);
        usuarioRepository.save(usuario);
    }

    public Set<Jogo> listarFavoritos(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuario.getFavoritos();
    }
}




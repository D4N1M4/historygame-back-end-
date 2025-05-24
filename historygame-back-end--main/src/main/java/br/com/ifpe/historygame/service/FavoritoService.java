package br.com.ifpe.historygame.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifpe.historygame.entity.Favorito;
import br.com.ifpe.historygame.entity.Jogo;
import br.com.ifpe.historygame.entity.Usuario;
import br.com.ifpe.historygame.repository.FavoritoRepository;
import br.com.ifpe.historygame.repository.JogoRepository;
import br.com.ifpe.historygame.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JogoRepository jogoRepository;

    public void adicionarFavorito(String uid, Long jogoId) {
        Usuario usuario = usuarioRepository.findByUid(uid)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Jogo jogo = jogoRepository.findById(jogoId)
            .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        if (favoritoRepository.findByUsuarioUidAndJogoId(uid, jogoId).isEmpty()) {
            favoritoRepository.save(new Favorito(usuario, jogo));
        }
    }

    public void removerFavorito(String uid, Long jogoId) {
        favoritoRepository.findByUsuarioUidAndJogoId(uid, jogoId)
            .ifPresent(favoritoRepository::delete);
    }

    public List<Jogo> listarFavoritos(String uid) {
        return favoritoRepository.findByUsuarioUid(uid).stream()
            .map(Favorito::getJogo)
            .toList();
    }
}

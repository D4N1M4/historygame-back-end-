package br.com.ifpe.historygame.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ifpe.historygame.dto.FavoritoJogoDTO;
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

    public List<FavoritoJogoDTO> listarFavoritos(String uid) {
        return favoritoRepository.findByUsuarioUid(uid).stream()
            .map(fav -> new FavoritoJogoDTO(fav.getJogo(), null))
            .toList();
    }

    public List<FavoritoJogoDTO> buscarMaisFavoritados() {
    List<Favorito> todosFavoritos = favoritoRepository.findAll();

    Map<Jogo, Long> contagemPorJogo = todosFavoritos.stream()
        .collect(Collectors.groupingBy(Favorito::getJogo, Collectors.counting()));

    return contagemPorJogo.entrySet().stream()
        .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) // ordem decrescente
        .map(entry -> {
            Jogo jogo = entry.getKey();
            Long total = entry.getValue();
            return new FavoritoJogoDTO(jogo, total); // precisa de construtor com totalFavoritos
        })
        .toList();
}
}

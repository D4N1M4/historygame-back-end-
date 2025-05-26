package br.com.ifpe.historygame.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifpe.historygame.dto.DesejadoJogoDTO;
import br.com.ifpe.historygame.entity.Desejado;
import br.com.ifpe.historygame.entity.Jogo;
import br.com.ifpe.historygame.entity.Usuario;
import br.com.ifpe.historygame.repository.DesejadoRepository;
import br.com.ifpe.historygame.repository.JogoRepository;
import br.com.ifpe.historygame.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DesejadoService {

    private final DesejadoRepository desejadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JogoRepository jogoRepository;

    public void adicionarDesejado(String uid, Long jogoId) {
        Usuario usuario = usuarioRepository.findByUid(uid)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Jogo jogo = jogoRepository.findById(jogoId)
            .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        if (desejadoRepository.findByUsuarioUidAndJogoId(uid, jogoId).isEmpty()) {
            desejadoRepository.save(new Desejado(usuario, jogo));
        }
    }

    public void removerDesejado(String uid, Long jogoId) {
        desejadoRepository.findByUsuarioUidAndJogoId(uid, jogoId)
            .ifPresent(desejadoRepository::delete);
    }

    public List<DesejadoJogoDTO> listarDesejados(String uid) {
        return desejadoRepository.findByUsuarioUid(uid).stream()
            .map(fav -> new DesejadoJogoDTO(fav.getJogo()))
            .toList();
    }
}

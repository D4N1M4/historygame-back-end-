package br.com.ifpe.historygame.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.historygame.dto.JogoDTO;
import br.com.ifpe.historygame.entity.Genero;
import br.com.ifpe.historygame.entity.Jogo;
import br.com.ifpe.historygame.mapper.JogoMapper;
import br.com.ifpe.historygame.repository.GeneroRepository;
import br.com.ifpe.historygame.repository.JogoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JogoService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private JogoRepository repository;

    @Autowired
    private JogoMapper mapper;

    // Listar todos os jogos
    public List<JogoDTO> listar() {
        return repository.findAll()
                         .stream()
                         .map(mapper::toDTO)
                         .toList();
    }

    // Buscar um jogo por ID
    public JogoDTO buscarPorId(Long id) {
        return repository.findById(id)
                         .map(mapper::toDTO)
                         .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
    }

    public List<JogoDTO> buscarPorNome(String termoBusca) {
        List<Jogo> jogos = repository.findByNomeContainingIgnoreCase(termoBusca);
        return jogos.stream().map(mapper::toDTO).toList();
    }

public JogoDTO salvar(JogoDTO dto) {
    // Converte o DTO para entidade
    Jogo jogo = mapper.toEntity(dto);

    // Trata os gêneros fornecidos pelo usuário
    if (dto.getGeneros() != null && !dto.getGeneros().isEmpty()) {
        List<Genero> generos = dto.getGeneros().stream()
            .map(generoDto -> {
                // Verifica se o gênero já existe no banco (ignora maiúsculas/minúsculas)
                return generoRepository.findByNomeIgnoreCase(generoDto.getNome())
                    .orElseGet(() -> {
                        // Se não existir, cria e salva um novo
                        Genero novoGenero = new Genero();
                        novoGenero.setNome(generoDto.getNome());
                        return generoRepository.save(novoGenero);
                    });
            })
            .collect(Collectors.toList());

        jogo.setGeneros(generos);
    }

    // Salva o jogo com os gêneros associados
    Jogo salvo = repository.save(jogo);

    // Converte a entidade salva de volta para DTO
    return mapper.toDTO(salvo);
}


    // Deletar jogo por ID
    public void deletar(Long id) {
        repository.deleteById(id);
    }

public JogoDTO atualizar(Long id, JogoDTO dto) {
    Jogo jogo = repository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

    jogo.setNome(dto.getNome());
    jogo.setResumo(dto.getResumo());
    jogo.setCapa(dto.getCapa());
    jogo.setDataLancamento(dto.getDataLancamento());

    // Atualiza os gêneros, criando-os se não existirem
    if (dto.getGeneros() != null && !dto.getGeneros().isEmpty()) {
        List<Genero> generos = dto.getGeneros().stream()
            .map(generoDto -> generoRepository.findByNomeIgnoreCase(generoDto.getNome())
                .orElseGet(() -> {
                    Genero novoGenero = new Genero();
                    novoGenero.setNome(generoDto.getNome());
                    return generoRepository.save(novoGenero);
                }))
            .collect(Collectors.toList());

        jogo.setGeneros(generos);
    } else {
        jogo.setGeneros(null); // Se o campo vier nulo ou vazio,
    }

    Jogo atualizado = repository.save(jogo);
    return mapper.toDTO(atualizado);
}
    public List<JogoDTO> listarPorGenero(String nomeGenero) {
        List<Jogo> jogos = repository.findByGenerosNomeIgnoreCase(nomeGenero);
        return jogos.stream()
                .map(mapper::toDTO)
                .toList();
    }

}

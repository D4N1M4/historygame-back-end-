package br.com.ifpe.historygame.service;

import br.com.ifpe.historygame.dto.GeneroDTO;
import br.com.ifpe.historygame.entity.Genero;
import br.com.ifpe.historygame.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    public Genero buscarOuCriar(String nome) {
        return generoRepository.findByNomeIgnoreCase(nome)
        .orElseGet(() -> generoRepository.save(new Genero(nome)));
    }

    public GeneroDTO salvarSeNaoExistir(GeneroDTO dto) {
        Genero generoExistente = generoRepository.findByNomeIgnoreCase(dto.getNome()).orElse(null);

        if (generoExistente != null) {
            dto.setId(generoExistente.getId());
            return dto;
        }

        Genero novo = new Genero();
        novo.setNome(dto.getNome());

        Genero salvo = generoRepository.save(novo);
        dto.setId(salvo.getId());
        return dto;
    }
}

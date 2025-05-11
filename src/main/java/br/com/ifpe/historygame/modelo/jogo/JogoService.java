package br.com.ifpe.historygame.modelo.jogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders; 
import br.com.ifpe.historygame.config.IGDBConfig;
import br.com.ifpe.historygame.dto.JogoDTO;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class JogoService {

    @Autowired
    private IGDBConfig igdbConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JogoRepository jogoRepository;

    public void importarJogosIGDB() {
        String url = igdbConfig.getApiUrl();
        String query = "fields name,summary,genres.name,platforms.name,game_modes.name,themes.name,screenshots.url,artworks.url,slug,url,first_release_date; limit 10;";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", igdbConfig.getClientId());
        headers.set("Authorization", "Bearer " + igdbConfig.getBearerToken());
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        ResponseEntity<JogoDTO[]> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                JogoDTO[].class
        );

        JogoDTO[] jogos = response.getBody();

        if (jogos != null) {
            for (JogoDTO dto : jogos) {
                if (!jogoRepository.existsById(dto.getId())) {
                    Jogo jogo = converterParaEntidade(dto);
                    jogoRepository.save(jogo);
                }
            }
        }
    }

    private Jogo converterParaEntidade(JogoDTO dto) {
        Jogo jogo = new Jogo();
        jogo.setId(dto.getId());
        jogo.setNome(dto.getName());
        jogo.setResumo(dto.getSummary());
        jogo.setSlug(dto.getSlug());
        jogo.setUrl(dto.getUrl());

        if (dto.getFirst_release_date() != null) {
            String data = Instant.ofEpochSecond(dto.getFirst_release_date())
                    .atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            jogo.setDataLancamento(data);
        }

        jogo.setGeneros(dto.getGenres());
        jogo.setPlataformas(dto.getPlatforms());
        jogo.setModosJogo(dto.getGame_modes());
        jogo.setTemas(dto.getThemes());
        jogo.setScreenshots(dto.getScreenshots());
        jogo.setArtworks(dto.getArtworks());

        return jogo;
    }
}


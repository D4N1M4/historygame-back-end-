import api from '@/assets/js/axios';

const JOGO_ENDPOINT = '/jogos';

export default class JogoService {
  getAllGames() {
    return api.get(JOGO_ENDPOINT).then(res => res.data);
  }

  getGameById(id) {
  return api.get(`/jogos/${id}`).then(res => res.data);
  }

  searchGamesByName(nome) {
    return api.get(`${JOGO_ENDPOINT}/buscar`, { params: { busca: nome } }).then(res => res.data);
  }

  getGamesByGenero(genero) {
    return api.get(`${JOGO_ENDPOINT}/genero/${genero}`).then(res => res.data);
  }

  getGameById(id) {
    return api.get(`${JOGO_ENDPOINT}/${id}`).then(res => res.data);
  }

  createGame(game) {
    return api.post(JOGO_ENDPOINT, game).then(res => res.data);
  }

  updateGame(id, game) {
    return api.put(`${JOGO_ENDPOINT}/${id}`, game).then(res => res.data);
  }

  deleteGame(id) {
    return api.delete(`${JOGO_ENDPOINT}/${id}`);
  }
}

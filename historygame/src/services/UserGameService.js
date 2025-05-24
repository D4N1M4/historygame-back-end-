import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export default {
  // Favoritos
  async adicionarFavorito(uid, jogoId) {
    return axios.post(`${API_URL}/usuarios/${uid}/favoritos/${jogoId}`);
  },

  async removerFavorito(uid, jogoId) {
    return axios.delete(`${API_URL}/usuarios/${uid}/favoritos/${jogoId}`);
  },

  async listarFavoritos(uid) {
    const res = await axios.get(`${API_URL}/usuarios/${uid}/favoritos`);
    return res.data;
  },

  // Jogados
  async adicionarJogado(uid, jogoId) {
    return axios.post(`${API_URL}/usuarios/${uid}/jogados/${jogoId}`);
  },

  async removerJogado(uid, jogoId) {
    return axios.delete(`${API_URL}/usuarios/${uid}/jogados/${jogoId}`);
  },

  async listarJogados(uid) {
    const res = await axios.get(`${API_URL}/usuarios/${uid}/jogados`);
    return res.data;
  },

  // Desejados
  async adicionarDesejado(uid, jogoId) {
    return axios.post(`${API_URL}/usuarios/${uid}/desejados/${jogoId}`);
  },

  async removerDesejado(uid, jogoId) {
    return axios.delete(`${API_URL}/usuarios/${uid}/desejados/${jogoId}`);
  },

  async listarDesejados(uid) {
    const res = await axios.get(`${API_URL}/usuarios/${uid}/desejados`);
    return res.data;
  },

  // Registro (opcional)
  async registrarUsuario(uid, nome, email) {
    return axios.post(`${API_URL}/usuarios/registrar`, {
      uid, nome, email
    });
  }
};

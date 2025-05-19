import api from '@/assets/js/axios';

const GENERO_ENDPOINT = '/generos';

export default class GeneroService {
  async getAllGeneros() {
    const res = await api.get(GENERO_ENDPOINT);
      return res.data;
  }
}

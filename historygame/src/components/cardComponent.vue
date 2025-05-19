<template>
  <div class="card mb-3 custom-card" @click="handleClick">
    <img :src="fullImageUrl" class="card-img-top" alt="Imagem do Jogo">
    <div class="card-body">
      <h5 class="card-title">{{ nome }}</h5>
      <p class="card-text">{{ truncatedDescricao }}</p>
      <p class="card-text">Modo de Jogo: {{ modoJogo }}</p>
      <p class="card-text">Data de Lançamento: {{ dataLancamento }}</p>
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits, computed } from 'vue';
import imagemPadrao from '@/assets/jogosSemImagem.jpg'; // ✅ moderno

const emit = defineEmits(['card-click']); // ✅ emite evento no clique

const props = defineProps({
  nome: { type: String, required: true },
  modoJogo: { type: String, required: true },
  resumo: { type: String, required: true },
  dataLancamento: { type: String, required: true },
  capa: { type: String, required: true },
});

const maxLength = 100;

const truncatedDescricao = computed(() => {
  return props.resumo && props.resumo.length > maxLength
    ? props.resumo.substring(0, maxLength) + '...'
    : props.resumo;
});

const fullImageUrl = computed(() => {
  if (!props.capa || props.capa.trim() === '') {
    return imagemPadrao;
  }

  return props.capa.startsWith('http')
    ? props.capa
    : `https:${props.capa.replace('t_thumb', 't_cover_big')}`;
});

const handleClick = () => {
  emit('card-click'); // ✅ pode ser usado no pai para redirecionar, etc.
};
</script>


<style scoped>
.custom-card {
  width: 325px;
  height: 459px;
  cursor: pointer; /* Indica que o card é interativo */
}

.card-img-top {
  width: 200px;
  height: auto;
  object-fit: cover;
  display: block;
  margin: 0 auto;
  margin-top: 10px;
}

.card-body {
  padding: 15px;
}

.card {
  border: 1px solid #ddd;
  border-radius: 5px;
}
</style>
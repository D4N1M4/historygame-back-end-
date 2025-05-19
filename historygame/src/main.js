import {createApp} from 'vue';
import App from './App.vue';
import router from './routes';
import './assets/styles/global.css' 
import 'swiper/swiper-bundle.css'

const fontAwesomeLink = document.createElement('link');
fontAwesomeLink.rel = 'stylesheet';
fontAwesomeLink.href = 'https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css';
document.head.appendChild(fontAwesomeLink);

const app = createApp(App);

app.use(router);
app.mount('#app'); // <--- isso é obrigatório!

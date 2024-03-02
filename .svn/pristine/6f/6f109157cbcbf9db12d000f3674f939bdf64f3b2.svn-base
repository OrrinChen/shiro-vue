import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import request from "@/utils/request";
import './assets/gloable.css';
import Loading from "@/utils/loading.js";
// 二维码生成器
import VueQriously from 'vue-qriously'
Vue.use(VueQriously)
Vue.prototype.$Loading = Loading;
Vue.config.productionTip = false
Vue.prototype.request = request
Vue.use(ElementUI,{size:"small"});
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

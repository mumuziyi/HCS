import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.config.productionTip = false
new Vue({
    el: '#app',
    router,
    render: h => h(App)
});

import Element from 'element-ui';
Vue.use(Element, { size: 'small', zIndex: 3000 });
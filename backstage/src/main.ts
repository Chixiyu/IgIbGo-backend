import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import axios from "axios";

axios.defaults.baseURL=process.env.VUE_APP_SERVER;

// axios interceptor: use token for request
axios.interceptors.request.use(function (config){
    const token=store.state.token;
    if(token!=null){
        // eslint-disable-next-line @typescript-eslint/ban-ts-comment
        // @ts-ignore
        config.headers.token=token;
        console.log("used token: ",token);
    }
    return config;
},error => {
    return Promise.reject(error);
})

createApp(App).use(store).use(router).use(Antd).mount('#app')

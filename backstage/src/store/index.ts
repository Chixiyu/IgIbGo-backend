import {createStore} from 'vuex'

declare let SessionStorage:any;
const USER = 'user';
const STATE = 'state';
const TOKEN = 'token';
const store = createStore({
    state: {
        user: SessionStorage.get(USER) || {},
        loginState: SessionStorage.get(STATE) || false,
        token: SessionStorage.get(TOKEN)||{}
    },
    getters: {},
    mutations: {
        setUser(state, user: string) {
            state.user = user;
            SessionStorage.set(USER, user);
        },
        setStatus(state, loginState) {
            state.loginState = loginState;
            SessionStorage.set(STATE, loginState);
        },
        setToken(state, token: string) {
            state.token = token;
            SessionStorage.set(TOKEN,token);
        }
    },
    actions: {},
    modules: {}
})

export default store;
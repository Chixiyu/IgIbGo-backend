<template>
  <a-layout class="layout">
    <a-layout-header>
      <div class="logo"/>
      <a-menu
          v-model:selectedKeys="selectedKeys"
          theme="dark"
          mode="horizontal"
          :style="{ lineHeight: '64px' }"
      >
        <a-menu-item key="1">
        <router-link to="/notes">
            Dapesh Notes
          </router-link>
        </a-menu-item>
        <a-menu-item key="2">
          <router-link to="/videos">
            Dapesh Videos
          </router-link>
        </a-menu-item>
        <a-menu-item style="margin-left: 1250px">
          <label style="margin-right: 10px" v-show="loginStatus">
            {{ username }}
          </label>
          <a-avatar size="small" v-show="loginStatus">
            <template #icon>
              <UserOutlined/>
            </template>
          </a-avatar>
        </a-menu-item>
        <a-menu-item @click="logout">
          <label v-show="loginStatus">
            Logout
          </label>
        </a-menu-item>
      </a-menu>
    </a-layout-header>
  </a-layout>
</template>

<script lang="ts">
import {computed, defineComponent} from "vue";
import {UserOutlined} from '@ant-design/icons-vue';
import store from "@/store";
import axios from "axios";

export default defineComponent({
  name: "TheHeader",
  // props:{
  //   username: String,
  //   loginStatus: Boolean
  // },
  setup() {
    const loginStatus = computed(() => store.state.loginState);
    const username = computed(() => store.state.user);
    const token = computed(() => store.state.token);
    // console.log(loginStatus.value + ' ' + username.value)
    const logout = function () {
      axios.get("/backstage/logout",{
        params:{
          token: token.value
        }
      }).then(function (){
        sessionStorage.clear();
        window.location.href='/';
      })
    }
    return {
      username,
      loginStatus,
      logout
    }
  },
  components: {
    UserOutlined,
  }
})

</script>

<style scoped>
</style>
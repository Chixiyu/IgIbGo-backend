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
          <router-link to="/">
            Home
          </router-link>
        </a-menu-item>
        <a-menu-item key="2">
          <router-link to="/dapesh/videos">
            Dapesh
          </router-link>
        </a-menu-item>
      </a-menu>
    </a-layout-header>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from "vue";

export default defineComponent({
  name: "TheHeader"
})

</script>

<style scoped>

</style>
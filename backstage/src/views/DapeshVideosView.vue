<template>
  <the-header></the-header>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          v-model:selectedKeys="selectedKeys2"
          v-model:openKeys="openKeys"
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
      >
        <a-menu-item>Upload</a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout style="padding: 0 24px 24px">
      <a-breadcrumb style="margin: 16px 0">
        <a-breadcrumb-item>
          <router-link to="/">
            Management
          </router-link>
        </a-breadcrumb-item>
        <a-breadcrumb-item>
          <router-link to="/videso">
            videos
          </router-link>
        </a-breadcrumb-item>
      </a-breadcrumb>
      <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <dapesh-video-upload></dapesh-video-upload>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from "vue";
import TheHeader from "@/components/TheHeader.vue";
import DapeshVideoUpload from "@/components/DapeshVideoUpload.vue";

export default defineComponent({
  name: "DapeshVideosView.vue",
  components: {DapeshVideoUpload, TheHeader}
})


</script>

<style scoped>

</style>
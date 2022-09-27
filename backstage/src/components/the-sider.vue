<template>
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
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: "the-sider"
})
</script>

<style scoped>

</style>
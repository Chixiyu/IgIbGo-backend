<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          v-model:selectedKeys="selectedKeys2"
          v-model:openKeys="openKeys"
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
      >
        <a-menu-item key="sub1">
          <video-camera-outlined/>
          <router-link to="/dapesh/videos">
            Videos
          </router-link>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout style="padding: 0 24px 24px">
      <a-breadcrumb style="margin: 16px 0">
        <a-breadcrumb-item>
          <router-link to="/">
            Home
          </router-link>
        </a-breadcrumb-item>
        <a-breadcrumb-item>
          <router-link to="/dapesh/videos">
            Videos
          </router-link>
        </a-breadcrumb-item>
        <a-breadcrumb-item>
          {{ videoName }}
        </a-breadcrumb-item>
      </a-breadcrumb>
      <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <h1 class="videoTitle">{{ videoName }}</h1>
        <video class="video" :src="videoLink" controls="controls">
        </video>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import {onMounted, ref} from 'vue';
import router from "@/router";
import axios from "axios";
import {VideoCameraOutlined} from '@ant-design/icons-vue';


export default {
  name: "DapeshOneVideoView",
  // methods:{
  // linkToVideo:function (){
  //
  // }
  // },
  components: {
    VideoCameraOutlined,
  },
  setup() {
    const videoLink = ref();
    const videoName = ref();
    onMounted(() => {
      const videoId = router.currentRoute.value.query;
      console.log(videoId);
      console.log(videoId.videoId);
      axios.get("/dapesh/videos/getVideo", {
        params: {
          id: videoId.videoId,
        }
      }).then((response) => {
        videoLink.value = response.data.position;
        videoName.value = response.data.name;
        console.log(videoLink);
      });
    })
    return {
      videoLink: videoLink,
      videoName: videoName,
    }
  }
}
</script>

<style scoped>
.videoTitle {
  /*font-weight: bold;*/
  font-size: 30px;
  margin-left: 150px;
}

.video {
  /*  640*367*/
  width: 80%;
  height: 90%;
  margin-left: 150px;
}
</style>
<template>
  <a-layout>
    <DapeshFrame></DapeshFrame>
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
      </a-breadcrumb>
      <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <a-list item-layout="vertical" size="large" :pagination="pagination" :data-source="videoData">
          <template #renderItem="{ item }">
            <a-list-item key="item.name">
              <!--              like Button-->
              <!--              <template #actions>-->
              <!--                <div style="padding: 0 0 0 0">-->
              <!--                  <like-outlined id="like-button" style="margin-right: 8px"/>-->
              <!--                  {{ item.likes }}-->
              <!--                </div>-->
              <!--              </template>-->
              <template #extra>
                <img
                    width="272"
                    alt="logo"
                    :src="item.cover"
                />
              </template>
              <a-list-item-meta :description="item.subtitle">
                <template #title>
                  <a @click="linkToVideo(item.id)">{{ item.name }}</a>
                </template>
              </a-list-item-meta>
              {{ item.description }}
            </a-list-item>
          </template>
        </a-list>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">

import {defineComponent, onMounted, ref} from 'vue';
import DapeshFrame from "@/components/DapeshFrame.vue";
// import {LikeOutlined} from '@ant-design/icons-vue';
import axios from "axios";

export default defineComponent({
  name: "DapeshVideosView",
  components: {
    // LikeOutlined,
    DapeshFrame,
  },
  // Like Button
  // methods:{
  //   click(){
  //     axios.get("/dapesh/videos/videoList")
  //   }
  // }

  methods: {
    linkToVideo: function (videoId: any) {
      this.$router.push({
        path: "/dapesh/videos/videoPage",
        query: {videoId}
      });
    }
  },

  setup() {
    const videoData = ref();
    // const type = 'LikeOutlined';
    const likes = ref();
    const pagination = {
      onChange: (page: number) => {
        console.log(page);
      },
      pageSize: 3,
    };
    onMounted(() => {
      axios.get('/dapesh/videos/videoList').then((response) => {
        videoData.value = response.data;
        likes.value = response.data.likes;
      })
    });
    return {
      videoData,
      pagination,
    };
  },
});

</script>

<style scoped>
.ant-list-vertical .ant-list-item-action > li {
  padding: 0 0 0 0;
}
</style>
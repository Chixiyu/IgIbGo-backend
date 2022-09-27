<template>
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
      <a-sub-menu key="sub2">
        <template #title>
            <span>
                <edit-outlined/>
                Notes
            </span>
        </template>
        <a-menu-item-group>
          <template #title>
            Grade 9
          </template>
          <a-menu-item v-for="item in subjects" :key="item" @click="selectSubject(item)">{{ item }}</a-menu-item>
        </a-menu-item-group>
      </a-sub-menu>
    </a-menu>
  </a-layout-sider>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import {EditOutlined, VideoCameraOutlined} from '@ant-design/icons-vue';
import axios from 'axios';
import "vue-router/dist/vue-router";


export default defineComponent({
  name: 'DapeshFrame',
  components: {
    VideoCameraOutlined,
    EditOutlined
  },
  methods:{
    selectSubject:function (subject:string){
      this.$router.push({
        path:'/dapesh/notes',
        query:{subject}
      });
    }
  },

  setup() {
    const subjects = ref();
    onMounted(() => {
      axios.get('/dapesh/subjects').then((response) => {
        subjects.value = response.data;
        // console.log(response);
      })
    });
    return {
      subjects,
    };
  },
})
</script>

<style scoped>

#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}

</style>

<!--debug part-->

<!--<script lang="ts">-->
<!--import Vue, {onMounted} from 'vue';-->
<!--import {defineComponent, ref} from 'vue';-->
<!--import axios from 'axios';-->


<!--export default defineComponent({-->
<!--  name: 'DapeshView',-->
<!--  setup() {-->
<!--    const subjects=ref();-->
<!--    onMounted(()=>{-->
<!--      axios.get('/dapesh/subjects').then((response)=>{-->
<!--        const data=response.data;-->
<!--        subjects.value=data;-->
<!--        console.log(response);-->
<!--      })-->
<!--    })-->
<!--    return{-->
<!--      subjects-->
<!--    }-->
<!--    // const k=ref();-->
<!--    // onMounted(()=>{-->
<!--    //   axios.get('dapesh/subjects').then((resp)=>{-->
<!--    //     k.value=resp.data;-->
<!--    //     console.log(resp);-->
<!--    //   })-->
<!--    // })-->
<!--    // return {-->
<!--    //   k-->
<!--    // };-->
<!--  },-->
<!--})-->
<!--</script>-->


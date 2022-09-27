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
          <router-link to="/dapesh/notes">
            Notes
          </router-link>
        </a-breadcrumb-item>
      </a-breadcrumb>
      <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <a-list item-layout="horizontal" :data-source="notes">
          <template #renderItem="{ item }">
            <a-list-item>
              <a-list-item-meta
                  :description="'Author: '+item.author"
              >
                <template #title>
                  <a @click="linkToNote(item.id)">{{ item.name }}</a>
                </template>
              </a-list-item-meta>
            </a-list-item>
          </template>
        </a-list>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import router from "@/router";
import DapeshFrame from "@/components/DapeshFrame.vue";
import axios from "axios";
import "vue-router/dist/vue-router";

// interface DataItem {
//   title: string;
// }
// const notes: DataItem[] = [
// ];

export default defineComponent({
  components: {DapeshFrame},
  methods: {
    linkToNote: function (noteId: number) {
      this.$router.push({
        path: '/dapesh/notes/notePage',
        query: {noteId},
      })
    },
  },
  watch: {
    $route: {
      handler() {
        if (this.$route.query.subject) {
          this.getNoteList();
        }
      },
      deep:true,
      immediate: true,
    }
  },

  setup() {
    const notes = ref();

    function getNoteList(){
      const subject=router.currentRoute.value.query;
      axios.get('/dapesh/notes/noteList', {
        params: {
          subject: subject.subject,
        }
      }).then((resp: any) => {
        notes.value = resp.data;
      });
    }

    onMounted(() => {
      getNoteList();
    });


    return {
      notes,
      getNoteList
    };
  }
})
</script>

<style scoped>

</style>
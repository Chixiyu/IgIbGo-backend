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
      <iframe :src="notePosition" height="700px"></iframe>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import axios from "axios";
import router from "@/router";
import DapeshFrame from "@/components/DapeshFrame.vue";

export default defineComponent({
  components: {DapeshFrame},
  setup() {
    const noteId = router.currentRoute.value.query;
    const notePosition = ref();

    // windows refresh to request
    onMounted(() => {
      console.log('note id: ' + noteId.noteId);
      axios.get('/dapesh/notes/getNote', {
        params: {
          id: noteId.noteId,
        },
      }).then((resp: any) => {
        console.log('data: ' + resp.data);
        notePosition.value = resp.data.position;
      });
    });

    return {
      noteId,
      notePosition
    }
  }
})
</script>

<style scoped>

</style>
<template>
  <div class="upload">
    <a-form
        :model="formState"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        enctype="multipart/form-data"
        @submit="submitForm()"
    >
      <a-form-item
          label="Note Title"
          name="noteTitle"
          :rules="[{ required: true, message: 'The note title can\'t be null '}]"
      >
        <a-input v-model:value="formState.noteTitle"/>
      </a-form-item>

      <a-form-item
          label="Author"
          name="author"
          :rules="[{ required: true, message: 'The author can\'t be null'}]"
      >
        <a-input v-model:value="formState.author"/>
      </a-form-item>

      <a-form-item label="Subject" name="subject">
        <a-select
          v-model:value="selectedSubject"
          >
          <a-select-option v-for="item in subjects" :key="item" :value="item">{{item}}</a-select-option>
        </a-select>
      </a-form-item>

      <div class="upload-btn">
        <a-upload-dragger
            v-model:file-list="fileList"
            action="http://localhost:8081/backstage/notes/upload"
            :headers="headers"
            name="noteFile"
            accept=".html"
            @change="handleChange"
        >
          <p class="ant-upload-drag-icon">
            <inbox-outlined></inbox-outlined>
          </p>
          <p class="ant-upload-text">Note html file upload (required)</p>
          <p class="ant-upload-hint">
            Click or drag file to this area to upload
          </p>
        </a-upload-dragger>
      </div>

      <div style="margin-top: 20px;margin-left: 350px">
        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit">Submit</a-button>
        </a-form-item>
      </div>
    </a-form>
  </div>

</template>

<script lang="ts">
import {computed, defineComponent, onMounted, reactive, ref} from 'vue';
import store from "@/store";
import {InboxOutlined} from '@ant-design/icons-vue';
import {message, UploadChangeParam} from "ant-design-vue";
import axios from "axios";

interface FormState {
  noteTitle: string;
  author: string;
  subject: string;
}

export default defineComponent({
  components: {
    InboxOutlined,
  },


  setup() {
    const formState = reactive<FormState>({
      noteTitle: '',
      author: '',
      subject: '',
    });
    const fileList = ref([]);
    const selectedSubject=ref<string>();

    const token = ref<boolean>(computed(() => store.state.token).value);
    console.log('token:' + token.value);

    const handleChange = (info: UploadChangeParam) => {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }
      if (info.file.status === 'done') {
        message.success(`${info.file.name} file uploaded successfully`);
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} file upload failed.`);
      }
    };

    const submitForm=()=>{
      console.log('title: '+formState.noteTitle);
      console.log('author: '+formState.author);
      console.log('subject: '+selectedSubject.value);
      axios.post('/backstage/notes/noteInfo',0,{
        params:{
          noteTitle: formState.noteTitle,
          author: formState.author,
          subject: selectedSubject.value,
        }
      }).then(function (resp){
        if(resp.data===true){
          message.success('Successfully submitted');
        }
        else{//resp.data===false (failed)
          message.error('Submission failed, please check login status')
        }
      });
    };

    const subjects=ref();
    onMounted(()=>{
      axios.get('/dapesh/subjects').then((resp)=>{
        console.log(resp.data);
        subjects.value=resp.data;
      });
    });


    return {
      formState,
      token,
      fileList,
      selectedSubject,
      handleChange,
      submitForm,
      subjects,
      headers:{
        Token: token.value,
      }
    };
  },
});

</script>

<style scoped>

.upload {
  width: 800px;
}
.upload-btn{
  margin-left:265px;
  margin-bottom: 30px;
}

</style>
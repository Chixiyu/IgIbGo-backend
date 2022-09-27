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
          label="Video name"
          name="videoName"
          :rules="[{ required: true, message: 'The video name can\'t be null '}]"
      >
        <a-input v-model:value="formState.videoName"/>
      </a-form-item>

      <a-form-item
          label="Description"
          name="description"
          :rules="[{ required: true, message: 'The video description can\'t be null'}]"
      >
        <a-input v-model:value="formState.description"/>
      </a-form-item>

      <a-form-item
          label="Video Subtitle"
          name="videoSubtitle"
          :rules="[{ required: true, message: 'The video description can\'t be null'}]"
      >
        <a-input v-model:value="formState.videoSubtitle"/>
      </a-form-item>

      <div class="upload-btn">
        <a-upload-dragger
            v-model:file-list="fileList"
            action="http://localhost:8081/backstage/videos/upload"
            :headers="headers"
            name="video"
            accept=".mov,.mp4,.mkv"
            @change="handleChange"
        >
          <p class="ant-upload-drag-icon">
            <inbox-outlined></inbox-outlined>
          </p>
          <p class="ant-upload-text">Video Upload (required)</p>
          <p class="ant-upload-hint">
            Click or drag file to this area to upload
          </p>
        </a-upload-dragger>
      </div>


      <div class="upload-btn">
        <a-upload-dragger
            v-model:file-list="fileList2"
            action="http://localhost:8081/backstage/videoCovers/upload"
            :headers="headers"
            name="videoCover"
            accept=".jpg,.jpeg,.png"
            @change="handleChange"

        >
          <p class="ant-upload-drag-icon">
            <inbox-outlined></inbox-outlined>
          </p>
          <p class="ant-upload-text">Upload Video Cover (required)</p>
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
import {computed, defineComponent, reactive, ref} from 'vue';
import store from "@/store";
import {InboxOutlined} from '@ant-design/icons-vue';
import {message, UploadChangeParam} from "ant-design-vue";
import axios from "axios";

interface FormState {
  videoName: string;
  description: string;
  videoSubtitle: string;
}

export default defineComponent({
  components: {
    InboxOutlined,
  },


  setup() {
    const formState = reactive<FormState>({
      description: '',
      videoName: '',
      videoSubtitle: '',
    });
    const fileList = ref([]);
    const fileList2 = ref([]);

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
      axios.post('/backstage/videos/videoInfo',0,{
        params:{
          videoName: formState.videoName,
          videoSubtitle: formState.videoSubtitle,
          description: formState.description
        }
      }).then(function (resp){
        if(resp.data===true){
          message.success('Successfully submitted');
        }
        else{//resp.data===false (failed)
          message.error('Submmission failed, please check login status')
        }
      });
    };


    return {
      formState,
      token,
      fileList,
      fileList2,
      handleChange,
      submitForm,
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
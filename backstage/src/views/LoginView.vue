<template>
  <div>
    <the-header :username="username" :login-status="loginStatus"></the-header>
    <div class="title">
      Welcome to management panel!
      <div>
        <a-button class="btn" type="primary" @click="showModal" v-show="!loginStatus">Login</a-button>
        <a-modal
            v-model:visible="visible"
            title="Login"
            :confirm-loading="confirmLoading"
            @ok="handleOk"
        >
          <a-form
              :model="formState"
              name="basic"
              :label-col="{ span: 8 }"
              :wrapper-col="{ span: 16 }"
              @finish="onFinish"
              @finishFailed="onFinishFailed"
              style="margin-right: 60px"
              @submit="submitLogin()"
          >
            <a-form-item
                label="Huili Email"
                name="username"
                :rules="[{ required: true, message: 'Please input your Huili email!' }]"
            >
              <a-input v-model:value="formState.email"/>
            </a-form-item>

            <a-form-item
                label="Password"
                name="password"
                :rules="[{ required: true, message: 'Please input your password!' }]"
            >
              <a-input-password v-model:value="formState.password"/>
            </a-form-item>

            <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
              <a-button type="primary" html-type="submit">Submit</a-button>
            </a-form-item>
          </a-form>
          <!--          <span>-->
          <!--            Your <b>Huili School</b> email-->
          <!--          </span>-->
          <!--          <a-input v-model="username.value" style="margin-top:5px; margin-bottom: 20px"-->
          <!--                   placeholder="Input your email here. It must be your Huili email to login"></a-input>-->
          <!--          <span>-->
          <!--            Your password:-->
          <!--          </span>-->
          <!--          <a-input-password v-model="password.value" style="margin-top:5px;margin-bottom: 10px"-->
          <!--                            placeholder="Input your password here"></a-input-password>-->
        </a-modal>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {ref, defineComponent, computed} from 'vue';
import TheHeader from "@/components/TheHeader.vue";
import axios from "axios";
import {message} from 'ant-design-vue';
import store from "@/store";

interface FormState {
  email: string;
  password: string;
  loginState: string;
}

export default defineComponent({
  name: 'HomeView',

  components: {TheHeader},
  setup() {
    const visible = ref<boolean>(false); // modal's visibility
    const confirmLoading = ref<boolean>(false); // modal loading
    const loginResult = ref<string>();//
    const username = ref<string>();
    const loginStatus=ref<boolean>(computed(()=>store.state.loginState).value);
    console.log('login status: '+loginStatus.value);

    // from ant design, shows the popup modal
    const showModal = () => {
      visible.value = true;
    };

    // from ant design, closes the popup modal
    const handleOk = () => {
      confirmLoading.value = true;
      setTimeout(() => {
        visible.value = false;
        confirmLoading.value = false;
      });
    };

    // shows the state of the form
    const formState = ref<FormState>({
      email: '',
      password: '',
      loginState: '0'
    });

    // submit login form data to backend, and deals with the status given back from backend
    const submitLogin = () => {
      // console.log('username ' + formState.value.email);
      // console.log('password ' + formState.value.password)
      axios.post("/backstage/login", 0, {
        params: {
          email: formState.value.email,
          password: formState.value.password
        }
      }).then(function (resp) {
        // console.log(resp);
        if (resp.data.status === 0) {
          loginResult.value = 'Login failed';
          loginStatus.value=false;
          message.error(loginResult.value);
        }
        else if (resp.data.status === 1) {
          formState.value.loginState = '1';
          username.value=resp.data.username;
          loginStatus.value=true;
          store.commit("setUser",username.value);
          store.commit("setStatus",loginStatus.value);
          store.commit("setToken",resp.data.token);
          if (resp.data.admin === true) {
            loginResult.value = 'You are a Dapesh member and Admin!';
          }
          else if (resp.data.dapesh === true) {
            loginResult.value = 'You are a Dapesh member!';
          }
          else {
            loginResult.value = 'You are now logged in';
          }
          message.success(loginResult.value);
        }
        else if (resp.data.status === 2) {
          formState.value.loginState = '1';
          if (resp.data.admin === true) {
            loginResult.value = 'You are a Dapesh member and Admin! Password assigned';
          }
          else if (resp.data.dapesh === true) {
            loginResult.value = 'You are a Dapesh member! Password assigned';
          }
          else {
            loginResult.value = 'You are now logged in, password assigned';
          }
          message.success(loginResult.value);
        }
      })
    }

    return {
      visible,
      confirmLoading,
      showModal,
      submitLogin,
      handleOk,
      loginResult,
      formState,
      username,
      loginStatus
    }
  }
})


</script>

<style scoped>
.title {
  /*position: absolute;*/
  /*top: 100px;*/
  /*left: 35%;*/
  text-align: center;
  margin-top: 300px;
  font-size: 40px;
}

.btn {
  margin-right: 30px;
}
</style>
<template>
  <div>
    <div class="register_view">
      <el-form :model="registerForm" class="register_form">
        <div class="title_view">{{ projectName }}注册</div>
        <div class="list_item">
          <div class="list_label">账号：</div>
          <input
            class="list_inp"
            v-model="registerForm.account"
            placeholder="请输入账号"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">密码：</div>
          <input
            class="list_inp"
            v-model="registerForm.password"
            placeholder="请输入密码"
            type="password"
          />
        </div>
        <div class="list_item">
          <div class="list_label">确认密码：</div>
          <input
            class="list_inp"
            v-model="registerForm.password2"
            type="password"
            placeholder="请输入确认密码"
          />
        </div>
        <div class="list_item">
          <div class="list_label">头像：</div>
          <div class="list_file_list">
            <uploads
              action="file/upload"
              tip="请上传头像"
              :limit="3"
              style="width: 100%; text-align: left"
              :fileUrls="
                registerForm.headportrait ? registerForm.headportrait : ''
              "
              @change="headportraitUploadSuccess"
            >
            </uploads>
          </div>
        </div>
        <div class="list_item">
          <div class="list_label">姓名：</div>
          <input
            class="list_inp"
            v-model="registerForm.name"
            placeholder="请输入姓名"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">性别：</div>
          <el-select
            class="list_sel"
            v-model="registerForm.gender"
            placeholder="请选择性别"
          >
            <el-option
              v-for="item in usergenderLists"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
        </div>
        <div class="list_item">
          <div class="list_label">手机号码：</div>
          <input
            class="list_inp"
            v-model="registerForm.mobilenumber"
            placeholder="请输入手机号码"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">身份证号码：</div>
          <input
            class="list_inp"
            v-model="registerForm.idnumber"
            placeholder="请输入身份证号码"
            type="text"
          />
        </div>
        <div class="list_btn">
          <el-button class="register" type="success" @click="handleRegister"
            >注册</el-button
          >
          <div class="r-login" @click="close">已有账号，直接登录</div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onMounted } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const projectName = context?.$project.projectName
//获取注册类型
import { useRoute } from 'vue-router'
const route = useRoute()
const tableName = ref('user')

//公共方法
const getUUID = () => {
  return new Date().getTime()
}

const registerForm = ref({
  gender: '',
})
const usergenderLists = ref([])
const init = () => {
  usergenderLists.value = '男,女'.split(',')
}
const headportraitUploadSuccess = (fileUrls) => {
  registerForm.value.headportrait = fileUrls
}
// 多级联动参数
//注册按钮
const handleRegister = () => {
  let url = tableName.value + '/register'
  if (!registerForm.value.account) {
    context?.$toolUtil.message(`账号不能为空`, 'error')
    return false
  }
  if (!registerForm.value.password) {
    context?.$toolUtil.message(`密码不能为空`, 'error')
    return false
  }
  if (registerForm.value.password != registerForm.value.password2) {
    context?.$toolUtil.message('两次密码输入不一致', 'error')
    return false
  }
  if (registerForm.value.headportrait != null) {
    registerForm.value.headportrait = registerForm.value.headportrait.replace(
      new RegExp(context?.$config.url, 'g'),
      ''
    )
  }
  if (!registerForm.value.name) {
    context?.$toolUtil.message(`姓名不能为空`, 'error')
    return false
  }
  if (!registerForm.value.gender) {
    context?.$toolUtil.message(`性别不能为空`, 'error')
    return false
  }
  if (
    registerForm.value.mobilenumber &&
    !context?.$toolUtil.isMobile(registerForm.value.mobilenumber)
  ) {
    context?.$toolUtil.message(`手机号码应输入手机格式`, 'error')
    return false
  }
  if (
    registerForm.value.idnumber &&
    !context?.$toolUtil.checkIdCard(registerForm.value.idnumber)
  ) {
    context?.$toolUtil.message(`身份证号码应输入身份证格式`, 'error')
    return false
  }
  if (
    registerForm.value.money &&
    !context?.$toolUtil.isNumber(registerForm.value.money)
  ) {
    context?.$toolUtil.message(`余额应输入数字`, 'error')
    return false
  }

  context
    ?.$http({
      url: url,
      method: 'post',
      data: registerForm.value,
    })
    .then((res) => {
      context?.$toolUtil.message('注册成功', 'success', (obj) => {
        context?.$router.push({
          path: '/login',
        })
      })
    })
}
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
init()
onMounted(() => {})
</script>
<style lang="scss" scoped>
.register_view {
  background-image: url('');
  // 表单盒子
  .register_form {
  }
  // 标题样式
  .title_view {
  }
  // item盒子
  .list_item {
    // label
    .list_label {
    }
    // 输入框
    :deep(.list_inp) {
    }
  }
  //下拉框样式
  :deep(.list_sel) {
    //去掉默认样式
    .select-trigger {
      height: 100%;
      .el-input {
        height: 100%;
      }
    }
  }
  //图片上传样式
  .list_file_list {
    //提示语
    :deep(.el-upload__tip) {
    }
    //外部盒子
    :deep(.el-upload--picture-card) {
      //图标
      .el-icon {
      }
    }
    :deep(.el-upload-list__item) {
    }
  }
  //按钮盒子
  .list_btn {
    //注册按钮
    .register {
    }
    //注册按钮悬浮样式
    .register:hover {
    }
    //已有账号
    .r-login {
    }
  }
}
</style>
<style>
.register_view {
  min-height: 100vh;
  position: relative;
  background: url(http://localhost:8080/agriculturalAssistance/file/b51a46971b2644fbb0dc451c854bdeb8.jpg)
    no-repeat center center / 100% 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.register_view .register_form {
  width: 500px;
  box-shadow: rgba(0, 102, 0, 0.3) 0px 4px 9px;
  padding: 90px 60px 30px 20px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  border-radius: 10px;
  background: url(http://localhost:8080/agriculturalAssistance/file/8a909a83dba545859aa9aedcbf242cc2.png),
    rgb(255, 255, 255);
  position: relative;
  margin: 20px 0;
}

.register_view .title_view {
  width: calc(100% + 10px);
  color: rgb(255, 255, 255);
  padding: 0px 0px 0px 30px;
  font-size: 16px;
  text-align: left;
  background: url(http://localhost:8080/agriculturalAssistance/file/613a6d79b2304b44b88f7dfe72049bfe.png)
    no-repeat left top / 100% 100%;
  height: 60px;
  line-height: 60px;
  position: absolute;
  left: -10px;
  top: 12px;
}

.register_view .register_form .list_item {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 20px 0px;
}
.register_view .register_form .list_item .list_label {
  width: 120px;
  text-align: right;
  font-size: 16px;
}
.register_view .register_form .list_item .list_inp {
  height: 36px;
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: calc(100% - 120px);
  color: #999;
  font-size: 16px;
  border-radius: 4px;
  background: #fff;
}
.register_view .register_form .list_item .list_date {
  width: calc(100% - 120px) !important;
  height: 36px;
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  box-sizing: border-box;
  color: #999;
  font-size: 16px;
  border-radius: 4px;
  background: #fff;
}
.register_view .register_form .list_item .list_date .el-icon-time {
  line-height: 36px;
}

.register_view .register_form .list_item .list_sel {
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  box-sizing: border-box;
  width: calc(100% - 120px);
  padding: 0px 10px;
  color: #999;
  font-size: 16px;
  border-radius: 4px;
  background: #fff;
}

.register_view .register_form .list_item .list_file_list {
  width: calc(100% - 120px);
}

.register_view .register_form .list_item .el-upload--picture-card {
  cursor: pointer;
  width: 90px;
  height: 60px;
  line-height: 60px;
  text-align: center;
  border: 1px solid rgb(221, 221, 221);
  border-radius: 4px;
  background: #fff;
}

.register_view .register_form .list_item .el-upload--picture-card .el-icon {
  font-size: 22px;
  color: rgb(153, 153, 153);
}

.register_view .register_form .list_item .el-upload__tip {
  font-size: 16px;
  color: rgb(153, 153, 153);
  margin: 7px 0px 0px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.register_view .register_form .list_item .el-upload-dragger {
  border: 1px solid rgb(221, 221, 221);
  box-sizing: border-box;
  width: 100%;
  height: auto;
  text-align: center;
  cursor: pointer;
  overflow: hidden;
  padding: 10px;
  border-radius: 4px;
  background: #fff;
}

.register_view .register_form .list_item .el-upload-dragger .el-icon--upload {
  font-size: 60px;
  color: rgba(0, 102, 0, 1);
  line-height: 50px;
  margin: 0px;
}

.register_view .register_form .list_item .el-upload-dragger .el-upload__text {
  font-size: 16px;
  color: rgb(153, 153, 153);
  margin: 5px 0px 0px;
}

.register_view
  .register_form
  .list_item
  .el-upload-dragger
  .el-upload__text
  em {
  color: rgba(0, 102, 0, 1);
}

.register_view .register_form .list_item .list_radio {
  display: flex;
  width: calc(100% - 120px);
  align-items: center;
  flex-wrap: wrap;
}

.register_view .register_form .list_item .list_radio .el-radio {
  width: auto;
  margin: 0px 20px 0px 0px;
  display: flex;
  align-items: center;
}

.register_view
  .register_form
  .list_item
  .list_radio
  .el-radio
  .el-radio__inner {
  border-color: rgb(153, 153, 153);
  background: rgb(255, 255, 255);
}

.register_view
  .register_form
  .list_item
  .list_radio
  .el-radio.is-checked
  .el-radio__inner {
  border-color: rgba(0, 102, 0, 1);
  background: rgba(0, 102, 0, 1);
}

.register_view
  .register_form
  .list_item
  .list_radio
  .el-radio
  .el-radio__label {
  color: #999;
  font-size: 16px;
}

.register_view
  .register_form
  .list_item
  .list_radio
  .el-radio.is-checked
  .el-radio__label {
  color: rgba(0, 102, 0, 1);
}

.register_view .register_form .list_item .list_checkbox {
  display: flex;
  width: calc(100% - 120px);
  flex-wrap: wrap;
  align-items: center;
}

.register_view .register_form .list_item .list_checkbox .el-checkbox {
  width: auto;
  margin: 0px 15px 0px 0px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.register_view
  .register_form
  .list_item
  .list_checkbox
  .el-checkbox
  .el-checkbox__inner {
  border-color: #999;
  background: rgb(255, 255, 255);
}

.register_view
  .register_form
  .list_item
  .list_checkbox
  .el-checkbox.is-checked
  .el-checkbox__inner {
  border-color: rgba(0, 102, 0, 1);
  background: rgba(0, 102, 0, 1);
}

.register_view
  .register_form
  .list_item
  .list_checkbox
  .el-checkbox
  .el-checkbox__label {
  color: #999;
  font-size: 16px;
}

.register_view
  .register_form
  .list_item
  .list_checkbox
  .el-checkbox.is-checked
  .el-checkbox__label {
  color: #999;
}

.register_view .register_form .list_code {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 10px 0px;
}
.register_view .register_form .list_code .list_code_label {
  width: 120px;
  text-align: right;
  font-size: 16px;
}
.register_view .register_form .list_code .list_code_item {
  width: calc(100% - 120px);
  display: flex;
  align-items: center;
}
.register_view .register_form .list_code .list_code_item .list_code_inp {
  height: 36px;
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: calc(100% - 110px);
  font-size: 16px;
  border-right: 0px;
  border-radius: 4px 0 0 4px;
}
.register_view .register_form .list_code .list_code_btn {
  padding: 0px;
  width: 100px;
  height: 36px;
  line-height: 36px;
  text-align: center;
  border: none;
  background: rgba(0, 102, 0, 1);
  color: rgb(255, 255, 255);
  font-size: 16px;
  border-radius: 0 4px 4px 0;
}

.register_view .register_form .list_btn {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  justify-content: center;
  width: 100%;
  margin: 20px 0px 0px;
  padding: 0 0 0 120px;
}

.register_view .register_form .list_btn .register {
  margin: 0px 10px 12px 0px;
  padding: 0px 24px;
  width: 100%;
  height: 44px;
  font-size: 18px;
  color: rgb(255, 255, 255);
  border-radius: 4px;
  border: 0px;
  background: rgba(0, 102, 0, 1);
  cursor: pointer;
}

.register_view .register_form .list_btn .r-login {
  width: 100%;
  text-align: right;
  cursor: pointer;
  padding: 10px 0px 0px;
  color: #999;
  font-size: 16px;
}
</style>

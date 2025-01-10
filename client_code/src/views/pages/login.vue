<template>
  <div>
    <div class="login_view">
      <el-form :model="loginForm" class="login_form">
        <div class="title_view">SpringBoot+Vue助农平台登录</div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">账号：</div>
          <input
            class="list_inp"
            v-model="loginForm.username"
            placeholder="请输入账号"
          />
        </div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">密码：</div>
          <input
            class="list_inp"
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            @keydown.enter.native="handleLogin"
          />
        </div>
        <div class="list_type" v-if="userList.length > 1">
          <div class="list_label">用户类型：</div>
          <el-select v-model="loginForm.role" placeholder="请选择用户类型">
            <el-option
              v-for="(item, index) in userList"
              :label="item.roleName"
              :value="item.roleName"
            ></el-option>
          </el-select>
        </div>
        <div class="remember_view" v-if="loginType == 1">
          <el-checkbox
            v-model="rememberPassword"
            label="记住密码"
            size="large"
            :true-label="true"
            :false-label="false"
          />
        </div>
        <div class="btn_view">
          <el-button
            class="login"
            v-if="loginType == 1"
            type="success"
            @click="handleLogin"
            >登录</el-button
          >
          <el-button
            class="register"
            type="primary"
            @click="handleRegister('user')"
            >注册用户</el-button
          >
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onMounted } from 'vue'
import { useStore } from 'vuex'
const store = useStore()
import menu from '@/utils/menu'
const userList = ref([])
const menus = ref([])
const loginForm = ref({
  role: '',
  username: '',
  password: '',
})
const tableName = ref('')
const loginType = ref(1)
//是否记住密码
const rememberPassword = ref(true)
const context = getCurrentInstance()?.appContext.config.globalProperties
//注册
const handleRegister = (tableName) => {
  context?.$router.push(`/${tableName}Register`)
}
const handleLogin = () => {
  if (!loginForm.value.username) {
    context?.$toolUtil.message('请输入用户名', 'error')
    return
  }
  if (!loginForm.value.password) {
    context?.$toolUtil.message('请输入密码', 'error')
    return
  }
  if (userList.value.length > 1) {
    if (!loginForm.value.role) {
      context?.$toolUtil.message('请选择角色', 'error')
      verifySlider.reset()
      return
    }
    for (let i = 0; i < menus.value.length; i++) {
      if (menus.value[i].roleName == loginForm.value.role) {
        tableName.value = menus.value[i].tableName
      }
    }
  } else {
    tableName.value = userList.value[0].tableName
    loginForm.value.role = userList.value[0].roleName
  }
  login()
}
const login = () => {
  context
    ?.$http({
      url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
      method: 'post',
    })
    .then(
      (res) => {
        //是否保存当前账号密码至缓存
        if (rememberPassword.value) {
          let loginForm1 = JSON.parse(JSON.stringify(loginForm.value))
          delete loginForm1.code
          context?.$toolUtil.storageSet(
            'frontLoginForm',
            JSON.stringify(loginForm1)
          )
        } else {
          context?.$toolUtil.storageRemove('frontLoginForm')
        }
        context?.$toolUtil.storageSet('frontToken', res.data.token)
        context?.$toolUtil.storageSet('frontRole', loginForm.value.role)
        context?.$toolUtil.storageSet('frontSessionTable', tableName.value)
        store.dispatch('user/getSession')
        let path = context?.$toolUtil.storageGet('toPath')
        if (path) {
          context?.$router.push(path)
          context?.$toolUtil.storageRemove('toPath')
          return
        }
        context?.$router.push(`/index/${tableName.value}Center`)
      },
      (err) => {}
    )
}
//获取菜单
const getMenu = async () => {
  let arr = menu.list()
  if (!arr) {
    let res = await context?.$http.get('menu/list')
    context?.$toolUtil.storageSet('menus', res.data.data.list[0].menujson)
    arr = JSON.parse(res.data.data.list[0].menujson)
  }
  menus.value = arr
  for (let i = 0; i < menus.value.length; i++) {
    if (menus.value[i].hasFrontLogin == '是') {
      userList.value.push(menus.value[i])
    }
  }
}
//初始化
const init = async () => {
  await getMenu()
  //获取缓存是否有保存的账号密码
  let form = context?.$toolUtil.storageGet('frontLoginForm')
  if (form) {
    loginForm.value = JSON.parse(form)
  } else {
    loginForm.value.role = userList.value[0].roleName
  }
}
onMounted(() => {
  init()
})
</script>

<style lang="scss" scoped>
.login_view {
  background-image: url('');
  // 表单盒子
  .login_form {
  }
  .title_view {
  }
  // item盒子
  .list_item {
    // label
    .list_label {
    }
    // 输入框
    .list_inp {
    }
  }
  .list_type {
    .list_label {
    }
    // 下拉框样式
    :deep(.el-select) {
      //去掉默认样式
      .select-trigger {
        height: 100%;
        .el-input {
          height: 100%;
        }
      }
    }
  }
  // 记住密码样式
  .remember_view {
    padding: 0 0 0 130px;
    margin: 0 0 20px;
    width: 100%;

    // 未选中样式
    :deep(.el-checkbox) {
      margin: 0;
      display: flex;
      width: 20%;
      justify-content: center;
      align-items: center;
      // 复选框
      .el-checkbox__inner {
        border: 1px solid #dcdfe6;
        background: #fff;
      }
      // 提示文字
      .el-checkbox__label {
        color: #999;
      }
    }
    // 选中样式
    :deep(.is-checked) {
      //复选框
      .el-checkbox__inner {
        background-color: #006600;
        border-color: #006600;
      }
      // 提示文字
      .el-checkbox__label {
        color: #006600;
      }
    }
  }
  // 按钮盒子
  .btn_view {
    // 登录
    .login {
    }
    // 注册
    .register {
    }
  }
}
</style>
<style>
/*背景*/
.login_view {
  min-height: 100vh;
  position: relative;
  background: url(http://localhost:8080/agriculturalAssistance/file/b51a46971b2644fbb0dc451c854bdeb8.jpg)
    no-repeat center center / 100% 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
/*表单盒子*/
.login_view .login_form {
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
}
/*内部标题*/
.login_view .title_view {
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

/*item*/
.login_view .login_form .list_item {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 20px 0px;
}
.login_view .login_form .list_item .list_label {
  width: 120px;
  text-align: right;
  font-size: 16px;
}
.login_view .login_form .list_item .list_inp {
  height: 36px;
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: calc(100% - 120px);
  font-size: 16px;
  border-radius: 4px;
}
/*用户类型 盒子*/
.login_view .login_form .list_type {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 20px 0px;
}
.login_view .login_form .list_type .list_label {
  width: 120px;
  text-align: right;
  font-size: 14px;
}
.login_view .login_form .list_type .el-select {
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  box-sizing: border-box;
  width: calc(100% - 120px);
  padding: 0px 10px;
  border-radius: 4px;
  font-size: 16px;
  color: rgb(102, 102, 102);
  background: #fff;
}
/*验证码 盒子*/
.login_view .login_form .listCode_view {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 10px 0px;
}
.login_view .login_form .listCode_view .listCode_label {
  width: 120px;
  text-align: right;
  font-size: 16px;
}
.login_view .login_form .listCode_view .listCode_inp {
  height: 36px;
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: calc(100% - 220px);
  font-size: 16px;
  border-radius: 4px 0 0 4px;
}
.login_view .login_form .listCode_view .listCode_btn {
  width: 100px;
  border-style: solid;
  border-color: rgb(221, 221, 221);
  border-image: initial;
  height: 36px;
  line-height: 36px;
  text-align: center;
  border-width: 1px 1px 1px 0px;
  border-radius: 0 4px 4px 0;
  background: #fff;
}
/*按钮 盒子*/
.login_view .login_form .btn_view {
  display: flex;
  align-items: center;
  padding: 0px 0px 0px 120px;
  flex-wrap: wrap;
  width: 100%;
}
.login_view .login_form .btn_view .login {
  margin: 0px 10px 20px 0px;
  padding: 0px 24px;
  width: 100%;
  height: 44px;
  font-size: 16px;
  color: rgb(255, 255, 255);
  border-radius: 4px;
  border: 0px;
  background: linear-gradient(
    180deg,
    rgba(23, 139, 23, 1) 0%,
    rgba(0, 102, 0, 1) 100%
  );
  cursor: pointer;
}
.login_view .login_form .btn_view .register {
  margin: 0px 20px 20px 0px;
  padding: 0px;
  width: auto;
  height: auto;
  font-size: 16px;
  color: rgba(0, 102, 0, 1);
  border-radius: 4px;
  border: 0px;
  background: none;
  cursor: pointer;
}
.login_view .login_form .btn_view .forget {
  margin: 0px 10px 12px 0px;
  padding: 0px;
  width: 100%;
  height: auto;
  font-size: 16px;
  color: rgb(102, 102, 102);
  border-radius: 4px;
  border: 0px;
  background: none;
  cursor: pointer;
  text-align: right;
}
.login_view .login_form .face {
  font-size: 16px;
  color: #ef6000;
  cursor: pointer;
  padding: 0px 0px 0px 120px;
  text-align: left;
  width: 100%;
}
</style>

<template>
  <div>
    <div class="forget_view">
      <el-form :model="forgetForm" class="forget_box">
        <div class="forget_title">
          SpringBoot+Vue助农平台{{
            pageType == 1 ? '找回密码' : pageType == 2 ? '输入密保' : '重置密码'
          }}
        </div>
        <div class="tab_view">
          <div
            class="header_number"
            :class="
              pageType > 1
                ? 'header_number2'
                : pageType == 1
                ? 'header_number1'
                : ''
            "
          >
            <div class="number" v-if="pageType < 2">1</div>
            <div class="number" v-else>
              <el-icon><Check /></el-icon>
            </div>
          </div>
          <div
            class="header_number"
            :class="
              pageType > 2
                ? 'header_number2'
                : pageType == 2
                ? 'header_number1'
                : ''
            "
          >
            <div class="number" v-if="pageType < 3">2</div>
            <div class="number" v-else>
              <el-icon><Check /></el-icon>
            </div>
          </div>
          <div
            class="header_number"
            :class="pageType == 3 ? 'header_number1' : ''"
          >
            <div class="number">3</div>
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const pageType = ref(1)
const forgetForm = ref({})
const userForm = ref({})
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
</script>

<style lang="scss" scoped>
.forget_view {
  background-image: url('http://localhost:8080/agriculturalAssistance/file/0d51e630f6bb434c8a4f98d6f5fb78e3.png');
  // 表单盒子
  .forget_box {
    // 标题
    .forget_title {
    }
    // tab
    // 盒子
    .tab_view {
      padding: 0 0 20px;
      display: flex;
      width: 100%;
      align-items: center;
      // 默认样式
      .header_number {
        display: flex;
        width: 100%;
        font-size: 24px;
        justify-content: center;
        align-items: center;
        .number {
          border: 4px solid #ddd;
          border-radius: 50%;
          color: #aaa;
          display: flex;
          width: 40px;
          justify-content: center;
          align-items: center;
          height: 40px;
        }
      }
      // 当前样式
      .header_number1 {
        .number {
          margin: 0 30px;
          color: rgb(16, 67, 169);
          background: #fff;
          width: 50px;
          font-size: 30px;
          border-color: rgb(16, 67, 169);
          height: 50px;
        }
      }
      // 完成样式
      .header_number2 {
        .number {
          color: rgba(0, 170, 0, 1);
          border-color: rgba(0, 170, 0, 1);
        }
      }
    }
    // item
    .list_item {
      // label
      .item_label {
      }
      // 输入框
      :deep(.list_inp) {
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
    }
    // 按钮盒子
    .list_btn {
      // 获取密保
      :deep(.el-button--success) {
      }
      // 获取密保悬浮
      :deep(.el-button--success:hover) {
      }
      // 确认密保
      :deep(.el-button--primary) {
      }
      // 确认密保悬浮
      :deep(.el-button--primary:hover) {
      }
      // 重置密码
      :deep(.el-button--warning) {
      }
      // 重置密码悬浮
      :deep(.el-button--warning:hover) {
      }
      .r-login {
      }
    }
  }
}
</style>
<style>
.forget_view {
  min-height: 100vh;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-image: url(http://localhost:8080/agriculturalAssistance/file/72771594b3e8431880badf496191c7ce.jpg);
  background-position: center center;
  background-size: 100% 100% !important;
  background-repeat: no-repeat;
  background-attachment: initial;
  background-origin: initial;
  background-clip: initial;
  background-color: initial;
}

.forget_box {
  width: 600px;
  margin: 0 auto;
  padding: 30px 50px 40px 50px;
  background: #fff;
  font-size: 16px;
  border-radius: 10px;
  transform: scale(1);
  -webkit-transform: scale(1);
  -ms-transform: scale(1);
  z-index: 5;
  box-shadow: 0px -3px 5px 0px rgba(0, 0, 0, 0.09),
    0px 12px 13px 0px rgba(0, 0, 0, 0.17), 0px 4px 6px 0px rgba(0, 0, 0, 0.12),
    0px -12px 30px 0px rgba(0, 0, 0, 0.12),
    0px 54px 55px 0px rgba(0, 0, 0, 0.25);
}
.forget_box:before {
  content: '';
  width: 100%;
  height: 0px;
  border-radius: 10px;
  position: absolute;
  top: -10px;
  background: rgba(255, 255, 255, 0.6);
  left: 0;
  transform: scale(0.95);
  -webkit-transform: scale(0.95);
  -ms-transform: scale(0.95);
  z-index: -1;
}

.forget_title {
  width: calc(100% + 0px);
  float: left;
  line-height: 46px;
  font-size: 22px;
  font-weight: 600;
  letter-spacing: 0.5px;
  background: none;
  color: #333;
  position: relative;
  margin-bottom: 20px;
  margin-left: 0px;
  padding-left: 0px;
  text-align: center;
}

.forget_title:before {
  content: '';
  width: 0px;
  height: 100%;
  position: absolute;
  top: 0;
  left: -50px;
  background: #8b5c7e;
}

.list_item {
  width: 100%;
  padding: 0 10px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  margin: 0 0 20px;
  box-shadow: inset 0px -2px 6px 0px rgba(10, 37, 64, 0.35),
    0px 30px 60px -30px rgba(0, 0, 0, 0.3),
    0px 50px 100px -20px rgba(50, 50, 93, 0.25);
}
.list_item .item_label {
  margin-right: 10px;
  white-space: nowrap;
  font-size: 16px;
}
.list_item .item_label i {
  font-size: 24px;
  color: #999;
}
.list_item .list_inp {
  width: 100%;
  border: none;
  border-bottom: 1px solid #ddd;
  height: 40px;
  line-height: 40px;
  font-size: 16px;
}
.list_item .el-select {
  width: 100%;
  border-bottom: 1px solid #ddd;
  height: 40px;
  line-height: 40px;
  font-size: 16px;
  color: #999;
}
.list_item .el-select .el-input__inner {
  font-size: 16px;
  color: #999;
}

.list_type {
  margin-bottom: 20px;
}

.list_btn {
  margin-top: 20px;
  text-align: center;
}

.list_btn .get_btn {
  width: 100%;
  height: 46px;
  line-height: 46px;
  background: #0f43aa;
  border: 0px solid #ccc;
  font-weight: 600;
  font-size: 18px;
  color: #fff;
  margin-bottom: 20px;
  padding: 0;
  border-radius: 5px;
}
.list_btn .get_btn:hover {
}

.list_btn .valid_btn {
  width: 100%;
  height: 46px;
  line-height: 46px;
  background: #0f43aa;
  border: 0px solid #ccc;
  font-weight: 600;
  font-size: 18px;
  color: #fff;
  margin-bottom: 20px;
  padding: 0;
  border-radius: 5px;
}
.list_btn .valid_btn:hover {
}

.list_btn .update_btn {
  width: 100%;
  height: 46px;
  line-height: 46px;
  background: #0f43aa;
  border: 0px solid #ccc;
  font-weight: 600;
  font-size: 18px;
  color: #fff;
  margin-bottom: 20px;
  padding: 0;
  border-radius: 5px;
}
.list_btn .update_btn:hover {
}

.list_btn .r-login {
  font-size: 16px;
  color: #999;
}
.list_btn .r-login:hover {
  cursor: pointer;
  color: #1043a9;
}
</style>

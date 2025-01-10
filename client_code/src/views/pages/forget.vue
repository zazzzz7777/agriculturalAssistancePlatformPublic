<template>
  <div>
    <div class="forget_view">
      <el-form :model="forgetForm" class="forget_box">
        <div class="forget_title">
          SpringBoot+Vue助农平台{{
            pageType == 1 ? '找回密码' : pageType == 2 ? '输入密保' : '重置密码'
          }}
        </div>
        <div class="tab_line">
          <div class="line"></div>
          <div class="num_line">
            <div
              class="line_number"
              :class="
                (pageType == 1 ? 'line_number1' : '',
                pageType > 1 ? 'line_number2' : '')
              "
            >
              <div class="number" v-if="pageType < 2">1</div>
              <el-icon v-else><Check /></el-icon>
            </div>
            <div
              class="line_number"
              :class="
                (pageType == 2 ? 'line_number1' : '',
                pageType > 2 ? 'line_number2' : '')
              "
            >
              <div class="number" v-if="pageType < 3">2</div>
              <el-icon v-else><Check /></el-icon>
            </div>
            <div
              class="line_number"
              :class="pageType == 3 ? 'line_number1' : ''"
            >
              <div class="number">3</div>
            </div>
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
  background-image: url('');
  // 表单盒子
  .forget_box {
    // 标题
    .forget_title {
    }
    // tab
    // 盒子
    .tab_line {
      padding: 30px 0;
      flex-direction: column;
      display: flex;
      width: 100%;
      align-items: center;
      // 中间线
      .line {
        background: #ccc;
        width: 80%;
        height: 2px;
      }
      // item盒子
      .num_line {
        margin: -20px 0 0;
        display: flex;
        width: 80%;
        justify-content: space-between;
        align-items: center;
        height: 40px;
        // 默认样式
        .line_number {
          border: 4px solid #ddd;
          border-radius: 50%;
          color: #aaa;
          background: #eee;
          display: flex;
          width: 40px;
          font-size: 18px;
          justify-content: center;
          align-items: center;
          height: 40px;
        }
        // 选中样式
        .line_number1 {
          color: rgba(64, 158, 255, 1);
          font-size: 22px;
          border-color: rgba(64, 158, 255, 1);
        }
        // 完成样式
        .line_number2 {
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
  background: url(http://localhost:8080/agriculturalAssistance/file/b51a46971b2644fbb0dc451c854bdeb8.jpg)
    no-repeat center center / 100% 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.forget_view .forget_box {
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

.forget_view .forget_title {
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

.forget_view .forget_box .tab_view {
  width: 100%;
  display: flex;
  align-items: center;
  padding: 0px 0px 20px;
}

.forget_view .forget_box .tab_view .tab {
  width: calc(33.3333%);
  padding: 16px 0px;
  box-sizing: border-box;
  text-align: center;
  font-size: 16px;
}
.forget_view .forget_box .tab_view .tab.tab_active {
  width: calc(33.3333%);
  padding: 16px 0px;
  box-sizing: border-box;
  text-align: center;
  font-size: 16px;
  clip-path: polygon(0% 0%, 92% 0%, 100% 50%, 92% 100%, 0px 100%, 8% 50%);
  background: rgba(0, 102, 0, 1);
  color: rgb(255, 255, 255);
}

.forget_view .forget_box .list_item {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: flex-start;
  margin: 0px 0px 20px;
}
.forget_view .forget_box .list_item .item_label {
  width: 120px;
  text-align: right;
  box-sizing: border-box;
  font-size: 16px;
}
.forget_view .forget_box .list_item .list_inp {
  height: 36px;
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: calc(100% - 120px);
  box-sizing: border-box;
}
.forget_view .forget_box .list_item .list_sel {
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  box-sizing: border-box;
  width: calc(100% - 120px);
  padding: 0px 10px;
  border-radius: 0px;
}

.forget_view .forget_box .list_btn {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  width: 100%;
  margin: 0px;
  padding: 0px 0px 0px 120px;
}

.forget_view .forget_box .list_btn .get_btn {
  margin: 0px 10px 0px 0px;
  padding: 0px 24px;
  width: auto;
  height: 36px;
  font-size: 16px;
  color: rgb(255, 255, 255);
  border-radius: 0px;
  border: 0px;
  background: rgba(0, 102, 0, 1);
  cursor: pointer;
}

.forget_view .forget_box .list_btn .valid_btn {
  margin: 0px 10px 0px 0px;
  padding: 0px 24px;
  width: auto;
  height: 36px;
  font-size: 16px;
  color: rgb(255, 255, 255);
  border-radius: 0px;
  border: 0px;
  background: rgba(0, 102, 0, 1);
  cursor: pointer;
}

.forget_view .forget_box .list_btn .update_btn {
  margin: 0px 10px 0px 0px;
  padding: 0px 24px;
  width: auto;
  height: 36px;
  font-size: 16px;
  color: rgb(255, 255, 255);
  border-radius: 0px;
  border: 0px;
  background: rgba(0, 102, 0, 1);
  cursor: pointer;
}

.forget_view .forget_box .list_btn .r-login {
  width: 100%;
  text-align: right;
  cursor: pointer;
  padding: 10px 0px 0px;
  font-size: 15px;
  color: rgb(153, 153, 153);
}
</style>

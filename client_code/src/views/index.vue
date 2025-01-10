<template>
  <div class="home">
    <div class="index_top">
      <div class="index_top_title">SpringBoot+Vue助农平台</div>
      <div class="index_top_right">
        <template v-if="Token">
          <div v-if="date && weather.city" class="weather_time_view">
            <div class="weather">
              <div class="city">{{ weather.city }}</div>
              <div class="wea">{{ weather.wea }} | {{ weather.tem }}℃</div>
            </div>
            <div class="time">
              <div class="date">{{ date }}</div>
              <div class="timer">{{ timer }}</div>
            </div>
          </div>
        </template>
        <el-button
          v-if="!Token"
          type="success"
          icon="User"
          circle
          @click="loginClick"
        >
          登录
        </el-button>
        <div class="user" v-if="Token">
          <div class="avatar-wrapper">
            <img
              class="user-avatar"
              :src="store.getters['user/avatar']"
              style="width: 50px"
            />
            <div class="nickname">{{ $toolUtil.storageGet('frontName') }}</div>
          </div>
          <div
            class="center"
            @click="menuHandler('center')"
            style="cursor: pointer"
          >
            个人中心
          </div>
          <div class="loginOut" @click="loginOut" style="cursor: pointer">
            退出登录
          </div>
        </div>
      </div>
    </div>
    <el-scrollbar class="contain_view">
      <el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
        <el-menu
          :default-openeds="[]"
          :unique-opened="true"
          :default-active="menuIndex"
          class="menu_view"
          mode="horizontal"
          :ellipsis="false"
          @select="menuChange"
          :key="menuIndex"
        >
          <el-menu-item class="first-item" index="0" @click="menuHandler('/')">
            <template #title>
              <span>首页</span>
            </template>
          </el-menu-item>
          <template v-for="(menu, index) in menuList" :key="menu.menu">
            <el-sub-menu
              v-if="menu.child.length > 1"
              :index="index + 2 + ''"
              class="first-item"
            >
              <template #title>
                <span>{{ menu.name }}</span>
              </template>
              <el-menu-item
                class="second-item"
                v-for="(child, sort) in menu.child"
                :key="sort"
                :index="index + 2 + '-' + sort"
                @click="menuHandler(child.url)"
                >{{ child.name }}
              </el-menu-item>
            </el-sub-menu>
            <el-menu-item
              v-else
              :index="index + 2 + ''"
              class="first-item"
              @click="menuHandler(menu.child[0].url)"
            >
              <template #title>
                <span>{{ menu.child[0].name }}</span>
              </template>
            </el-menu-item>
          </template>
        </el-menu>
      </el-scrollbar>
      <div class="rotation_view">
        <mySwiper
          :type="3"
          :data="rotationList"
          :autoHeight="false"
          :autoplay="true"
          :loop="false"
          :navigation="true"
          :pagination="false"
          :paginationType="1"
          :scrollbar="false"
          :slidesPerView="1"
          :spaceBetween="20"
          :centeredSlides="false"
          :freeMode="false"
          :effectType="0"
          :direction="horizontal"
        >
          <template #default="scope">
            <img
              :style="{}"
              :src="scope.row.value ? $config.url + scope.row.value : ''"
            />
          </template>
        </mySwiper>
      </div>
      <router-view />
      <el-backtop :right="100" :bottom="100" />
      <div class="bottom_view">
        <img
          class="bottom_img"
          src="http://chy2.gdnxeco.com/20230912/0d5ae5e141c14b06a52a7c4c2f8ea4cf.jpg"
          alt=""
        />
        <div class="bottom_company"></div>
        <div class="bottom_record"></div>
        <div class="bottom_desc"></div>
      </div>
    </el-scrollbar>
  </div>
</template>
<script setup>
import menu from '@/utils/menu'
import axios from 'axios'
import {
  ref,
  onBeforeUnmount,
  getCurrentInstance,
  nextTick,
  computed,
  watch,
} from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useStore } from 'vuex'
const store = useStore()
const router = useRouter()
const route = useRoute()
const context = getCurrentInstance()?.appContext.config.globalProperties
const Token = ref('')
const weather = ref({})
const date = ref('')
const timer = ref('')
const interval = ref(null)
if (localStorage.getItem('frontToken') && !store.getters['user/session'].id) {
  store.dispatch('user/getSession')
}
const getWeather = () => {
  axios({
    method: 'get',
    url: 'http://v0.yiketianqi.com/free/day?appid=69475998&appsecret=rldbX1Zl&city=北京',
  }).then((res) => {
    weather.value = res.data
  })
}
onBeforeUnmount(() => {
  clearInterval(interval.value)
})
// 获取默认菜单index
const setMenuIndex = () => {
  menuIndex.value = ''
  nextTick(() => {
    menuIndex.value = context?.$toolUtil.storageGet('menuIndex')
      ? context?.$toolUtil.storageGet('menuIndex')
      : '0'
  })
}
// 默认菜单index
const menuIndex = ref('0')
watch(
  () => router.currentRoute.value,
  () => {
    Token.value = context?.$toolUtil.storageGet('frontToken')
    setMenuIndex()
  },
  {
    immediate: true,
  }
)
const init = () => {
  // 获取菜单
  getMenu()
  // 赋值token
  Token.value = context?.$toolUtil.storageGet('frontToken')
  // 时间
  interval.value = setInterval(() => {
    date.value = context?.$toolUtil.getCurDate()
    timer.value = context?.$toolUtil.getCurDateTime().split(' ')[1]
  }, 1000)
  // 天气
  getWeather()
  // 轮播图
  getRotationList()
  if (Token.value) {
    getSession()
  }
}
// 切换菜单保存index
const menuChange = (e) => {
  if (e == 'chat') return
  context?.$toolUtil.storageSet('menuIndex', e)
}
// 轮播图
const rotationList = ref([])
const baseUrl = context?.$config.url
const getRotationList = () => {
  context
    ?.$http({
      url: 'config/list',
      method: 'get',
      params: {
        page: 1,
        limit: 3,
      },
    })
    .then((res) => {
      rotationList.value = res.data.data.list
    })
}
const menuList = ref([])
const role = ref('')
const getMenu = () => {
  let params = {
    page: 1,
    limit: 1,
    sort: 'id',
  }
  context
    ?.$http({
      url: 'menu/list',
      method: 'get',
      params: params,
    })
    .then((res) => {
      context?.$toolUtil.storageSet('menus', res.data.data.list[0].menujson)
    })
  menuList.value = context?.$config.menuList
}
const loginClick = () => {
  context?.$toolUtil.storageSet('toPath', window.history.state.current)
  router.push('/login')
}
const loginOut = () => {
  context?.$toolUtil.message('退出成功', 'success')
  context?.$toolUtil.storageClear()
  router.replace('/index/home')
  context?.$toolUtil.storageSet('menuIndex', '0')
  Token.value = ''
}
//菜单跳转
const menuHandler = (name) => {
  if (name == 'center') {
    name = `${context?.$toolUtil.storageGet('frontSessionTable')}Center`
    menuChange('center')
  }
  router.push(name)
}
// 获取用户信息
const getSession = () => {
  context
    ?.$http({
      url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
      method: 'get',
    })
    .then((res) => {
      context?.$toolUtil.storageSet('userid', res.data.data.id)
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'user') {
        context?.$toolUtil.storageSet('frontName', res.data.data.account)
      }
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'user') {
        context?.$toolUtil.storageSet(
          'headportrait',
          res.data.data.headportrait
        )
      }
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'merchant') {
        context?.$toolUtil.storageSet('frontName', res.data.data.merchantname)
      }
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'merchant') {
        context?.$toolUtil.storageSet('headportrait', res.data.data.picture)
      }
    })
}
init()
</script>
<style lang="scss" scoped>
/* 头部 */
.index_top {
  .index_top_title {
  }
  .index_top_right {
    line-height: 1;
    // 天气时间盒子
    .weather_time_view {
      display: flex;
      align-items: center;
      // 天气城市盒子
      .weather {
        padding: 0 10px;
        flex-direction: row;
        display: flex;
        font-size: 16px;
        line-height: 34px;
        justify-content: center;
        align-items: center;
        // 城市
        .city {
          padding: 0;
          margin: 0 10px 0 0;
        }
        // 天气
        .wea {
        }
      }
      // 时间盒子
      .time {
        padding: 0 20px;
        flex-direction: row;
        display: flex;
        font-size: 16px;
        line-height: 36px;
        justify-content: center;
        align-items: center;
        // 日期
        .date {
          padding: 0;
          margin: 0 10px 0 0;
        }
        // 时间
        .timer {
          padding: 0;
          margin: 0;
        }
      }
    }
  }
  // 登录按钮
  :deep(.el-button--success) {
    border: 0;
    border-radius: 4px;
    padding: 0px;
    margin: 0 20px 0 0;
    color: #fff;
    background: none;
    border-color: none;
    line-height: 34px;
  }
  :deep(.el-button--success:hover) {
    color: #feb043;
    background: none;
    border-color: none;
  }
  // 登出按钮
  :deep(.el-button--danger) {
    border: 0;
    border-radius: 4px;
    padding: 10px;
    color: #fff;
    background: none;
    border-color: none;
  }
  :deep(.el-button--danger:hover) {
    background: none;
    border-color: none;
  }
}
// 轮播盒子
.rotation_view {
}
// 底部
.bottom_view {
  // 图片
  .bottom_img {
    border-radius: 100%;
    object-fit: cover;
    display: none;
    width: 44px;
    height: 44px;
  }
  // 公司
  .bottom_company {
    margin: 10px 0 0;
  }
  // 备案号
  .bottom_record {
    margin: 10px 0 0;
  }
  // 联系方式
  .bottom_desc {
    margin: 10px 0 0;
  }
}

.contain_view {
  margin: 0;
  background: url(http://localhost:8080/agriculturalAssistance/file/948143e7be9e47a5984532b993598280.jpg);
  position: relative;
  height: calc(100% - 60px);
}
.el-aside {
  transition: width 0.15s;
  -webkit-transition: width 0.15s;
  -moz-transition: width 0.15s;
  -webkit-transition: width 0.15s;
  -o-transition: width 0.15s;
}
.el-sub-menu__hide-arrow {
  display: block !important;
}
.menu_scrollbar {
}
// 总盒子
.menu_view {
  // 一级菜单
  :deep(.first-item) {
    .el-sub-menu__title:hover {
      background: none !important;
    }
    // 图标

    // 标题
    .el-sub-menu__title,
    span {
    }

    //箭头
    .el-sub-menu__icon-arrow {
    }
  }
  // 选中
  :deep(.is-active) {
  }

  // 悬浮
  :deep(.first-item:hover) {
  }
}
</style>
<style lang="scss">
// 二级
.menu_popper {
  border: none !important;
  background: none !important;

  // 二级总盒子
  .el-menu--popup {
  }

  // 二级菜单
  .second-item {
  }

  // 选中
  .is-active {
  }

  // 悬浮
  .second-item:hover {
  }
}
</style>
<style>
/* 盒子 */
.index_top {
  width: 100%;
  display: flex;
  height: 166px;
  background: url(http://localhost:8080/agriculturalAssistance/file/840384e9477c40c887173c5adf7d5206.png)
      no-repeat center top / auto 80px,
    url(http://localhost:8080/agriculturalAssistance/file/48f2ae66b6204fbea94ad707d9640a2f.jpg)
      no-repeat center bottom / cover;
  justify-content: space-between;
  padding: 0px calc((100% - 1200px) / 2);
  box-sizing: border-box;
  z-index: 1002;
  position: relative;
  font-size: 14px;
  color: #fff;
}
/* 标题 */
.index_top .index_top_title {
  font-size: 20px;
  color: #333;
  font-weight: 500;
  line-height: 34px;
  margin-top: 100px;
}
/* 右部 盒子 */
.index_top .index_top_right {
  display: flex;
  align-items: flex-start;
}
/* 系统公告 */
.index_top .notice-btn {
  background: none;
  border: 0px solid #dcdfe6;
  color: #fff;
  padding: 0px;
  font-size: 14px;
  border-radius: 0px;
  line-height: 34px;
  margin-right: 20px;
}
.index_top .notice-btn:hover {
  color: #feb043;
}
/* 图标 */
.index_top .notice-btn .iconfont {
  font-size: 14px;
}

/* 客服聊天 */
.index_top .index_top_right .chat {
  line-height: 34px;
  margin-right: 20px;
}
.index_top .index_top_right .chat:hover {
  color: #feb043;
}
.index_top .index_top_right .chat .iconfont {
  font-size: 14px;
}
.index_top .index_top_right .chat span {
  font-size: 14px;
}
/* 购物车 */
.index_top .index_top_right .cart {
  font-size: 14px;
  line-height: 34px;
  margin-right: 20px;
}
.index_top .index_top_right .cart:hover {
  color: #feb043;
}
.index_top .index_top_right .cart .iconfont {
  font-size: 14px;
  margin-right: 5px;
}

/* 用户 盒子 */
.index_top .user {
  display: flex;
  align-items: center;
  height: 34px;
}
.index_top .user .avatar-wrapper {
  display: flex;
  align-items: center;
  cursor: pointer;
  line-height: 34px;
  position: absolute;
  left: calc((100% - 1200px) / 2);
}
/* 头像 */
.index_top .user .avatar-wrapper .user-avatar {
  width: 26px !important;
  height: 26px;
  border-radius: 100%;
  margin-right: 5px;
}
/* 昵称 */
.index_top .user .avatar-wrapper .nickname {
  font-size: 14px;
  color: #fff;
  margin-right: 5px;
}
/* 箭头 */
.index_top .user .avatar-wrapper .el-icon-arrow-down {
}
/* 个人中心 */
.index_top .user .center {
  margin-right: 20px;
}
.index_top .user .center:hover {
  color: #feb043;
}
/* 退出登录 */
.index_top .user .loginOut {
}
.index_top .user .loginOut:hover {
  color: #feb043;
}

/* 用户下拉 盒子 */
.user-dropDown {
  padding: 10px 0;
  margin: 5px 0;
  background: #fff;
  border: 1px solid #e6ebf5;
  border-radius: 4px;
  -webkit-box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.user-dropDown li {
  padding: 0 20px !important;
  line-height: 36px !important;
  font-size: 14px !important;
  color: #606266 !important;
}
.user-dropDown li:hover {
  color: #fff !important;
  background: #feb043 !important;
}
.bottom_view {
  width: 100%;
  background: url(http://localhost:8080/agriculturalAssistance/file/106cdfcfb3a941e5a11f2473cfb982c2.jpg)
      no-repeat center top,
    rgb(4, 62, 24);
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 20px 0px;
  min-height: 120px;
  border-width: 1px 0px 0px;
  border-style: solid;
  border-color: rgb(254, 176, 67);
  font-size: 16px;
  color: #fff;
  line-height: 1.8;
}

/* 总盒子(可滚动容器) */
.menu_scrollbar {
  width: 100%;
  background: url(http://localhost:8080/agriculturalAssistance/file/8496ac37fdc8484d9c080c1da479cce7.jpg)
      no-repeat center bottom,
    rgb(255, 247, 236);
  border-width: 0px;
  border-style: solid;
  border-color: rgb(239, 239, 239);
}
.menu_scrollbar .el-scrollbar__view {
  padding: 10px;
}
/* 菜单盒子 */
.menu_scrollbar .menu_view {
  background: transparent;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  border-bottom: none;
}
/* 一级菜单 item */
.menu_scrollbar .menu_view .el-menu-item,
.menu_scrollbar .menu_view .el-sub-menu,
.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title {
  height: 30px;
  line-height: 30px;
  border: 0px;
  color: #816b0b;
  font-size: 16px;
  padding: 0px 10px;
  cursor: pointer;
  white-space: nowrap;
  list-style: none;
  background: transparent;
  min-width: 80px;
  text-align: center;
}
.menu_scrollbar .menu_view .el-menu-item:hover,
.menu_scrollbar .menu_view .el-sub-menu:hover {
  color: #333333 !important;
}
.menu_scrollbar .menu_view .el-menu-item.is-active,
.menu_scrollbar .menu_view .el-sub-menu.is-active {
  height: 30px;
  line-height: 30px;
  font-size: 16px;
  border-bottom: none;
  color: #333333 !important;
}
/* 图标 */
.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .el-icon-menu,
.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .iconfont {
  vertical-align: middle;
  margin: 0px 3px;
  width: 34px;
  text-align: center;
  font-size: 18px;
  color: inherit;
}
/* 标题 */
.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title span {
  font-size: 16px;
  vertical-align: middle;
  color: inherit;
}
/* 箭头 */
.menu_scrollbar
  .menu_view
  .el-sub-menu
  .el-sub-menu__title
  .el-sub-menu__icon-arrow {
  position: static;
  margin: -3px 0px 0px 8px;
  font-size: 12px;
  vertical-align: middle;
  color: inherit;
}

/* 二级菜单 总盒子 */
.el-menu--horizontal .el-menu {
  border: none;
}
.el-menu--horizontal .el-menu .el-menu-item {
  color: rgb(102, 102, 102);
  height: 40px;
  line-height: 40px;
  padding: 0px 20px;
  background: rgb(255, 255, 255);
}
.el-menu--horizontal .el-menu .el-menu-item:hover {
  color: #fff !important;
  background: rgba(254, 177, 66, 1) !important;
}
.el-menu--horizontal .el-menu .el-menu-item.is-active {
  color: #fff !important;
  background: rgba(254, 177, 66, 1) !important;
}

/*总盒子*/
.rotation_view {
  width: 100%;
  margin: 0px auto;
  background: url(http://localhost:8080/agriculturalAssistance/file/4bc8612ca7e845d89147e79e1facb64e.png)
      repeat-x center top,
    url(http://localhost:8080/agriculturalAssistance/file/4bc8612ca7e845d89147e79e1facb64e.png)
      repeat-x center bottom;
  padding: 10px 0;
}
/*banner盒子*/
.rotation_view .swiper-container {
  width: 100%;
  height: 100%;
}
.rotation_view .swiper .swiper-wrapper {
  transition-duration: 0ms;
  transform: translate3d(0px, 0px, 0px);
  height: 440px;
}
.rotation_view .swiper .swiper-wrapper .swiper-slide {
}
.rotation_view .swiper .swiper-wrapper .swiper-slide img {
  width: 100%;
}
/*左箭头*/
.rotation_view .swiper .swiper-button-prev {
  margin-left: calc((100% - 1200px) / 2);
  color: #feb043;
}
/*右箭头*/
.rotation_view .swiper .swiper-button-next {
  margin-right: calc((100% - 1200px) / 2);
  color: #feb043;
}

/*总盒子*/
.bread_view {
  margin: 10px auto;
  padding: 12px 20px;
  width: 100%;
  background: #fff;
  border-radius: 0px;
  border: 0px solid rgb(239, 239, 239);
  border-bottom: 1px solid #000;
}
/*面包屑盒子*/
.bread_view .el-breadcrumb {
  font-size: 16px;
  line-height: 1;
}
/*分隔符*/
.bread_view .el-breadcrumb .el-breadcrumb__separator {
  margin: 0px 9px;
  font-weight: 500;
  color: rgb(204, 204, 204);
}
/*一级*/
.bread_view .el-breadcrumb .first_breadcrumb span a {
  color: #333;
  display: inline-block;
}
/*二级*/
.bread_view .el-breadcrumb .second_breadcrumb .el-breadcrumb__inner {
  color: rgb(153, 153, 153);
  display: inline-block;
}
</style>

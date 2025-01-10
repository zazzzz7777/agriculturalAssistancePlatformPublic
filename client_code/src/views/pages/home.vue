<template>
  <div>
    <div class="home_box">
      <!-- 帮扶商品信息推荐 -->
      <div class="recomList_view">
        <div class="recomList_title">帮扶商品信息推荐</div>
        <div class="recommend_list_two">
          <mySwiper
            :data="assistanceproductinformationRecomList"
            :type="3"
            :loop="false"
            :navigation="true"
            :pagination="false"
            :paginationType="1"
            :scrollbar="false"
            :slidesPerView="6"
            :spaceBetween="10"
            :autoHeight="false"
            :centeredSlides="false"
            :freeMode="false"
            :effectType="0"
            :direction="horizontal"
            :autoplay="false"
            :slidesPerColumn="1"
          >
            <template #default="scope">
              <div
                class="recommend_item animation_box"
                @click="
                  detailClick('assistanceproductinformation', scope.row.id)
                "
              >
                <div class="recommend_img_box">
                  <img
                    class="recommend_img"
                    v-if="isHttp(scope.row.picture)"
                    :src="scope.row.picture.split(',')[0]"
                    alt=""
                  />
                  <img
                    class="recommend_img"
                    v-else
                    :src="
                      scope.row.picture
                        ? $config.url + scope.row.picture.split(',')[0]
                        : ''
                    "
                    alt=""
                  />
                </div>
                <div class="recommend_content">
                  <div class="recommend_title">
                    {{ scope.row.productname }}
                  </div>
                  <div class="recommend_price">￥{{ scope.row.price }}</div>
                </div>
              </div>
            </template>
          </mySwiper>
        </div>
        <div
          class="recommend_more_view"
          @click="moreClick('assistanceproductinformation')"
        >
          <span class="recommend_more_text">更多</span>
        </div>
      </div>
      <!-- 扶贫资讯推荐 -->
      <div class="recomList_view">
        <div class="recomList_title">扶贫资讯推荐</div>
        <div class="recommend_list_two">
          <mySwiper
            :data="povertyalleviationinformationRecomList"
            :type="3"
            :loop="false"
            :navigation="true"
            :pagination="false"
            :paginationType="1"
            :scrollbar="false"
            :slidesPerView="6"
            :spaceBetween="10"
            :autoHeight="false"
            :centeredSlides="false"
            :freeMode="false"
            :effectType="0"
            :direction="horizontal"
            :autoplay="false"
            :slidesPerColumn="1"
          >
            <template #default="scope">
              <div
                class="recommend_item animation_box"
                @click="
                  detailClick('povertyalleviationinformation', scope.row.id)
                "
              >
                <div class="recommend_img_box">
                  <img
                    class="recommend_img"
                    v-if="isHttp(scope.row.cover)"
                    :src="scope.row.cover.split(',')[0]"
                    alt=""
                  />
                  <img
                    class="recommend_img"
                    v-else
                    :src="
                      scope.row.cover
                        ? $config.url + scope.row.cover.split(',')[0]
                        : ''
                    "
                    alt=""
                  />
                </div>
                <div class="recommend_content">
                  <div class="recommend_title">
                    {{ scope.row.title }}
                  </div>
                  <div class="recommend_title">
                    {{ scope.row.releasetime }}
                  </div>
                </div>
              </div>
            </template>
          </mySwiper>
        </div>
        <div
          class="recommend_more_view"
          @click="moreClick('povertyalleviationinformation')"
        >
          <span class="recommend_more_text">更多</span>
        </div>
      </div>
      <!-- 扶贫资讯首页展示 -->
      <div class="homeList_view">
        <div class="homeList_title">扶贫资讯展示</div>
        <div class="home_list_two">
          <mySwiper
            :data="povertyalleviationinformationHomeList"
            :type="3"
            :loop="false"
            :navigation="true"
            :pagination="false"
            :paginationType="1"
            :scrollbar="false"
            :slidesPerView="4"
            :spaceBetween="10"
            :autoHeight="false"
            :centeredSlides="false"
            :freeMode="false"
            :effectType="0"
            :direction="horizontal"
            :autoplay="true"
            :slidesPerColumn="1"
          >
            <template #default="scope">
              <div
                class="home_item animation_box"
                @click="
                  detailClick('povertyalleviationinformation', scope.row.id)
                "
              >
                <div class="home_img_box">
                  <img
                    class="home_img"
                    v-if="isHttp(scope.row.cover)"
                    :src="scope.row.cover.split(',')[0]"
                    alt=""
                  />
                  <img
                    class="home_img"
                    v-else
                    :src="
                      scope.row.cover
                        ? $config.url + scope.row.cover.split(',')[0]
                        : ''
                    "
                    alt=""
                  />
                </div>
                <div class="home_content">
                  <div class="home_title">
                    {{ scope.row.title }}
                  </div>
                  <div class="home_title">
                    {{ scope.row.releasetime }}
                  </div>
                </div>
              </div>
            </template>
          </mySwiper>
        </div>
        <div
          class="homeList_more_view"
          @click="moreClick('povertyalleviationinformation')"
        >
          <span class="homeList_more_text">更多</span>
        </div>
      </div>
      <!-- 公告信息 -->
      <div class="newsList_view">
        <div class="newsList_title">公告信息</div>
        <div class="news_list_three">
          <div
            class="news_left animation_box"
            v-if="newsList.length > 0"
            @click="newsDetailClick(newsList[0])"
          >
            <div class="news_img_box">
              <img
                class="news_img"
                v-if="isHttp(newsList[0].picture)"
                :src="newsList[0].picture.split(',')[0]"
                alt=""
              />
              <img
                class="news_img"
                v-else
                :src="
                  newsList[0].picture
                    ? $config.url + newsList[0].picture.split(',')[0]
                    : ''
                "
                alt=""
              />
            </div>
            <div class="news_content">
              <div class="news_title">{{ newsList[0].title }}</div>
              <div class="news_text">{{ newsList[0].introduction }}</div>
              <div class="news_time">
                {{ newsList[0].addtime.split(' ')[0] }}
              </div>
            </div>
          </div>
          <div class="news_right">
            <template v-for="(item, index) in newsList" :key="index">
              <div
                class="news_right_item animation_box"
                v-if="index > 0"
                @click="newsDetailClick(item)"
              >
                <div class="news_img_box">
                  <img
                    class="news_img"
                    v-if="isHttp(item.picture)"
                    :src="item.picture.split(',')[0]"
                    alt=""
                  />
                  <img
                    class="news_img"
                    v-else
                    :src="
                      item.picture
                        ? $config.url + item.picture.split(',')[0]
                        : ''
                    "
                    alt=""
                  />
                </div>
                <div class="news_content">
                  <div class="news_title">{{ item.title }}</div>
                  <div class="news_text">{{ item.introduction }}</div>
                  <div class="news_time">{{ item.addtime.split(' ')[0] }}</div>
                </div>
              </div>
            </template>
          </div>
        </div>
        <div class="news_more_view" @click="moreClick('news')">
          <span class="news_more_text">更多</span>
        </div>
      </div>

      <div class="appendBox1"></div>
      <div class="appendBox2"></div>
      <div class="appendBox3"></div>
    </div>
    <formModel ref="newsFormModelRef"></formModel>
  </div>
</template>

<script setup>
import { ref, getCurrentInstance } from 'vue'
import moment from 'moment'
import { useRouter } from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties
const router = useRouter()
//帮扶商品信息推荐
const assistanceproductinformationRecomList = ref([])
const getassistanceproductinformationRecomList = () => {
  let autoSortUrl = 'assistanceproductinformation/autoSort'
  if (context?.$toolUtil.storageGet('frontToken')) {
    autoSortUrl = 'assistanceproductinformation/autoSort2'
  }
  let params = {
    sfsh: '是',
    page: 1,
    limit: 8,
  }
  context
    ?.$http({
      url: autoSortUrl,
      method: 'get',
      params: params,
    })
    .then((res) => {
      assistanceproductinformationRecomList.value = res.data.data.list
      assistanceproductinformationRecomList.value.forEach((item) => {
        if (!isHttp(item.picture)) {
          item.imgUrls = item.picture
            .split(',')
            .map((item) => context.$config.url + item)
        }
      })
    })
}
//扶贫资讯推荐
const povertyalleviationinformationRecomList = ref([])
const getpovertyalleviationinformationRecomList = () => {
  let autoSortUrl = 'povertyalleviationinformation/autoSort'
  let params = {
    page: 1,
    limit: 8,
  }
  context
    ?.$http({
      url: autoSortUrl,
      method: 'get',
      params: params,
    })
    .then((res) => {
      povertyalleviationinformationRecomList.value = res.data.data.list
      povertyalleviationinformationRecomList.value.forEach((item) => {
        if (!isHttp(item.cover)) {
          item.imgUrls = item.cover
            .split(',')
            .map((item) => context.$config.url + item)
        }
      })
    })
}
//扶贫资讯首页展示
const povertyalleviationinformationHomeList = ref([])
const getpovertyalleviationinformationHomeList = () => {
  let params = {
    page: 1,
    limit: 8,
  }
  context
    ?.$http({
      url: 'povertyalleviationinformation/list',
      method: 'get',
      params: params,
    })
    .then((res) => {
      povertyalleviationinformationHomeList.value = res.data.data.list
      povertyalleviationinformationHomeList.value.forEach((item) => {
        if (!isHttp(item.cover)) {
          item.imgUrls = item.cover
            .split(',')
            .map((item) => context.$config.url + item)
        }
      })
    })
}
//公告信息弹窗
import formModel from './news/formModel'
const newsFormModelRef = ref(null)
//公告信息
const newsList = ref([])
const getNewsList = () => {
  context
    ?.$http({
      url: 'news/list',
      method: 'get',
      params: {
        page: 1,
        limit: 8,
      },
    })
    .then((res) => {
      newsList.value = res.data.data.list
    })
}
const newsDetailClick = (item) => {
  if (item && item.id) {
    newsFormModelRef.value.init(item.id)
  }
}
//判断图片链接是否带http
const isHttp = (str) => {
  return str && str.substr(0, 4) == 'http'
}
//跳转详情
const detailClick = (table, id) => {
  router.push(`/index/${table}Detail?id=${id}`)
}
const moreClick = (table) => {
  router.push(`/index/${table}List`)
}
const init = () => {
  //帮扶商品信息推荐
  getassistanceproductinformationRecomList()
  //扶贫资讯推荐
  getpovertyalleviationinformationRecomList()
  //扶贫资讯首页展示
  getpovertyalleviationinformationHomeList()
  //公告信息
  getNewsList()
}
init()
</script>

<style lang="scss">
.home_box {
}

// 推荐
.recomList_view {
  width: 100%;
  position: relative;
  .recomList_title {
  }
  // list
  .recommend_list_two {
    margin: 20px 0 0;
    display: flex;
    width: 100%;
    flex-wrap: wrap;
    .recommend_item {
      cursor: pointer;
      margin: 0 10px;
      background: #fff;
      width: 100%;
      .recommend_img_box {
        width: 100%;
        height: 168px;
        .recommend_img {
          object-fit: cover;
          width: 100%;
          height: 100%;
        }
      }
      .recommend_content {
        .recommend_title {
          overflow: hidden;
          color: #000;
          white-space: nowrap;
          font-weight: 500;
          width: 100%;
          font-size: 14px;
          line-height: 30px;
          text-overflow: ellipsis;
          text-align: center;
        }
        .recommend_price {
          color: #f00;
          display: none;
          width: 100%;
          font-size: 14px;
          text-align: right;
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1.05) skew(0deg, 0deg)
      translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .recommend_more_view {
    cursor: pointer;
    color: #999;
    top: 15px;
    width: auto;
    font-size: 14px;
    position: absolute;
    right: 10px;
    .recommend_more_text {
    }
  }
}
// 推荐
// 新闻资讯
.newsList_view {
  .newsList_title {
  }
  // list
  .news_list_three {
    .news_left {
      .news_img_box {
        .news_img {
        }
      }
      .news_content {
        .news_title {
        }
        .news_text {
        }
        .news_time {
        }
      }
    }
    .news_right {
      .news_right_item {
        .news_img_box {
          .news_img {
          }
        }
        .news_content {
          .news_title {
          }
          .news_text {
          }
          .news_time {
          }
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .news_more_view {
    .news_more_text {
    }
  }
}
// 新闻资讯
// 首页展示
.homeList_view {
  .homeList_title {
  }
  // list
  .home_list_two {
    margin: 20px 0 0;
    display: flex;
    width: 100%;
    flex-wrap: wrap;
    .home_item {
      cursor: pointer;
      margin: 0 10px;
      background: #fff;
      width: 100%;
      .home_img_box {
        width: 100%;
        height: 168px;
        .home_img {
          object-fit: cover;
          width: 100%;
          height: 100%;
        }
      }
      .home_content {
        .home_title {
          overflow: hidden;
          color: #000;
          white-space: nowrap;
          font-weight: 500;
          width: 100%;
          font-size: 14px;
          line-height: 30px;
          text-overflow: ellipsis;
          text-align: center;
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .homeList_more_view {
    cursor: pointer;
    color: #999;
    top: 15px;
    width: auto;
    font-size: 14px;
    position: absolute;
    right: 10px;
    text-align: center;
    .homeList_more_text {
    }
  }
}
// 首页展示
</style>
<style>
/* 总盒子 */
.home_box {
  width: 1200px;
  margin: 0px auto;
  display: flex;
  flex-wrap: wrap;
  padding: 0;
  justify-content: space-between;
  align-items: flex-start;
  background: #fff;
}
/* 附加盒子 */
.home_box .appendBox1 {
  width: 100%;
  height: 1px;
  background: #fff;
  order: 3;
}
.home_box .appendBox2 {
  width: 100%;
  height: 1px;
  background: #fff;
  order: 5;
}
.home_box .appendBox3 {
  width: 100%;
  height: 1px;
  background: #fff;
  order: 8;
}
/* 总盒子 */
.recomList_view {
  width: 100%;
  order: 4;
  padding: 10px;
  background: #fff;
  text-align: center;
}
/* 标题 */
.recomList_view .recomList_title {
  width: 100%;
  display: flex;
  text-align: left;
  align-items: flex-end;
  background: url(http://localhost:8080/agriculturalAssistance/file/f3afd2ab6d99433b90d6909cc88b2350.png)
    no-repeat left top / 30px;
  padding-left: 32px;
  border-bottom: 1px solid #000;
  line-height: 30px;
  font-size: 16px;
  color: #000;
  font-weight: 600;
}

/* 样式一 总盒子 */
.recomList_view .recommend_list_one {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  margin: 20px auto 0;
}
/* item */
.recomList_view .recommend_list_one .recommend_item {
  width: calc(25% - 20px);
  margin: 0px 10px 20px;
  background: rgb(255, 255, 255);
  cursor: pointer;
}
/* 图片 盒子 */
.recomList_view .recommend_list_one .recommend_item .recommend_img_box {
  width: 100%;
  height: 280px;
  overflow: hidden;
  margin: 0px 0px 5px;
}
/* 图片 */
.recomList_view
  .recommend_list_one
  .recommend_item
  .recommend_img_box
  .recommend_img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* 内容区 盒子 */
.recomList_view .recommend_list_one .recommend_item .recommend_content {
  width: 100%;
}
/* 标题 */
.recomList_view
  .recommend_list_one
  .recommend_item
  .recommend_content
  .recommend_title {
  font-size: 16px;
  text-align: center;
  font-weight: 600;
  color: rgb(0, 0, 0);
  width: 100%;
  line-height: 1.5;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
/* 价格 */
.recomList_view
  .recommend_list_one
  .recommend_item
  .recommend_content
  .recommend_price {
  width: 100%;
  padding: 0 10px;
  color: rgb(255, 0, 0);
  text-align: right;
  font-size: 16px;
}

/* 底部 小元素 总盒子 */
.recomList_view
  .recommend_list_one
  .recommend_item
  .recommend_content
  .recommend_bottom {
  width: 100%;
  display: flex;
  align-items: center;
  padding: 0 10px;
}
/* 点赞 盒子 */
.recomList_view
  .recommend_list_one
  .recommend_item
  .recommend_content
  .recommend_bottom
  .recommend_like {
  display: flex;
  align-items: center;
  color: rgb(153, 153, 153);
  font-size: 16px;
  margin: 0px 10px 0px 0px;
}
/* 图标 */
.recomList_view
  .recommend_list_one
  .recommend_item
  .recommend_content
  .recommend_bottom
  .like_icon {
  color: inherit;
  margin: 0px 4px 0px 0px;
}
/* 关注 盒子 */
.recomList_view
  .recommend_list_one
  .recommend_item
  .recommend_content
  .recommend_bottom
  .recommend_collect {
  display: flex;
  align-items: center;
  color: rgb(153, 153, 153);
  font-size: 16px;
  margin: 0px 10px 0px 0px;
}
/* 图标 */
.recomList_view
  .recommend_list_one
  .recommend_item
  .recommend_content
  .recommend_bottom
  .recommend_collect
  .el-icon-star-on {
  color: inherit;
  margin: 0px 4px 0px 0px;
}
/* 数量 */
.recomList_view
  .recommend_list_one
  .recommend_item
  .recommend_content
  .recommend_bottom
  .recommend_collect
  .num {
  color: inherit;
}

/* 总盒子*/
.homeList_view {
  width: calc(100% - 400px);
  order: 6;
  padding: 10px 10px 5px;
  text-align: center;
  background: #fff;
  position: relative;
  flex: 2;
}
/* 标题 */
.homeList_view .homeList_title {
  width: 100%;
  display: flex;
  text-align: left;
  align-items: flex-end;
  background: url(http://localhost:8080/agriculturalAssistance/file/f3afd2ab6d99433b90d6909cc88b2350.png)
    no-repeat left top / 30px;
  padding-left: 32px;
  border-bottom: 1px solid #000;
  line-height: 30px;
  font-size: 16px;
  color: #000;
  font-weight: 600;
}
/* 样式五 盒子 */
.homeList_view .home_list_five {
  width: 1200px;
  display: flex;
  flex-wrap: wrap;
  align-items: flex-start;
  margin: 40px auto 20px;
}
/* item */
.homeList_view .home_list_five div {
  width: calc(25% - 10px);
  margin: 0px 5px 10px;
  overflow: hidden;
}
/* img盒子 */
.homeList_view .home_list_five div .img_box {
  width: 100%;
  height: 280px;
}
.homeList_view .home_list_five div .img_box .home_img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
/* 内容盒子 */
.homeList_view .home_list_five div .homeList_content {
  background: rgb(238, 238, 238);
  padding: 10px;
  display: flex;
  flex-wrap: wrap;
  position: absolute;
  top: 0px;
  left: -100%;
  transition: all 0.3s ease 0s;
}
.homeList_view .home_list_five div .homeList_content div {
  width: 100%;
  text-align: center;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

/* 悬浮 */
.homeList_view .home_list_five div:hover {
  transition: all 0.3s;
  position: relative;
}
.homeList_view .home_list_five div:hover .homeList_content {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.3);
  padding: 0px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  cursor: pointer;
}
.homeList_view .home_list_five div:hover .homeList_content div {
  line-height: 24px;
  color: #fff;
}

/* 总盒子 */
.newsList_view {
  width: 55%;
  order: 2;
  padding: 10px;
  text-align: center;
  background: rgb(255, 255, 255);
  position: relative;
  flex: 1.2;
}
/* 标题 */
.newsList_view .newsList_title {
  width: 100%;
  display: flex;
  text-align: left;
  align-items: flex-end;
  background: url(http://localhost:8080/agriculturalAssistance/file/f3afd2ab6d99433b90d6909cc88b2350.png)
    no-repeat left top / 30px;
  padding-left: 32px;
  border-bottom: 1px solid #000;
  line-height: 30px;
  font-size: 16px;
  color: #000;
  font-weight: 600;
}
/* 样式三盒子 */
.newsList_view .news_list_three {
  display: flex;
  align-items: flex-start;
  width: 100%;
  margin: 20px auto 0;
  justify-content: space-between;
}

/* 左边盒子 */
.newsList_view .news_list_three .news_left {
  width: 220px;
  background: none;
  cursor: pointer;
  position: relative;
  padding-top: 24px;
}
/* 图片盒子 */
.newsList_view .news_list_three .news_left .news_img_box {
  width: 100%;
  padding: 5px;
  border: 1px solid #ddd;
}
/* 图片 */
.newsList_view .news_list_three .news_left .news_img_box .news_img {
  width: 100%;
  height: 115px;
  object-fit: cover;
}
.newsList_view .news_list_three .news_left .news_content {
  display: block;
}
.newsList_view .news_list_three .news_left .news_content .news_title {
  font-size: 14px;
  text-align: center;
  color: #000;
  width: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  position: absolute;
  top: 0px;
  right: 0px;
}
.newsList_view .news_list_three .news_left .news_content .news_text {
  font-size: 14px;
  height: 24px;
  line-height: 24px;
  width: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  color: rgb(0, 102, 0);
  text-decoration: underline;
  text-align: center;
}
.newsList_view .news_list_three .news_left .news_content .news_time {
  color: rgb(153, 153, 153);
  width: 100%;
  text-align: right;
  display: none;
}

/* 右边盒子 */
.newsList_view .news_list_three .news_right {
  width: calc(100% - 240px);
  height: auto;
}
/* item */
.newsList_view .news_list_three .news_right .news_right_item {
  width: 100%;
  cursor: pointer;
  border-bottom: 1px dotted #e2e2e2;
}
.newsList_view .news_list_three .news_right .news_right_item .news_img_box {
  width: 30%;
  display: none;
}
.newsList_view
  .news_list_three
  .news_right
  .news_right_item
  .news_img_box
  .news_img {
  width: 100%;
  object-fit: cover;
  height: 150px;
}
.newsList_view .news_list_three .news_right .news_right_item .news_content {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  line-height: 24px;
}
.newsList_view
  .news_list_three
  .news_right
  .news_right_item
  .news_content
  .news_title {
  width: calc(100% - 110px);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: left;
  font-size: 15px;
  display: inline-block;
  font-family: Microsoft YaHei;
  font-size: 14px;
  color: #666666;
  font-weight: normal;
  text-decoration: none;
  background: url(http://localhost:8080/agriculturalAssistance/file/f411ebff57ca426e9d852ae7a8fc5508.png)
    no-repeat left center;
  padding-left: 20px;
}
.newsList_view
  .news_list_three
  .news_right
  .news_right_item
  .news_content
  .news_text {
  font-size: 14px;
  line-height: 1.5;
  display: none;
}
.newsList_view
  .news_list_three
  .news_right
  .news_right_item
  .news_content
  .news_time {
  width: 110px;
  text-align: right;
  display: inline-block;
  font-size: 14px;
  color: rgb(153, 153, 153);
}
.newsList_view
  .news_list_three
  .news_right
  .news_right_item:hover
  .news_content
  .news_title {
  color: #339933;
}

/* 查看更多 */
.newsList_view .news_more_view {
  width: auto;
  cursor: pointer;
  color: #999;
  position: absolute;
  top: 15px;
  right: 10px;
}
.newsList_view .news_more_view .news_more_text {
}
.newsList_view .news_more_view .el-icon-d-arrow-right {
}
</style>

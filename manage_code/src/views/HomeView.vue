<template>
  <div class="home_view">
    <div class="projectTitle">欢迎使用 {{ projectName }}</div>
    <div class="count_list">
      <el-collapse-transition v-if="btnAuth('orders', '首页总数')">
        <el-card v-show="countTypeList.closeordersCountType" class="card_view">
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">商品订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenordersCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenordersCountType ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closeordersCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenordersCountType"
            >
              <div class="count_title">商品订单总数</div>
              <div class="count_num">{{ ordersCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
    <div class="card_list">
      <el-collapse-transition v-if="btnAuth('orders', '首页统计')">
        <el-card v-show="cardTypeList.closeordersChartType1" class="card_view">
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">商品订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenordersChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenordersChartType1 ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeordersChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div class="card_item" v-show="cardTypeList.hiddenordersChartType1">
              <div
                id="ordersbuynumberEchart1"
                class="Echart"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('orders', '首页统计')">
        <el-card v-show="cardTypeList.closeordersChartType2" class="card_view">
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">商品订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenordersChartType2')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenordersChartType2 ? 'showIcons2' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeordersChartType2')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div class="card_item" v-show="cardTypeList.hiddenordersChartType2">
              <div
                id="ordersbuynumberEchart2"
                class="Echart"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('orders', '首页统计')">
        <el-card v-show="cardTypeList.closeordersChartType3" class="card_view">
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">商品订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenordersChartType3')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenordersChartType3 ? 'showIcons3' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeordersChartType3')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div class="card_item" v-show="cardTypeList.hiddenordersChartType3">
              <div
                id="orderstotalEchart3"
                class="Echart"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('orders', '首页统计')">
        <el-card v-show="cardTypeList.closeordersChartType4" class="card_view">
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">商品订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenordersChartType4')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenordersChartType4 ? 'showIcons4' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeordersChartType4')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div class="card_item" v-show="cardTypeList.hiddenordersChartType4">
              <div
                id="orderstotalEchart4"
                class="Echart"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
  </div>
</template>

<script setup>
import { inject, nextTick, ref, getCurrentInstance } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const projectName = context.$project.projectName
const countTypeList = ref({})
const getCountList = () => {
  countTypeList.value.closeordersCountType = true
  countTypeList.value.hiddenordersCountType = true
  if (btnAuth('orders', '首页总数')) {
    getordersCount()
  }
}
const ordersCount = ref(0)
const getordersCount = () => {
  context
    ?.$http({
      url: 'orders/count',
      method: 'get',
    })
    .then((res) => {
      ordersCount.value = res.data.data
    })
}
const countTypeClick = (e) => {
  countTypeList.value[e] = !countTypeList.value[e]
}
//权限验证
const btnAuth = (e, a) => {
  return context?.$toolUtil.isAuth(e, a)
}
let echarts = inject('echarts')
const cardTypeClick = (e) => {
  cardTypeList.value[e] = !cardTypeList.value[e]
  setTimeout(() => {
    getCardList()
  }, 1000)
}
const cardTypeList = ref({
  closeordersChartType1: true,
  hiddenordersChartType1: true,
  closeordersChartType2: true,
  hiddenordersChartType2: true,
  closeordersChartType3: true,
  hiddenordersChartType3: true,
  closeordersChartType4: true,
  hiddenordersChartType4: true,
})
const getCardList = () => {
  if (btnAuth('orders', '首页统计')) {
    getordersChart1()
  }
  if (btnAuth('orders', '首页统计')) {
    getordersChart2()
  }
  if (btnAuth('orders', '首页统计')) {
    getordersChart3()
  }
  if (btnAuth('orders', '首页统计')) {
    getordersChart4()
  }
}
import '@/assets/js/echarts-theme'
const getordersChart1 = () => {
  nextTick(() => {
    var buynumberEchart1 = echarts.init(
      document.getElementById('ordersbuynumberEchart1'),
      'theme'
    )
    context
      ?.$http({
        url: `orders/value/addtime/buynumber/日`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let dataList = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].addtime)
          yAxis.push(parseFloat(res[i].total))
          dataList.push({
            value: parseFloat(res[i].total),
            name: res[i].addtime,
          })
        }
        var option = {}
        option = {
          title: {
            text: '日销量',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            data: xAxis,
            type: 'category',
          },
          yAxis: {
            type: 'value',
          },
          series: {
            data: yAxis,
            type: 'bar',
          },
        }

        buynumberEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        buynumberEchart1.setOption(option)
        //根据窗口的大小变动图表
        buynumberEchart1.resize()
      })
  })
}
const getordersChart2 = () => {
  nextTick(() => {
    var buynumberEchart2 = echarts.init(
      document.getElementById('ordersbuynumberEchart2'),
      'theme'
    )
    context
      ?.$http({
        url: `orders/value/addtime/buynumber/月`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let dataList = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].addtime)
          yAxis.push(parseFloat(res[i].total))
          dataList.push({
            value: parseFloat(res[i].total),
            name: res[i].addtime,
          })
        }
        var option = {}
        option = {
          title: {
            text: '月销量',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            data: xAxis,
            type: 'category',
          },
          yAxis: {
            type: 'value',
          },
          series: {
            data: yAxis,
            type: 'bar',
          },
        }

        buynumberEchart2.clear()
        // 使用刚指定的配置项和数据显示图表。
        buynumberEchart2.setOption(option)
        //根据窗口的大小变动图表
        buynumberEchart2.resize()
      })
  })
}
const getordersChart3 = () => {
  nextTick(() => {
    var totalEchart3 = echarts.init(
      document.getElementById('orderstotalEchart3'),
      'theme'
    )
    context
      ?.$http({
        url: `orders/value/addtime/total/日`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let dataList = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].addtime)
          yAxis.push(parseFloat(res[i].total))
          dataList.push({
            value: parseFloat(res[i].total),
            name: res[i].addtime,
          })
        }
        var option = {}
        option = {
          title: {
            text: '日销额',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            data: xAxis,
            type: 'category',
          },
          yAxis: {
            type: 'value',
          },
          series: {
            data: yAxis,
            type: 'line',
          },
        }

        totalEchart3.clear()
        // 使用刚指定的配置项和数据显示图表。
        totalEchart3.setOption(option)
        //根据窗口的大小变动图表
        totalEchart3.resize()
      })
  })
}
const getordersChart4 = () => {
  nextTick(() => {
    var totalEchart4 = echarts.init(
      document.getElementById('orderstotalEchart4'),
      'theme'
    )
    context
      ?.$http({
        url: `orders/value/addtime/total/月`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let dataList = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].addtime)
          yAxis.push(parseFloat(res[i].total))
          dataList.push({
            value: parseFloat(res[i].total),
            name: res[i].addtime,
          })
        }
        var option = {}
        option = {
          title: {
            text: '月销额',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            data: xAxis,
            type: 'category',
          },
          yAxis: {
            type: 'value',
          },
          series: {
            data: yAxis,
            type: 'line',
          },
        }

        totalEchart4.clear()
        // 使用刚指定的配置项和数据显示图表。
        totalEchart4.setOption(option)
        //根据窗口的大小变动图表
        totalEchart4.resize()
      })
  })
}

// 日历
const year = ref(0)
const month = ref(0)
const heads = ref(['一', '二', '三', '四', '五', '六', '日'])
const weeks = ref([])
const now = ref(Solar.fromDate(new Date()))
const nextYear = () => {
  year.value = year.value + 1
  weeks.value = render(year.value, month.value)
}
const lastYear = () => {
  year.value = year.value - 1
  weeks.value = render(year.value, month.value)
}
const nextMonth = () => {
  month.value = month.value + 1
  if (month.value > 12) {
    month.value = 1
    year.value = year.value + 1
  }
  weeks.value = render(year.value, month.value)
}
const lastMonth = () => {
  month.value = month.value - 1
  if (month.value < 1) {
    month.value = 12
    year.value = year.value - 1
  }
  weeks.value = render(year.value, month.value)
}
const render = (year, month, weekStart = 1) => {
  let months = SolarMonth.fromYm(year, month)
  let weeks = []

  months.getWeeks(weekStart).forEach((w) => {
    let week = {
      index: 0,
      days: [],
    }
    week.index = w.getIndexInYear()
    let days = []
    w.getDays().forEach((d) => {
      days.push(buildDay(d, month))
    })
    week.days = days
    weeks.push(week)
  })
  return weeks
}
const buildDay = (d, month) => {
  let lunar = d.getLunar()
  let day = {
    day: 0,
    text: '',
    isFestival: false,
    isToday: false,
    isOther: false,
    isHoliday: false,
    isRest: false,
  }
  day.day = d.getDay()
  let text = lunar.getDayInChinese()
  if (1 === d.getDay()) {
    text = lunar.getMonthInChinese() + '月'
  }
  let festivals = d.getFestivals()
  if (festivals.length > 0) {
    text = festivals[0]
    day.isFestival = true
  }
  festivals = lunar.getFestivals()
  if (festivals.length > 0) {
    text = festivals[0]
    day.isFestival = true
  }
  let jq = lunar.getJieQi()
  if (jq) {
    text = jq
    day.isFestival = true
  }
  day.text = text
  if (d.toYmd() === now.value.toYmd()) {
    day.isToday = true
  }
  if (d.getMonth() !== month) {
    day.isOther = true
  }
  let h = HolidayUtil.getHoliday(d.getYear(), d.getMonth(), d.getDay())
  if (h) {
    day.isHoliday = true
    day.isRest = !h.isWork()
  }
  return day
}

year.value = now.value.getYear()
month.value = now.value.getMonth()
weeks.value = render(year.value, month.value)
const init = () => {
  getCountList()
  getCardList()
}
init()
</script>
<style lang="scss">
// 首页盒子
.home_view {
  background-image: url('http://localhost:8080/agriculturalAssistance/file/2ba09c686491481982bda72452d5ca8f.png');
}
.home_view .projectTitle {
}

.showIcons {
  transition: transform 0.3s;
  margin-right: 10px;
}

.showIcons1 {
  transform: rotate(-180deg);
}

// 总数盒子
.count_list {
  // 总数card
  .card_view {
    // card头部
    .el-card__header {
      // 头部盒子
      .index_card_head {
        // 标题
        .card_head_title {
        }
        // 按钮盒子
        .card_head_right {
          // 按钮
          .el-icon {
          }
        }
      }
    }
    // body
    .el-card__body {
      // body盒子
      .count_item {
        // 总数标题
        .count_title {
        }
        // 总数数字
        .count_num {
        }
      }
    }
  }
}

// 统计图盒子
.card_list {
  // 统计图card
  .card_view {
    // 头部
    .el-card__header {
      // 头部盒子
      .index_card_head {
        // 标题
        .card_head_title {
        }
        // 按钮盒子
        .card_head_right {
          // 按钮
          .el-icon {
          }
        }
      }
    }
    // body
    .el-card__body {
      // body盒子
      .card_item {
      }
    }
  }
}
</style>
<style>
/*总盒子*/
.home_view {
  padding: 20px 30px 20px 40px;
  margin: 0px;
  height: auto;
  min-height: 100vh;
  background: url(http://localhost:8080/agriculturalAssistance/file/a82bb73d58204756a20c14c95e44c86f.png)
    no-repeat center top / cover !important;
  width: 100%;
  border-radius: 0px;
  display: flex;
  flex-wrap: wrap;
  align-items: flex-start;
}
.home_view .projectTitle {
  width: 100%;
  font-size: 26px;
  font-weight: bold;
  padding: 40px 0px 20px;
  height: auto;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0px 0px 20px;
  color: rgb(55, 55, 55);
}

/*总数*/
/*总盒子*/
.home_view .count_list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  padding: 0px 0px 20px;
  width: 100%;
  align-items: flex-start;
}
/*卡片盒子*/
.home_view .count_list .card_view {
  width: 23%;
  height: auto;
  margin: 0px 1% 20px;
  box-sizing: border-box;
  border: 0px;
  border-radius: 10px;
  flex: 1;
  padding: 0 1%;
}

.home_view .count_list .card_view:nth-child(1) {
  background: linear-gradient(
    90deg,
    rgba(236, 166, 232, 1) 0%,
    rgba(193, 149, 239, 1) 100%
  );
}
.home_view .count_list .card_view:nth-child(2) {
  background: linear-gradient(
    90deg,
    rgba(160, 217, 252, 1) 0%,
    rgba(135, 174, 245, 1) 100%
  );
}
.home_view .count_list .card_view:nth-child(3) {
  background: linear-gradient(
    90deg,
    rgba(253, 162, 146, 1) 0%,
    rgba(252, 161, 190, 1) 100%
  );
}
.home_view .count_list .card_view:nth-child(4) {
  background: linear-gradient(
    90deg,
    rgba(57, 236, 218, 1) 0%,
    rgba(55, 207, 232, 1) 100%
  );
}
.home_view .count_list .card_view:nth-child(2n + 5) {
  background: linear-gradient(
    90deg,
    rgba(236, 166, 232, 1) 0%,
    rgba(193, 149, 239, 1) 100%
  );
}
.home_view .count_list .card_view:nth-child(2n + 6) {
  background: linear-gradient(
    90deg,
    rgba(160, 217, 252, 1) 0%,
    rgba(135, 174, 245, 1) 100%
  );
}
.home_view .count_list .card_view:nth-child(1):hover {
}
.home_view .count_list .card_view:nth-child(2):hover {
}
.home_view .count_list .card_view:nth-child(3):hover {
}
.home_view .count_list .card_view:nth-child(4):hover {
}
.home_view .count_list .card_view:nth-child(2n + 5):hover {
}
.home_view .count_list .card_view:nth-child(2n + 6):hover {
}

/*head 总盒子*/
.home_view .count_list .card_view .el-card__header {
  width: 100%;
  border: 0px solid rgb(238, 238, 238);
  display: none;
}
/*item*/
.home_view .count_list .card_view .el-card__header .index_card_head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 10px;
}
/*标题*/
.home_view
  .count_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_title {
  font-size: 14px;
  color: rgb(51, 51, 51);
}
/*按钮盒子*/
.home_view
  .count_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_right {
  display: flex;
  align-items: center;
}
/*按钮*/
.home_view
  .count_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_right
  .showIcons {
  color: rgb(102, 102, 102);
  font-size: 20px;
  cursor: pointer;
}
/*body 总盒子*/
.home_view .count_list .card_view .el-card__body {
  padding: 0px;
  background: none;
}
/*item*/
.home_view .count_list .card_view .el-card__body .count_item {
  padding: 10px;
  text-align: left;
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
  flex-direction: column;
}
/*标题*/
.home_view .count_list .card_view .el-card__body .count_item .count_title {
  font-size: 18px;
  color: rgb(0, 0, 0);
  line-height: 2;
  display: inline-block;
  margin: 0px 10px 0px 0px;
  font-weight: 500;
}
/*数字*/
.home_view .count_list .card_view .el-card__body .count_item .count_num {
  width: 100%;
  font-size: 30px;
  color: rgb(255, 255, 255);
  line-height: 2;
  display: inline-block;
  font-weight: 500;
  text-align: center;
}

/*图表*/
/*总盒子*/
.home_view .card_list {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  padding: 0px 0px 20px;
  align-items: flex-start;
  order: 11;
}
/*卡片 总盒子*/
.home_view .card_list .card_view {
  width: 48%;
  height: auto;
  margin: 0px 1% 30px;
  box-sizing: border-box;
  border: 0px solid #ddd;
  border-radius: 0px;
  padding: 20px 0px 0px;
  box-shadow: 0 0px 0px 0 rgba(0, 0, 0, 0.1);
}
/*head 总盒子*/
.home_view .card_list .card_view .el-card__header {
  width: 100%;
  border: 0px solid rgb(238, 238, 238);
  background: rgb(255, 255, 255);
  display: none;
}
/*item*/
.home_view .card_list .card_view .el-card__header .index_card_head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 10px;
}
/*标题*/
.home_view
  .card_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_title {
  color: rgb(33, 33, 33);
}
/*按钮盒子*/
.home_view
  .card_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_right {
  display: none;
}
/*按钮*/
.home_view
  .card_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_right
  .showIcons {
  color: rgb(238, 238, 238);
  font-size: 20px;
  cursor: pointer;
}

/*body 总盒子*/
.home_view .card_list .card_view .el-card__body {
  padding: 0px;
  background: rgb(255, 255, 255);
}
.home_view .card_list .card_view .el-card__body .card_item {
  padding: 10px;
  text-align: center;
}

.home-calendar {
  border: 0px solid rgb(218, 218, 218);
  box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 0px;
  margin: 0px auto 30px;
  border-radius: 0px;
  color: rgb(102, 102, 102);
  background: #fff;
  width: calc(100% - 30px);
  font-size: inherit;
  height: auto;
  padding: 0 0 10px;
  order: 2;
}
.home-calendar .header {
  padding: 17px 40px;
  border-color: rgba(126, 96, 16, 0.1);
  flex-wrap: wrap;
  background: #1043a9;
  border-width: 0px 0px 1px;
  display: flex;
  width: 100%;
  border-style: solid;
  justify-content: space-between;
  height: auto;
}
.home-calendar .header .btn {
  cursor: pointer;
  border: 0px solid rgb(153, 153, 153);
  padding: 0px 0px 0px 12px;
  align-items: center;
  color: #fff;
  border-radius: 4px;
  display: flex;
  width: auto;
  font-size: 16px;
  justify-content: center;
}
.home-calendar .header .title {
  padding: 0px 10px;
  font-size: 24px;
  align-items: center;
  justify-content: center;
  display: flex;
  color: #fff;
}
.home-calendar table {
  width: 100%;
  padding: 0px 0px 20px;
  height: auto;
}
.home-calendar tbody,
thead {
  width: 100%;
}
.home-calendar tr {
  width: 100%;
  align-items: center;
  justify-content: center;
  display: flex;
}
.home-calendar th {
  align-items: center;
  flex: 1;
  display: flex;
  line-height: 50px;
  justify-content: center;
}
.home-calendar td {
  cursor: pointer;
  padding: 6px 12px 6px;
  flex: 1;
  display: flex;
  justify-content: center;
  text-align: center;
}
.home-calendar td.today .text {
  width: 80%;
  height: 80%;
  padding: 5px 0;
  background: #1043a980;
  color: #fff;
  border-radius: 10px;
}
.home-calendar td.festival .text {
  width: 80%;
  height: 80%;
  padding: 5px 0;
  background: #1043a910;
  border-radius: 10px;
}
</style>

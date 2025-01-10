<template>
  <div>
    <el-dialog
      modal-class="edit_form_modal"
      class="edit_form"
      v-model="formVisible"
      :title="formTitle"
      width="80%"
      destroy-on-close
      :fullscreen="false"
    >
      <el-form
        class="formModel_form"
        ref="formRef"
        :model="form"
        :rules="rules"
      >
        <el-row>
          <el-col :span="12">
            <el-form-item label="标题" prop="title">
              <el-input
                class="list_inp"
                v-model="form.title"
                placeholder="标题"
                type="text"
                :readonly="!isAdd || disabledForm.title ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item prop="cover" label="封面">
              <uploads
                :disabled="!isAdd || disabledForm.cover ? true : false"
                action="file/upload"
                tip="请上传封面"
                :limit="3"
                style="width: 100%; text-align: left"
                :fileUrls="form.cover ? form.cover : ''"
                @change="coverUploadSuccess"
              >
              </uploads>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资讯分类" prop="informationclassification">
              <el-select
                class="list_sel"
                :disabled="
                  !isAdd || disabledForm.informationclassification
                    ? true
                    : false
                "
                v-model="form.informationclassification"
                placeholder="请选择资讯分类"
              >
                <el-option
                  v-for="(item, index) in informationclassificationLists"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="发布时间" prop="releasetime">
              <el-date-picker
                class="list_date"
                v-model="form.releasetime"
                format="YYYY-MM-DD HH:mm:ss"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="datetime"
                :readonly="!isAdd || disabledForm.releasetime ? true : false"
                placeholder="请选择发布时间"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="商家名称" prop="merchantname">
              <el-input
                class="list_inp"
                v-model="form.merchantname"
                placeholder="商家名称"
                type="text"
                :readonly="!isAdd || disabledForm.merchantname ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="资讯内容" prop="informationcontent">
              <editor
                :value="form.informationcontent"
                placeholder="请输入资讯内容"
                :readonly="
                  !isAdd || disabledForm.informationcontent ? true : false
                "
                class="list_editor"
                @change="(e) => editorChange(e, 'informationcontent')"
              ></editor>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer v-if="isAdd || type == 'logistics' || type == 'reply'">
        <span class="formModel_btn_box">
          <el-button class="cancel_btn" @click="closeClick">取消</el-button>
          <el-button class="confirm_btn" type="primary" @click="save">
            提交
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
import {
  reactive,
  ref,
  getCurrentInstance,
  nextTick,
  computed,
  defineEmits,
} from 'vue'
import { useStore } from 'vuex'
const store = useStore()
const user = computed(() => store.getters['user/session'])
const context = getCurrentInstance()?.appContext.config.globalProperties
const emit = defineEmits(['formModelChange'])
//基础信息
const tableName = 'povertyalleviationinformation'
const formName = '扶贫资讯'
//基础信息
//form表单
const form = ref({})
const disabledForm = ref({
  title: false,
  cover: false,
  informationclassification: false,
  informationcontent: false,
  releasetime: false,
  merchantname: false,
  thumbsupNumber: false,
  crazilyNumber: false,
  storeupNumber: false,
  discussNumber: false,
  clicktime: false,
  clickNumber: false,
})
const formVisible = ref(false)
const isAdd = ref(false)
const formTitle = ref('')
//表单验证
//匹配整数
const validateIntNumber = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isIntNumer(value)) {
    callback(new Error('请输入整数'))
  } else {
    callback()
  }
}
//匹配数字
const validateNumber = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isNumber(value)) {
    callback(new Error('请输入数字'))
  } else {
    callback()
  }
}
//匹配手机号码
const validateMobile = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isMobile(value)) {
    callback(new Error('请输入正确的手机号码'))
  } else {
    callback()
  }
}
//匹配电话号码
const validatePhone = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isPhone(value)) {
    callback(new Error('请输入正确的电话号码'))
  } else {
    callback()
  }
}
//匹配邮箱
const validateEmail = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isEmail(value)) {
    callback(new Error('请输入正确的邮箱地址'))
  } else {
    callback()
  }
}
//匹配身份证
const validateIdCard = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.checkIdCard(value)) {
    callback(new Error('请输入正确的身份证号码'))
  } else {
    callback()
  }
}
//匹配网站地址
const validateUrl = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isURL(value)) {
    callback(new Error('请输入正确的URL地址'))
  } else {
    callback()
  }
}
const rules = ref({
  title: [{ required: true, message: '请输入', trigger: 'blur' }],
  cover: [],
  informationclassification: [],
  informationcontent: [],
  releasetime: [],
  merchantname: [],
  thumbsupNumber: [{ validator: validateIntNumber, trigger: 'blur' }],
  crazilyNumber: [{ validator: validateIntNumber, trigger: 'blur' }],
  storeupNumber: [{ validator: validateIntNumber, trigger: 'blur' }],
  discussNumber: [{ validator: validateIntNumber, trigger: 'blur' }],
  clicktime: [],
  clickNumber: [{ validator: validateIntNumber, trigger: 'blur' }],
})
//表单验证

const formRef = ref(null)
const id = ref(0)
const type = ref('')
//封面上传回调
const coverUploadSuccess = (e) => {
  form.value.cover = e
}
//资讯分类列表
const informationclassificationLists = ref([])
//methods

//获取唯一标识
const getUUID = () => {
  return new Date().getTime()
}
//重置
const resetForm = () => {
  form.value = {
    title: '',
    cover: '',
    informationclassification: '',
    informationcontent: '',
    releasetime: '',
    merchantname: '',
    clicktime: '',
  }
}
//获取info
const getInfo = () => {
  context
    ?.$http({
      url: `${tableName}/info/${id.value}`,
      method: 'get',
    })
    .then((res) => {
      let reg = new RegExp('../../../file', 'g')
      res.data.data.informationcontent = res.data.data.informationcontent
        ? res.data.data.informationcontent.replace(
            reg,
            '../../../agriculturalAssistance/file'
          )
        : ''
      form.value = res.data.data
      formVisible.value = true
    })
}
const crossRow = ref('')
const crossTable = ref('')
const crossTips = ref('')
const crossColumnName = ref('')
const crossColumnValue = ref('')
//初始化
const init = (
  formId = null,
  formType = 'add',
  formNames = '',
  row = null,
  table = null,
  statusColumnName = null,
  tips = null,
  statusColumnValue = null
) => {
  resetForm()
  form.value.releasetime = context?.$toolUtil.getCurDateTime()
  if (formId) {
    id.value = formId
    type.value = formType
  }
  if (formType == 'add') {
    isAdd.value = true
    formTitle.value = '新增' + formName
    formVisible.value = true
  } else if (formType == 'info') {
    isAdd.value = false
    formTitle.value = '查看' + formName
    getInfo()
  } else if (formType == 'edit') {
    isAdd.value = true
    formTitle.value = '修改' + formName
    getInfo()
  } else if (formType == 'cross') {
    isAdd.value = true
    formTitle.value = formNames
    // getInfo()
    for (let x in row) {
      if (x == 'title') {
        form.value.title = row[x]
        disabledForm.value.title = true
        continue
      }
      if (x == 'cover') {
        form.value.cover = row[x]
        disabledForm.value.cover = true
        continue
      }
      if (x == 'informationclassification') {
        form.value.informationclassification = row[x]
        disabledForm.value.informationclassification = true
        continue
      }
      if (x == 'informationcontent') {
        form.value.informationcontent = row[x]
        disabledForm.value.informationcontent = true
        continue
      }
      if (x == 'releasetime') {
        form.value.releasetime = row[x]
        disabledForm.value.releasetime = true
        continue
      }
      if (x == 'merchantname') {
        form.value.merchantname = row[x]
        disabledForm.value.merchantname = true
        continue
      }
      if (x == 'thumbsupNumber') {
        form.value.thumbsupNumber = row[x]
        disabledForm.value.thumbsupNumber = true
        continue
      }
      if (x == 'crazilyNumber') {
        form.value.crazilyNumber = row[x]
        disabledForm.value.crazilyNumber = true
        continue
      }
      if (x == 'storeupNumber') {
        form.value.storeupNumber = row[x]
        disabledForm.value.storeupNumber = true
        continue
      }
      if (x == 'discussNumber') {
        form.value.discussNumber = row[x]
        disabledForm.value.discussNumber = true
        continue
      }
      if (x == 'clicktime') {
        form.value.clicktime = row[x]
        disabledForm.value.clicktime = true
        continue
      }
      if (x == 'clickNumber') {
        form.value.clickNumber = row[x]
        disabledForm.value.clickNumber = true
        continue
      }
    }
    if (row) {
      crossRow.value = row
    }
    if (table) {
      crossTable.value = table
    }
    if (tips) {
      crossTips.value = tips
    }
    if (statusColumnName) {
      crossColumnName.value = statusColumnName
    }
    if (statusColumnValue) {
      crossColumnValue.value = statusColumnValue
    }
    formVisible.value = true
  }

  context
    ?.$http({
      url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
      method: 'get',
    })
    .then((res) => {
      var json = res.data.data
      if (
        json.hasOwnProperty('merchantname') &&
        context?.$toolUtil.storageGet('role') != '管理员'
      ) {
        form.value.merchantname = json.merchantname
        disabledForm.value.merchantname = true
      }
    })
  context
    ?.$http({
      url: `option/informationclassification/informationclassification`,
      method: 'get',
    })
    .then((res) => {
      informationclassificationLists.value = res.data.data
    })
}
//初始化
//声明父级调用
defineExpose({
  init,
})
//关闭
const closeClick = () => {
  formVisible.value = false
}
//富文本
const editorChange = (e, name) => {
  form.value[name] = e
}
//提交
const save = () => {
  if (form.value.cover != null) {
    form.value.cover = form.value.cover.replace(
      new RegExp(context?.$config.url, 'g'),
      ''
    )
  }
  var table = crossTable.value
  var objcross = JSON.parse(JSON.stringify(crossRow.value))
  let crossUserId = ''
  let crossRefId = ''
  let crossOptNum = ''
  if (type.value == 'cross') {
    if (crossColumnName.value != '') {
      if (!crossColumnName.value.startsWith('[')) {
        for (let o in objcross) {
          if (o == crossColumnName.value) {
            objcross[o] = crossColumnValue.value
          }
        }
        //修改跨表数据
        changeCrossData(objcross)
      } else {
        crossUserId = user.value.id
        crossRefId = objcross['id']
        crossOptNum = crossColumnName.value.replace(/\[/, '').replace(/\]/, '')
      }
    }
  }
  formRef.value.validate((valid) => {
    if (valid) {
      if (crossUserId && crossRefId) {
        form.value.crossuserid = crossUserId
        form.value.crossrefid = crossRefId
        let params = {
          page: 1,
          limit: 1000,
          crossuserid: form.value.crossuserid,
          crossrefid: form.value.crossrefid,
        }
        context
          ?.$http({
            url: `${tableName}/page`,
            method: 'get',
            params: params,
          })
          .then((res) => {
            if (res.data.data.total >= crossOptNum) {
              context?.$toolUtil.message(`${crossTips.value}`, 'error')
              return false
            } else {
              context
                ?.$http({
                  url: `${tableName}/${!form.value.id ? 'save' : 'update'}`,
                  method: 'post',
                  data: form.value,
                })
                .then((res) => {
                  emit('formModelChange')
                  context?.$toolUtil.message(`操作成功`, 'success', () => {
                    formVisible.value = false
                  })
                })
            }
          })
      } else {
        context
          ?.$http({
            url: `${tableName}/${!form.value.id ? 'save' : 'update'}`,
            method: 'post',
            data: form.value,
          })
          .then((res) => {
            emit('formModelChange')
            context?.$toolUtil.message(`操作成功`, 'success', () => {
              formVisible.value = false
            })
          })
      }
    }
  })
}
//修改跨表数据
const changeCrossData = (row) => {
  context
    ?.$http({
      url: `${crossTable.value}/update`,
      method: 'post',
      data: row,
    })
    .then((res) => {})
}
</script>
<style lang="scss" scoped>
// 表单
.formModel_form {
  // form item
  :deep(.el-form-item) {
    //label
    .el-form-item__label {
    }
    // 内容盒子
    .el-form-item__content {
      // 输入框
      .list_inp {
      }
      //日期选择器
      .list_date {
      }
      // 下拉框
      .list_sel {
        //去掉默认样式
        .select-trigger {
          height: 100%;
          .el-input {
            height: 100%;
          }
        }
      }
      // 富文本
      .list_editor {
      }
      //图片上传样式
      .el-upload-list {
        //提示语
        .el-upload__tip {
        }
        //外部盒子
        .el-upload--picture-card {
          //图标
          .el-icon {
          }
        }
        .el-upload-list__item {
        }
      }
    }
  }
}
// 按钮盒子
.formModel_btn_box {
  .cancel_btn {
  }
  .cancel_btn:hover {
  }

  .confirm_btn {
  }
  .confirm_btn:hover {
  }
}
</style>

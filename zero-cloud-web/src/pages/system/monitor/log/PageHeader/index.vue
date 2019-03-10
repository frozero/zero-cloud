<template>
  <el-form
    :inline="true"
    :model="form"
    ref="form"
    size="mini"
    style="margin-bottom: -18px;">

    <el-form-item label="用户名称" prop="username">
      <el-input
        v-model="form.username"
        placeholder="用户名称"
        style="width: 100px;"/>
    </el-form-item>

    <el-form-item label="模块名称" prop="module">
      <el-input
        v-model="form.module"
        placeholder="模块名称"
        style="width: 120px;"/>
    </el-form-item>

    <el-form-item label="时间">
      <el-date-picker
              v-model="timeValue"
              type="daterange"
              range-separator="至"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
              :default-time="['00:00:00', '23:59:59']">
      </el-date-picker>
    </el-form-item>

    <el-form-item>
      <el-button
        type="primary"
        @click="handleFormSubmit">
        <d2-icon name="search"/>
        查询
      </el-button>
    </el-form-item>

    <el-form-item>
      <el-button
        @click="handleFormReset">
        <d2-icon name="refresh"/>
        重置
      </el-button>
    </el-form-item>

  </el-form>
</template>

<script>
export default {
  data () {
    return {
      form: {
        username: '',
        module: ''
      },
      timeValue: ''
    }
  },
  methods: {
    handleFormSubmit () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.timeValue && this.timeValue.length > 1) {
            this.form.beginTime = this.timeValue[0]
            this.form.endTime = this.timeValue[1]
          }
          this.$emit('submit', this.form)
        } else {
          this.$notify.error({
            title: '错误',
            message: '表单校验失败'
          })
          return false
        }
      })
    },
    handleFormReset () {
      this.$refs.form.resetFields()
      this.timeValue = ''
    }
  }
}
</script>

<template>
  <el-form
    :inline="true"
    :model="form"
    ref="form"
    size="mini"
    style="margin-bottom: -18px;">

    <el-form-item label="状态" prop="type">
      <el-select
        v-model="form.enabled"
        placeholder="状态选择"
        style="width: 100px;">
        <el-option label="全部" value=""/>
        <el-option label="正常" value="1"/>
        <el-option label="禁止" value="0"/>
      </el-select>
    </el-form-item>

    <el-form-item label="用户名" prop="username">
      <el-input
        v-model="form.username"
        placeholder="用户名"
        style="width: 100px;"/>
    </el-form-item>

    <el-form-item label="用户昵称" prop="nickname">
      <el-input
        v-model="form.nickname"
        placeholder="用户昵称"
        style="width: 120px;"/>
    </el-form-item>

    <el-form-item label="电话" prop="phone">
      <el-input
        v-model="form.phone"
        placeholder="电话"
        style="width: 120px;"/>
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
        enabled: '',
        username: '',
        nickname: '',
        phone: ''
      }
    }
  },
  methods: {
    handleFormSubmit () {
      this.$refs.form.validate((valid) => {
        if (valid) {
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
    }
  }
}
</script>

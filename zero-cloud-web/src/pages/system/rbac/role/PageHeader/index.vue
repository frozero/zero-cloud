<template>
  <el-form
    :inline="true"
    :model="form"
    ref="form"
    size="mini"
    style="margin-bottom: -18px;">

    <el-form-item label="角色名称" prop="name">
      <el-input
        v-model="form.name"
        placeholder="角色名称"
        style="width: 100px;"/>
    </el-form-item>

    <el-form-item label="角色编码" prop="code">
      <el-input
        v-model="form.code"
        placeholder="角色编码"
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
        name: '',
        code: ''
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

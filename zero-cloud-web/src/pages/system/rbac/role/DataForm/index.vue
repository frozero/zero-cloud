<template>
    <div>
        <el-dialog
                title="角色信息"
                :visible.sync="dialogVisible">

            <el-form ref="form" :model="form" label-width="80px" size="small">
                <input type="hidden" v-model="form.id">

                <el-row>
                    <el-col>
                        <el-form-item prop="name" label="角色名称" :rules="[{ required: true, message: '不能为空'}]">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col>
                        <el-form-item prop="code" label="角色编号" :rules="[{ required: true, message: '不能为空'}]">
                            <el-input v-model="form.code"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-form-item>
                    <el-button type="primary" :loading="loading" @click="save">保存</el-button>
                    <el-button @click="close">取消</el-button>
                </el-form-item>
            </el-form>

        </el-dialog>
    </div>

</template>

<script>
import { saveRole, updateRole } from '@/api/system/role'

export default {
  props: {
    treeData: {}
  },
  data () {
    return {
      loading: false,
      method: '',
      dialogVisible: false,
      popoverVisible: false,
      filterText: '',
      changeOrgName: '',
      form: {}
    }
  },
  watch: {
    form (val) {
      this.$nextTick(() => {
        this.$refs.form.clearValidate()
      })
    }
  },
  methods: {
    save: function () {
      this.$refs.form.validate((valid, error) => {
        if (valid) {
          this.loading = true
          let process
          if (!this.form.id) {
            process = saveRole(this.form)
          } else {
            process = updateRole(this.form)
          }
          process
            .then(res => {
              this.loading = false
              this.dialogVisible = false
              this.$emit('save')
              this.$notify({
                title: '操作成功',
                type: 'success'
              })
            })
            .catch(err => {
              this.loading = false
              console.log('err', err)
            })
        }
      })
    },
    open: function (row, method) {
      this.changeOrgName = ''
      this.method = method
      this.dialogVisible = true
      this.form = Object.assign({}, row)
    },
    close: function () {
      this.loading = false
      this.dialogVisible = false
    }
  }
}
</script>

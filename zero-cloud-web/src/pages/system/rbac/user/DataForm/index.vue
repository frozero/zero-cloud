<template>
    <div>
        <el-dialog
                title="用户信息"
                :visible.sync="dialogVisible">

            <el-form ref="form" :model="form" label-width="80px" size="small">
                <input type="hidden" v-model="form.id">

                <el-form-item prop="username" label="用户账号" :rules="[{ required: true, message: '不能为空'}]">
                    <el-input v-model="form.username"></el-input>
                </el-form-item>

                <el-form-item prop="password" label="密码">
                    <el-input type="password" v-model="form.password" placeholder="请输入密码" show-password="true" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item prop="nickname" label="用户昵称" :rules="[{ required: true, message: '不能为空'}]">
                    <el-input v-model="form.nickname"></el-input>
                </el-form-item>

                <el-row>
                    <el-col :span="12">
                        <el-form-item label="电话">
                            <el-input v-model="form.phone"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="12">
                        <el-form-item label="性别">
                            <el-radio-group v-model="form.sex">
                                <el-radio :label="1">男</el-radio>
                                <el-radio :label="2">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-form-item label="角色">
                    <el-select
                            v-model="form.roleIds"
                            multiple
                            filterable
                            placeholder="请选择" style="width: 100%;">
                        <el-option
                                v-for="item in roles"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" :loading="loading" @click="insert">保存</el-button>
                    <el-button @click="close">取消</el-button>
                </el-form-item>
            </el-form>

        </el-dialog>
    </div>

</template>

<script>
import { getRoleList } from '@api/system/role'
import { saveUser, updateUser } from '@/api/system/user'

export default {
  props: {
    treeData: {}
  },
  data () {
    return {
      loading: false,
      form: {},
      dialogVisible: false,
      popoverVisible: false,
      filterText: '',
      roleIds: [],
      roles: [],
      page: {
        current: 1,
        size: 50,
        total: 0
      }
    }
  },
  created () {
    getRoleList({
      ...this.page
    })
      .then(res => {
        this.roles = res.records
      })
  },
  watch: {
    form (val) {
      this.$nextTick(() => {
        this.$refs.form.clearValidate()
      })
    }
  },
  methods: {
    insert: function () {
      this.$refs.form.validate((valid, error) => {
        if (valid) {
          this.loading = true
          let process
          if (!this.form.id) {
            process = saveUser(this.form)
          } else {
            process = updateUser(this.form)
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
    open: function (row) {
      this.dialogVisible = true
      if (!row.roleIds) {
        row.roleIds = []
      }
      if (row.id) {
        row.password = ''
      }
      this.form = Object.assign({}, row)
    },
    close: function () {
      this.loading = false
      this.dialogVisible = false
    }
  }
}
</script>

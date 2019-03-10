<template>
    <div>
        <el-dialog
                title="权限分配"
                :visible.sync="dialogVisible">

            <el-form ref="form" :model="form" label-width="5px" size="small">
                <input type="hidden" v-model="form.id">

                <el-row style="padding: 0 0 5px 5px;font-weight: bold;">
                    当前角色：{{ form.name }}
                </el-row>

                <el-form-item>
                    <el-input size="mini" placeholder="输入关键字进行过滤" v-model="filterText" style="padding-bottom: 5px;"></el-input>
                    <div>
                        <el-scrollbar class="aooms-scrollbar">
                            <el-tree
                                    ref="tree"
                                    show-checkbox
                                    :default-expanded-keys="[4,5,6,10,11,12,13,14]"
                                    node-key="permissionId"
                                    :data="resourceData"
                                    highlight-current
                                    :filter-node-method="filterNode">

                                <span class="custom-tree-node" slot-scope="{ node, data }">
                                   <span>{{ data.name }}</span>
                                </span>

                            </el-tree>
                        </el-scrollbar>
                    </div>
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
import { getAllResource } from '@api/system/resource'
import { saveRoleResource, getRoleResource } from '@api/system/role'

export default {
  data () {
    return {
      loading: false,
      form: {},
      dialogVisible: false,
      filterText: '',
      resourceIds: [],
      selectResourceIds: [],
      halfSelectResourceIds: [],
      resourceData: []

    }
  },
  watch: {
    form (val) {
      this.$nextTick(() => {
        this.$refs.form.clearValidate()
      })
    },
    filterText (val) {
      this.$refs.tree.filter(val)
    }
  },
  created () {
    var self = this
    getAllResource().then(res => {
      self.resourceData.push(res[0])
    })
  },
  updated () {
    // $('.resource-button').parent().parent().addClass('resource-item')
  },
  methods: {
    insert: function () {
      this.$refs.form.validate((valid, error) => {
        if (valid) {
          var parentArr = this.$refs.tree.getHalfCheckedKeys()
          var childeArr = this.$refs.tree.getCheckedKeys()
          var permissionIds = childeArr.concat(parentArr)

          saveRoleResource(this.form.id, permissionIds)
            .then(res => {
              this.loading = false
              this.dialogVisible = false
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
    open: async function (row) {
      this.dialogVisible = true
      this.form = Object.assign({}, row)

      if (this.$refs.tree) {
        let checkedNodes = this.$refs.tree.getCheckedKeys()
        checkedNodes.forEach(n => {
          if (n) {
            this.$refs.tree.setChecked(n, false)
          }
        })
      }
      await getRoleResource(this.form.id).then(res => {
        if (res) {
          res.forEach(p => {
            this.$refs.tree.setChecked(p.id, true)
          })
        }
      })
    },
    close: function () {
      this.dialogVisible = false
    },
    filterNode (value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    resetTreeNode () {
      let checkedNodes = this.$refs.tree.getCheckedKeys()
      checkedNodes.forEach(n => {
        this.$refs.tree.setChecked(n, false)
      })
    },
    refreshNode (id) {
      this.resetTreeNode()
      getRoleResource(id)
        .then(res => {
          if (res) {
            res.forEach(p => {
              this.$refs.tree.setChecked(p.id, true)
            })
          }
        })
    }
  }
}
</script>

<style>
    .custom-tree-node {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        padding-right: 8px;
    }
</style>

<template>
  <div>

    <div style="padding: 0 0 8px 5px;">
      <el-button type="primary" size="mini" icon="el-icon-plus" :disabled="!canAdd" @click="handleForm()">新增</el-button>
    </div>

    <el-table
      :data="currentTableData"
      v-loading="loading"
      size="mini"
      stripe
      style="width: 100%;"
      @selection-change="handleSelectionChange">

      <el-table-column type="selection" align="center"/>
      <el-table-column label="角色名称" :show-overflow-tooltip="true" align="center" prop="name"/>
      <el-table-column label="角色编码" align="center" prop="code"/>
      <el-table-column label="创建时间" prop="createTime" align="center"/>
      <el-table-column label="更新时间" prop="createTime" align="center"/>

      <el-table-column fixed label="操作" align="center" width="150">
        <template slot-scope="scope">
          <el-button type="primary" title="编辑" size="mini" icon="el-icon-edit" circle :disabled="!canUpdate" @click="handleForm(scope.row,'update')"></el-button>
          <el-button type="danger" title="删除" size="mini" icon="el-icon-delete" circle :disabled="!canDelete || scope.row.id === 1" :loading="scope.row.delLoading" @click="handleDelete(scope.row.id)"></el-button>
          <el-button type="info" title="分配权限" size="mini" icon="el-icon-view" circle :disabled="!canResource" @click="handleResourceAssign(scope.row)"></el-button>
        </template>
      </el-table-column>

    </el-table>

    <!-- 表单弹窗 -->
    <data-form
            @save="tableLoad"
            ref="dataForm">
    </data-form>

    <!-- 权限分配窗口 -->
    <resource-assign
            @tableLoad="tableLoad"
            ref="resourceAssign">
    </resource-assign>
  </div>
</template>

<script>
import DataForm from '../DataForm'
import ResourceAssign from '../ResourceAssign'
import { deleteRole } from '@api/system/role'

export default {
  components: {
    DataForm,
    ResourceAssign
  },
  props: {
    tableData: {
      default: () => []
    },
    loading: {
      default: false
    }
  },
  data () {
    return {
      currentTableData: [],
      multipleSelection: [],
      role: {},
      radioDisabled: false,
      permissionDialogVisible: false,
      canAdd: this.hasPermissions(['system:role:save']),
      canUpdate: this.hasPermissions(['system:role:update']),
      canDelete: this.hasPermissions(['system:role:delete']),
      canResource: this.hasPermissions(['system:role:update'])
    }
  },
  watch: {
    tableData: {
      handler (val) {
        this.currentTableData = val
      },
      immediate: true
    }
  },
  methods: {
    handleEnabledChange (val, index) {
      this.radioDisabled = true
      this.$message({
        message: '正在发送请求',
        type: 'info'
      })
    },
    handleForm: function (row, method) {
      this.$refs.dataForm.open(row, method)
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    handleDelete (id) {
      this.$confirm('确定删除选择的数据?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.loading = true
          deleteRole(id)
            .then(res => {
              this.loading = false
              this.$emit('tableLoad')
              this.$notify({
                title: '删除成功',
                type: 'success'
              })
            })
            .catch(err => {
              console.log('err', err)
              this.loading = false
            })
        })
    },
    handleResourceAssign (row) {
      this.role = row
      // this.permissionDialogVisible = true
      this.$refs.resourceAssign.open(row)
    },
    tableLoad () {
      this.$emit('tableLoad')
    }
  }
}
</script>

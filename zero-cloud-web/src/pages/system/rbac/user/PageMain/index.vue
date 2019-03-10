<template>
  <div>
    <div style="padding-left: 5px;">
      <el-button type="primary" size="mini" icon="el-icon-plus" :disabled="!canAdd" @click="handleForm({sex:1})">新增</el-button>
    </div>

    <el-table
            :data="currentTableData"
            v-loading="loading"
            size="mini"
            stripe
            style="width: 100%;"
            @selection-change="handleSelectionChange">

      <!-- Table 展开行 -->
      <el-table-column type="expand" width="20">
        <template slot-scope="scope">
          <el-form label-position="left" inline class="zero-table-expand">
            <el-form-item label="用户名">
              <span>{{ scope.row.username }}</span>
            </el-form-item>
            <el-form-item label="用户昵称">
              <span>{{ scope.row.nickname }}</span>
            </el-form-item>
            <el-form-item label="性别">
              <span>{{ (scope.row.sex === 1) ? '男':'女' }}</span>
            </el-form-item>
            <el-form-item label="电话">
              <span>{{ scope.row.phone }}</span>
            </el-form-item>
            <el-form-item label="创建时间">
              <span>{{ scope.row.createTime }}</span>
            </el-form-item>
            <el-form-item label="修改时间">
              <span>{{ scope.row.updateTime }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column type="selection" align="center"/>
      <el-table-column label="用户名" :show-overflow-tooltip="true" align="center" prop="username"/>
      <el-table-column label="用户昵称" align="center" prop="nickname"/>
      <el-table-column label="状态" align="center" >
        <template slot-scope="scope">
          <el-switch
                  v-model="scope.row.enabled"
                  :disabled="radioDisabled"
                  active-color="#67C23A"
                  inactive-color="#F56C6C"
                  @change="handleEnabledChange($event,scope.row)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="性别" prop="sex" align="center">
        <template slot-scope="scope">
          {{ (scope.row.sex === 1) ? '男':'女' }}
        </template>
      </el-table-column>
      <el-table-column label="电话" prop="phone"/>
      <el-table-column label="创建时间" prop="createTime" align="center"/>

      <el-table-column fixed label="操作" align="center" width="100">
        <template slot-scope="scope">
          <el-button type="primary" title="编辑" size="mini" icon="el-icon-edit" circle :disabled="!canUpdate || scope.row.id === 1 || scope.row.id === 2" @click="handleForm(scope.row)"></el-button>
          <el-button type="danger" title="删除" size="mini" icon="el-icon-delete" circle :loading="loading" :disabled="!canDelete || scope.row.id === 1 || scope.row.id === 2" @click="handleDelete(scope.row.id)"></el-button>
        </template>
      </el-table-column>

    </el-table>

    <!-- 表单弹窗 -->
    <data-form
            @save="tableLoad"
            ref="dataForm">
    </data-form>
  </div>
</template>

<script>
import { updateEnabled, deleteUser, getUserRoles } from '@/api/system/user'
import util from '@/libs/util.js'
import DataForm from '../DataForm'

export default {
  components: {
    DataForm
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
      radioDisabled: false,
      canAdd: this.hasPermissions(['system:user:save']),
      canUpdate: this.hasPermissions(['system:user:update']),
      canDelete: this.hasPermissions(['system:user:delete'])
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
    handleEnabledChange (val, data) {
      this.radioDisabled = true
      this.$message({
        message: '正在发送请求',
        type: 'info'
      })
      updateEnabled({
        id: data.id,
        enabled: val
      }).then(() => {
        this.$message({
          message: '修改成功',
          type: 'success'
        })
        this.radioDisabled = false
        const oldValue = this.currentTableData[index]
        this.$set(this.currentTableData, index, {
          ...oldValue,
          type: val
        })
      })
    },
    handleDelete (id) {
      this.$confirm('确定删除选择的数据?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.loading = true
          deleteUser(id)
            .then(res => {
              this.loading = false
              this.tableLoad()
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
    handleForm: function (row) {
      if (row.id) {
        getUserRoles(row.id)
          .then(res => {
            row.roleIds = res.map(r => { return r.id })
            this.$refs.dataForm.open(row)
          })
      } else {
        this.$refs.dataForm.open(row)
      }
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    tableLoad () {
      this.$emit('tableLoad')
    }
  }
}
</script>

<style>
  .zero-table-expand {
    font-size: 0;
  }
  .zero-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .zero-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  body .el-table th.gutter{
    display: table-cell!important;
  }
</style>

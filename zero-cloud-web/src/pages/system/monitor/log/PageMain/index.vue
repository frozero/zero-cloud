<template>
  <div>

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
            <el-form-item label="用户名称">
              <span>{{ scope.row.username }}</span>
            </el-form-item>
            <el-form-item label="模块名称">
              <span>{{ scope.row.module }}</span>
            </el-form-item>
            <el-form-item label="参数">
              <span>{{ scope.row.params }}</span>
            </el-form-item>
            <el-form-item label="创建时间">
              <span>{{ scope.row.createTime }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="用户名称" align="center" prop="username" width="200px"/>
      <el-table-column label="模块名称" align="center" prop="module" width="200px"/>
      <el-table-column label="参数" :show-overflow-tooltip="true" align="center" prop="params"/>
      <el-table-column label="创建时间" prop="createTime" align="center" width="200px"/>

    </el-table>
  </div>
</template>

<script>

export default {
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
      radioDisabled: false
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

<template>
  <d2-container>
    <page-header
            slot="header"
            @submit="handleSubmit"
            ref="header"/>
    <page-main
            :table-data="table"
            @tableLoad="tableLoad"
            :loading="loading"/>
    <page-footer
            slot="footer"
            :current="page.current"
            :size="page.size"
            :total="page.total"
            @change="handlePaginationChange"/>
  </d2-container>
</template>

<script>
import { getLogList } from '@api/system/log'
export default {
  // name 值和本页的 $route.name 一致才可以缓存页面
  name: 'system-monitor-log',
  components: {
    'PageHeader': () => import('./PageHeader'),
    'PageMain': () => import('./PageMain'),
    'PageFooter': () => import('./PageFooter')
  },
  data () {
    return {
      table: [],
      loading: false,
      page: {
        current: 1,
        size: 10,
        total: 0
      }
    }
  },
  created () {
    this.tableLoad()
  },
  methods: {
    handlePaginationChange (val) {
      this.page = val
      // nextTick 只是为了优化示例中 notify 的显示
      this.$nextTick(() => {
        this.$refs.header.handleFormSubmit()
      })
    },
    handleSubmit (form) {
      this.tableLoad(form)
    },
    tableLoad (param) {
      param = !param ? {} : param
      this.loading = true
      getLogList({
        ...param,
        ...this.page
      })
        .then(res => {
          this.loading = false
          this.table = res.records
          this.page.total = res.total
        })
        .catch(err => {
          console.log('err', err)
          this.loading = false
        })
    }
  }
}
</script>

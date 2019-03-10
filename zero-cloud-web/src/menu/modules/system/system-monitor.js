export default {
  title: '系统监控',
  icon: 'eye',
  children: (pre => [
    { path: `${pre}/eureka`, title: '服务监控', icon: 'server' },
    { path: `${pre}/swagger`, title: '接口文档', icon: 'file-code-o' },
    { path: `${pre}/zipkin`, title: '服务跟踪', icon: 'sort-amount-asc' },
    { path: `${pre}/elk`, title: '系统日志', icon: 'bars' },
    { path: `${pre}/log`, title: '服务日志', icon: 'book' }
  ])('/system/monitor')
}

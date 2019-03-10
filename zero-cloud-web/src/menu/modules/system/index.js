import rbac from './system-rbac'
import monitor from './system-monitor'

export default {
  path: '/system',
  title: '系统管理',
  icon: 'cog',
  children: [
    rbac,
    monitor
  ]
}

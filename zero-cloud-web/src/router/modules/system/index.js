import layoutHeaderAside from '@/layout/header-aside'

const meta = { auth: true }

export default {
  path: '/system',
  name: 'system',
  meta,
  redirect: { name: 'system-rbac-user' },
  component: layoutHeaderAside,
  children: (pre => [
    // 权限管理
    { path: 'rbac/user', name: `${pre}rbac-user`, component: () => import('@/pages/system/rbac/user'), meta: { ...meta, permission: 'system:user', title: '用户管理' } },
    { path: 'rbac/role', name: `${pre}rbac-role`, component: () => import('@/pages/system/rbac/role'), meta: { ...meta, permission: 'system:role', title: '角色管理' } },
    // 系统监控
    { path: 'monitor/eureka', name: `${pre}monitor-eureka`, component: () => import('@/pages/system/monitor/eureka'), meta: { ...meta, permission: 'system:monitor:eureka', title: '服务监控' } },
    { path: 'monitor/swagger', name: `${pre}monitor-swagger`, component: () => import('@/pages/system/monitor/swagger'), meta: { ...meta, permission: 'system:monitor:interface', title: '接口文档' } },
    { path: 'monitor/zipkin', name: `${pre}monitor-zipkin`, component: () => import('@/pages/system/monitor/zipkin'), meta: { ...meta, permission: 'system:monitor:zipkin', title: '服务跟踪' } },
    { path: 'monitor/elk', name: `${pre}monitor-elk`, component: () => import('@/pages/system/monitor/elk'), meta: { ...meta, permission: 'system:monitor:elk', title: '系统日志' } },
    { path: 'monitor/log', name: `${pre}monitor-log`, component: () => import('@/pages/system/monitor/log'), meta: { ...meta, permission: 'system:monitor:log', title: '服务日志' } }
  ])('system-')
}

export default {
  title: '权限管理',
  icon: 'sitemap',
  children: (pre => [
    { path: `${pre}/user`, title: '用户管理', icon: 'user' },
    { path: `${pre}/role`, title: '角色管理', icon: 'user-circle' }
  ])('/system/rbac')
}

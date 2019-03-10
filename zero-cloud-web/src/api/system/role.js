import request from '@/plugin/axios'
import API from '@/api/global.const'

export function getRoleList (params) {
  return request({
    url: API.USER_API + 'roles',
    method: 'get',
    params
  })
}

export function saveRole (data) {
  return request({
    url: API.USER_API + 'roles',
    method: 'post',
    data
  })
}

export function updateRole (data) {
  return request({
    url: API.USER_API + 'roles',
    method: 'put',
    data
  })
}

export function deleteRole (id) {
  return request({
    url: API.USER_API + 'roles/' + id,
    method: 'delete'
  })
}

export function saveRoleResource (id, data) {
  return request({
    url: API.USER_API + 'roles/' + id + '/permissions',
    method: 'post',
    data
  })
}

export function getRoleResource (id) {
  return request({
    url: API.USER_API + 'roles/' + id + '/permissions',
    method: 'get'
  })
}

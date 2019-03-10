import request from '@/plugin/axios'
import API from '@/api/global.const'

export function getUserList (params) {
  return request({
    url: API.USER_API + 'users',
    method: 'get',
    params
  })
}

export function updateEnabled (data) {
  return request({
    url: API.USER_API + 'users/enabled',
    method: 'put',
    data
  })
}

export function saveUser (data) {
  return request({
    url: API.USER_API + 'users',
    method: 'post',
    data
  })
}

export function updateUser (data) {
  return request({
    url: API.USER_API + 'users',
    method: 'put',
    data
  })
}

export function deleteUser (id) {
  return request({
    url: API.USER_API + 'users/' + id,
    method: 'delete'
  })
}

export function getUserRoles (id) {
  return request({
    url: API.USER_API + 'users/' + id + '/roles',
    method: 'get'
  })
}

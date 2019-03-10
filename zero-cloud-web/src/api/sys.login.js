import request from '@/plugin/axios'
import API from './global.const'
export function AccountLogin (data) {
  return request({
    url: '/sys/login',
    method: 'post',
    data
  })
}

export function getUserInfo (token) {
  return request({
    url: API.USER_API + 'users/current',
    method: 'get'
  })
}

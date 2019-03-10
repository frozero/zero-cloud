import request from '@/plugin/axios'
import API from '@/api/global.const'

export function getAllResource () {
  return request({
    url: API.USER_API + 'menus',
    method: 'get'
  })
}

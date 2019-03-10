import request from '@/plugin/axios'
import API from '@/api/global.const'

export function getLogList (params) {
  return request({
    url: API.LOG_API + 'logs',
    method: 'get',
    params
  })
}

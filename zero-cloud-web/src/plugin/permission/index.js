import store from '@/store'

export default {
  install (Vue, options) {
    Vue.directive('permission', {
      bind: function (el, binding, vnode) {
        let sysAdmin = store.state.d2admin.user.info.sysAdmin
        if (sysAdmin) {
          return
        }
        let checkCodes = []
        if (binding.arg === 'function') {
          checkCodes = store.state.d2admin.user.info.functions
        } else if (binding.arg === 'role') {
          checkCodes = store.state.d2admin.user.info.roles
        } else {
          checkCodes = store.state.d2admin.user.info.permissions
        }
        let access = true
        if (binding.modifiers.all) {
          for (let need of binding.value) {
            if (checkCodes.some(s => s !== need)) {
              access = false
              break
            }
          }
        } else {
          access = false
          for (let need of binding.value) {
            if (checkCodes.some(s => s === need)) {
              access = true
              break
            }
          }
        }
        if (!access) {
          el.parentNode.removeChild(el)
        }
      }
    })
    Vue.prototype.hasPermissions = (permissions) => {
      let sysAdmin = store.state.d2admin.user.info.sysAdmin
      if (sysAdmin) {
        return true
      }
      let has = false
      let checkCodes = store.state.d2admin.user.info.permissions
      for (let need of permissions) {
        if (checkCodes.some(s => s === need)) {
          has = true
          break
        }
      }
      console.log('has==' + has)
      return has
    }
    Vue.prototype.hasFunctions = (functions) => {
      let sysAdmin = store.state.d2admin.user.info.sysAdmin
      if (sysAdmin) {
        return true
      }
      let has = false
      let checkCodes = store.state.d2admin.user.info.functions
      for (let need of functions) {
        if (checkCodes.some(s => s === need)) {
          has = true
          break
        }
      }
      return has
    }
    Vue.prototype.hasRoles = (roles) => {
      let sysAdmin = store.state.d2admin.user.info.sysAdmin
      if (sysAdmin) {
        return true
      }
      let has = false
      let checkCodes = store.state.d2admin.user.info.roles
      for (let need of roles) {
        if (checkCodes.some(s => s === need)) {
          has = true
          break
        }
      }
      return has
    }
  }
}

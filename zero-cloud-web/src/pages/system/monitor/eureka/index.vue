<template>
  <d2-container-frame :src="eurekaUrl" />
</template>

<script>
export default {
  data () {
    return {
      eurekaUrl: 'http://127.0.0.1:9006/'
    }
  }
}
</script>

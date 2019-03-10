<template>
  <d2-container-frame :src="swaggerUrl" />
</template>

<script>
export default {
  data () {
    return {
      swaggerUrl: 'api/swagger-ui.html'
    }
  }
}
</script>

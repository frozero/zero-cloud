<template>
  <d2-container-frame :src="url" />
</template>

<script>
export default {
  data () {
    return {
      url: 'http://127.0.0.1:5601'
    }
  }
}
</script>

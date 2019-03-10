<template>
  <d2-container v-bind="$attrs" :better-scroll="betterScroll">
    <iframe
      class="d2-container-frame"
      :src="src"
      frameborder="0"/>
  </d2-container>
</template>

<script>
export default {
  name: 'd2-container-frame',
  props: {
    src: {
      type: String,
      required: false,
      default: 'https://doc.d2admin.fairyever.com/zh/'
    },
    betterScroll: {
      type: Boolean,
      required: false,
      default: false
    }
  }
}
</script>

<style lang="scss" scoped>
.d2-container-frame {
  position: absolute;
  top: 0px;
  left: 0px;
  height: 100%;
  width: 100%;
}
</style>

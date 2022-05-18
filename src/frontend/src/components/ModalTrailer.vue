<script>
export default {
  name: 'ModalTrailer',
  props: {
    show: Boolean,
    url: String,
  },

  mounted() {console.log(this.url)
  }

}
</script>

<template>
  <Transition name="modal-trailer">
    <div v-if="show" class="video-mask" >
    <div class="video-wrapper" @click="$emit('close')">
      <div class="video-container">
          <iframe :src="this.url" class="video-player"/>
    </div>
    </div>
    </div>

  </Transition>
</template>

<style>

.video-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.video-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.video-container {
  position: relative;
  padding-bottom: 56.25%;
  height: 0;
}

.video-player{
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  min-width: 420px;
  min-height: 240px;
  width: 55%;
  height: 50%;


  display: block;
  margin-left: auto;
  margin-right: auto
}


</style>
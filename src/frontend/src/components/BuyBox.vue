<script>
export default {
  name: 'ModalBox',
  props: {
    show: Boolean,
    id: String,
  }
}
</script>

<template>
  <Transition name="modalBox">
    <div v-if="show" class="modal-mask" >
      <div class="modal-wrapper" @click="$emit('close')">
        <div class="modal-container" @click.stop="">
          <div class="modal-header">
            <slot name="header">Movie Titile</slot>
          </div>

          <div class="modal-body">
            <slot name="body">Details about projection time</slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">

              <table>
                <tr>
                  <th>Tip</th>
                  <th>Pret</th>
                  <th>Numar bilete</th>

                </tr>
                <tr>
                  <td>Child </td>
                  <td>13 Ron</td>
                  <td><input type="number" style="width: 100%"></td>

                </tr>
                <tr>
                  <td>Adult </td>
                  <td>19 Ron</td>
                  <td><input type="number" style="width: 100%"></td>

                </tr>
                <tr>
                  <td>Student </td>
                  <td>15 Ron</td>
                  <td><input type="number" style="width: 100%"></td>

                </tr>
                <tr>
                  <td>Retired </td>
                  <td>10 Ron</td>
                  <td><input type="number" style="width:100%"></td>

                </tr>
              </table>
              <button
                  class="modal-default-button"
                  @click="$emit('close')"
              >OK</button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </Transition>
</template>

<style>
.modal-mask {
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

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
  text-align: center;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
 text-align: center;
  padding:5px 15px;
margin-top:7%;
}

.modal-footer{
  text-align: center;
}

.form-control{
  width:100%;
  max-width: 250px;
  padding:5px 10px;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */


.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
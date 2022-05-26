<template>
  <Transition name="modalBox">


    <div v-if="show" class="modal-mask">
      <div class="modal-wrapper">

        <form id="regForm">

          <div class="tab center-items">
            <div class="modal-container" style="max-width: 300px" @click.stop="">
              <div class="modal-header">
                <slot name="header">Movie Titile</slot>
              </div>
              <div class="modal-body">
                <slot name="body">

                  <select v-model="dateOption" >
                    <option v-for="(timeProjection, dateProjection) in dateList" :key="dateProjection" v-bind:value="dateProjection" @value="dateProjection">
                      {{ dateProjection }}
                    </option>
                  </select>

                  <select v-model="timeOption" v-if="dateOption" >
                    <option v-for="option in dateList[dateOption]" :key="option" v-bind:value="option" >
                      {{option}}
                    </option>
                  </select>

                </slot>
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
                      <td>Child</td>
                      <td>13 Ron</td>
                      <td><input type="number" class="ticket" style="width: 100%" id="child ticket"></td>

                    </tr>
                    <tr>
                      <td>Adult</td>
                      <td>19 Ron</td>
                      <td><input type="number" class="ticket" style="width: 100%" id="adult ticket"></td>

                    </tr>
                    <tr>
                      <td>Student</td>
                      <td>15 Ron</td>
                      <td><input type="number" class="ticket" style="width: 100%" id="student ticket"></td>

                    </tr>
                    <tr>
                      <td>Retired</td>
                      <td>10 Ron</td>
                      <td><input type="number" class="ticket" style="width:100%" id="retired-ticket"></td>

                    </tr>
                  </table>
                </slot>
              </div>
              <div class="modal-default-button" style="overflow:auto;">
                <button type="button" style="float:left;" @click="$emit('close'); this.currentTab = 0" >Close</button>
                <button type="button" style="float:right;" id="prevBtn" v-on:click="nextPrev(-1, this.currentTab)">
                  Previous
                </button>
                <button type="button" style="float:right;" id="nextBtn" v-on:click="nextPrev(1, this.currentTab)"
                        v-show="this.currentTab === 0">
                  Next
                </button>
              </div>
            </div>
          </div>

          <div class="tab center-items">
            <div class="modal-container" style="max-width: 800px">
              <!--CONTENT-->
              <div class="modal-header">
                <slot name="header">Movie Titile</slot>
              </div>

              <movieHall :id="this.id" :date="this.date" :time="this.time"/>

              <div class="modal-default-button" style="overflow:auto;">
                <button type="button" style="float:left;" @click="$emit('close'); this.currentTab = 0" >Close</button>
                <button type="button" style="float:right;" id="prevBtn" v-on:click="nextPrev(-1, this.currentTab) ">
                  Previous
                </button>
                <button type="button" style="float:right;" id="nextBtn" v-on:click="nextPrev(1, this.currentTab)">
                  Next
                </button>
              </div>
            </div>
          </div>

          <div class="tab center-items">
            <div class="modal-container" style="max-width: 1200px">
              <!--CONTENT-->
              <Checkout/>
              <div class="modal-default-button" style="overflow:auto;">
                <button type="button" style="float:left;" @click="$emit('close')">Close</button>
                <button type="button" style="float:right;" id="prevBtn" v-on:click="nextPrev(-1, this.currentTab) ">
                  Previous
                </button>
                <button type="button" style="float:right;" id="nextBtn" v-on:click="nextPrev(1, this.currentTab)">
                  Next
                </button>
              </div>
            </div>
          </div>


        </form>

      </div>
    </div>


  </Transition>

</template>

<script>


import axios from "axios";
import movieHall from "@/components/MovieHall";
import Checkout from "@/components/Checkout";

export default {
  name: 'ModalBox',
  components: {
    movieHall,
    Checkout,
  },
  props:
      ['id', 'show'],
  data() {
    return {
      dateList: [],
      currentTab: 0,
      totalSeats: 0,
      count:0,
      date:"",
      time:"",
    }
  },

  updated() {
    axios.get("/movies/".concat(this.id))
        .then(response => response.data)
        .then((data) => {
          this.dateList = data.projectionDate;

        })
        .catch(error => console.log(error))
    this.showTab(this.currentTab);

  },
  afterUpdate() {
    console.log(this.date)
    console.log(this.time)
  },


  methods: {

    nextPrev(n, tab) {
      let currentTab = tab;
      // This function will figure out which tab to display
      let x = document.getElementsByClassName("tab center-items");
      // Hide the current tab:
      x[currentTab].style.display = "none";
      // Increase or decrease the current tab by 1:
      this.currentTab = currentTab + n;
      currentTab = currentTab + n;

      // if you have reached the end of the form... :
      if (currentTab >= x.length) {
        //...the form gets submitted:
        document.getElementById("regForm").submit();
        return false;
      }
      // Otherwise, display the correct tab:
      this.showTab(currentTab);
    },
    showTab(n) {
      // This function will display the specified tab of the form ...
      let x = document.getElementsByClassName("tab");
      x[n].style.display = "block";
      // ... and fix the Previous/Next buttons:
      if (n === 0) {
        document.getElementById("prevBtn").style.display = "none";
      } else {
        document.getElementById("prevBtn").style.display = "inline";
      }
      if (n === (x.length - 1)) {
        document.getElementById("nextBtn").innerHTML = "Submit";
      } else {
        document.getElementById("nextBtn").innerHTML = "Next";
      }
    },
  }
}

</script>

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
  min-width: 300px;
  max-width: 800px;
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
  padding: 5px 15px;
  margin-top: 7%;
}

.modal-footer {
  text-align: center;
}

.tab {
  display: none;
}

.seat {
  width: 40px;
  height: 40px;
  margin: 5px;
}

.seatOccupied {
  background-color: #F5821E;
}

.seatOpen {
  background-color: #00e50f;
}

.seatDefault {
  background-color: #d6d6d6;
}

.center-items {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
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
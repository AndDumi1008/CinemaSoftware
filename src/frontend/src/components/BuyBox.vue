<script>
import $ from 'jquery'
// import {FormWizard, TabContent} from 'vue-form-wizard'
// import 'vue-form-wizard/dist/vue-form-wizard.min.css'
//component code
import axios from "axios";

export default {
  // components: {
  //   FormWizard,
  //   TabContent,
  // },
  name: 'ModalBox',
  props: {
    show: Boolean,
    id: String,
  },
  data() {
    return{
      date:[],
      reservedSeats:{}

    }
  },

  methods() {

  },

  mounted() {
    axios.get("/movies/".concat(this.id))
        .then(response => response.data)
        .then((data) =>{
          this.date = data.projectionDate;
          this.reservedSeats = data.reservedSeats;
        })
        .catch(error => console.log(error))
  }
}

let totalSeats = 0;

for (let i = 0; i < this.date.length; i++) {

  let year = this.date[i].slice(0, 4)
  let month = this.date[i].slice(5, 7)
  let day = this.date[i].slice(8, 10)
  let time = this.date[i].slice(11, 16)


  console.log(year + "/" + month + "/" + day + " " + time);
}



let btnSelected = [];

function selectedSeat(i, x) {

  if (btnSelected.includes(i + x)) {
    //Remove from array the selected seat
    const btnSelectedIndex = btnSelected.indexOf(i + x);
    if (btnSelectedIndex > -1) {
      btnSelected.splice(btnSelectedIndex, 1); // 2nd parameter means remove one item only
    }
    //Change color to default
    $("#seatNo" + i + x).addClass("seatDefault").removeClass("seatOpen");
  } else {
    //Add seat to array

    btnSelected.push(i + x);
    if (btnSelected.length == totalSeats) {
      $("#nextBtn").removeAttr("disabled").removeClass("disabled");
      $("#ticketsMessage").text("Ai de rezervat toate scaunele. Mergi mai departe")
    }
    else{
      $("#ticketsMessage").text("Mai ai de rezervat "+(totalSeats-btnSelected.length)+ " scaune!")
    }
    //Change color to green
    $("#seatNo" + i + x).addClass("seatOpen").removeClass("seatDefault");
  }
}

var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
  // This function will display the specified tab of the form...
  var x = document.getElementsByClassName("tab");
  x[n].style.display = "block";
  //... and fix the Previous/Next buttons:
  if (n == 0) {
    document.getElementById("prevBtn").style.display = "none";
  } else {
    document.getElementById("prevBtn").style.display = "inline";
  }
  if (n == (x.length - 1)) {
    document.getElementById("nextBtn").innerHTML = "Submit";
  } else {
    document.getElementById("nextBtn").innerHTML = "Next";
  }
  //... and run a function that will display the correct step indicator:
  //fixStepIndicator(n)
}

function nextPrev(n) {
  // This function will figure out which tab to display
  let x = document.getElementsByClassName("tab");
  // Exit the function if any field in the current tab is invalid:
  // if (n == 1 && !validateForm()) return false;
  // Hide the current tab:
  x[currentTab].style.display = "none";
  // Increase or decrease the current tab by 1:
  currentTab = currentTab + n;
  // if you have reached the end of the form...
  if (currentTab >= x.length) {
    // ... the form gets submitted:
    document.getElementById("regForm").submit();
    return false;
  }
  // Otherwise, display the correct tab:
  showTab(currentTab);
  createSeats();
}





function createSeats() {
  if (currentTab == 1) {

    $("#nextBtn").attr("disabled", "disabled").addClass("disabled");
    var inputs = document.getElementsByTagName('input');
    for (let i = 0; i < inputs.length; i++) {
      // deal with inputs[index] element.
      totalSeats += Number(inputs[i].value)
      // totalSeats+=Number($('#'+inputs[0].id).val())
    }
    $("#ticketsMessage").text("Ai de rezervat "+totalSeats+ " scaune!")
    if($("#pageContainer").is(':empty')){
      for (let i = 0; i < 10; i++) {
        let brakeSpace = "</br>";
        for (let x = 0; x < 13; x++) {
          let leftSeats = "<button id='seatNo" + i + x + "' @onclick='selectedSeat(\"" + i + "\",\"" + x +
              "\")'class='seat seatDefault'>" + (x + 1) + "</button>";
          let rightSeats = "<button id='seatNo" + i + x + "' @onclick='selectedSeat(\"" + i + "\",\"" + x +
              "\")'class='seat seatDefault'>" + (x) + "</button>";
          let emptySpace = "<span style='width:50px;margin:25px;'></span>";
          if (x < 6) {
            $("#pageContainer").append(leftSeats);
          } else if (x == 6) {
            $("#pageContainer").append(emptySpace)
          } else {
            $("#pageContainer").append(rightSeats);
          }
        }
        $("#pageContainer").append(brakeSpace);
      }
    }
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

              <div class="tab center-items">
                <table class="table table-bordless text-center">
                  <thead>
                  <tr>
                    <th>Tip</th>
                    <th>Pret</th>
                    <th>Numar de bilete</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                    <td>Child</td>
                    <td>17 Ron</td>
                    <td><input type="number" id="childTickets"></td>
                  </tr>
                  <tr>
                    <td>Student</td>
                    <td>19 Ron</td>
                    <td><input type="number" id="studentTickets"></td>
                  </tr>
                  <tr>
                    <td>Adult</td>
                    <td>21 Ron</td>
                    <td><input type="number" id="adultTickets"></td>
                  </tr>
                  <tr>
                    <td>Retired</td>
                    <td>10 Ron</td>
                    <td><input type="number" id="retiredTickets"></td>
                  </tr>
                  </tbody>
                </table>
              </div>
              <div class="tab center-items">
                <h6 id="ticketsMessage"></h6>
                <div id="pageContainer"></div>
              </div>

              <button type="button" id="nextBtn" @onclick="nextPrev(1)">Next</button>

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
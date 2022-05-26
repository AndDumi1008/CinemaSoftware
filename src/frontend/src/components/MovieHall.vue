<template>
  <canvas id="myCanvas"

          height="425" width="555"/>
</template>

<script>
import axios from "axios";

export default {
  name: "MovieHall",

  props: ['id', 'date', 'time'],

  data() {


    return {
      dateList: [],
      seatList: {},
      elements: elements,
    }
  },

  created() {
    axios.get("/movies/".concat(this.id))
        .then(response => response.data)
        .then((data) => {
          this.dateList = data.projectionDate;
          this.seatList = data.reservedSeats;
          // console.log("its working fine " + this.seatList[0] + " for real")
        })
        .catch(error => console.log(error))
  },

  updated() {
    console.log("date working fine " + this.date + " for real")
    console.log("time working fine " + this.time + " for real")
  },

  mounted() {
    draw()
  },

}


const STATE_GREY = "rgb(103,102,109)";
// const STATE_RED = "rgba(222,3,3,0.63)";
const STATE_GREEN = "rgba(40,245,4,0.79)";

const SEAT_WIDTH = 30;
const SEAT_HEIGHT = 30;

var elements = [];
var selectedSeats = [];
var reservedSeatsIndex = [];


function draw() {
  var seats = [
    {pos: [20, 20], state: STATE_GREY, textPos: [35, 37], text: "1", no: 1},
    {pos: [60, 20], state: STATE_GREY, textPos: [75, 37], text: "2", no:2},
    {pos: [100, 20], state: STATE_GREY, textPos: [115, 37], text: "3", no: 3},
    {pos: [140, 20], state: STATE_GREY, textPos: [155, 37], text: "4", no: 4},
    {pos: [180, 20], state: STATE_GREY, textPos: [195, 37], text: "5", no: 5},
    {pos: [220, 20], state: STATE_GREY, textPos: [235, 37], text: "6", no: 6},
    {pos: [300, 20], state: STATE_GREY, textPos: [315, 37], text: "7", no: 7},
    {pos: [340, 20], state: STATE_GREY, textPos: [355, 37], text: "8", no: 8},
    {pos: [380, 20], state: STATE_GREY, textPos: [395, 37], text: "9", no: 9},
    {pos: [420, 20], state: STATE_GREY, textPos: [435, 37], text: "10", no: 10},
    {pos: [460, 20], state: STATE_GREY, textPos: [475, 37], text: "11", no: 11},
    {pos: [500, 20], state: STATE_GREY, textPos: [515, 37], text: "12", no: 12},

    {pos: [20, 60], state: STATE_GREY, textPos: [35, 77], text: "13", no: 13},
    {pos: [60, 60], state: STATE_GREY, textPos: [75, 77], text: "14", no: 14},
    {pos: [100, 60], state: STATE_GREY, textPos: [115, 77], text: "15", no: 15},
    {pos: [140, 60], state: STATE_GREY, textPos: [155, 77], text: "16", no: 16},
    {pos: [180, 60], state: STATE_GREY, textPos: [195, 77], text: "17", no: 17},
    {pos: [220, 60], state: STATE_GREY, textPos: [235, 77], text: "18", no: 18},
    {pos: [300, 60], state: STATE_GREY, textPos: [315, 77], text: "19", no: 19},
    {pos: [340, 60], state: STATE_GREY, textPos: [355, 77], text: "20", no: 20},
    {pos: [380, 60], state: STATE_GREY, textPos: [395, 77], text: "21", no: 21},
    {pos: [420, 60], state: STATE_GREY, textPos: [435, 77], text: "22", no: 22},
    {pos: [460, 60], state: STATE_GREY, textPos: [475, 77], text: "23", no: 23},
    {pos: [500, 60], state: STATE_GREY, textPos: [515, 77], text: "24", no: 24},

    {pos: [20, 100], state: STATE_GREY, textPos: [35, 117], text: "25", no: 25},
    {pos: [60, 100], state: STATE_GREY, textPos: [75, 117], text: "26", no: 26},
    {pos: [100, 100], state: STATE_GREY, textPos: [115, 117], text: "27", no: 27},
    {pos: [140, 100], state: STATE_GREY, textPos: [155, 117], text: "28", no: 28},
    {pos: [180, 100], state: STATE_GREY, textPos: [195, 117], text: "29", no: 29},
    {pos: [220, 100], state: STATE_GREY, textPos: [235, 117], text: "30", no: 30},
    {pos: [300, 100], state: STATE_GREY, textPos: [315, 117], text: "31", no: 31},
    {pos: [340, 100], state: STATE_GREY, textPos: [355, 117], text: "32", no: 32},
    {pos: [380, 100], state: STATE_GREY, textPos: [395, 117], text: "33", no: 33},
    {pos: [420, 100], state: STATE_GREY, textPos: [435, 117], text: "34", no: 34},
    {pos: [460, 100], state: STATE_GREY, textPos: [475, 117], text: "35", no: 35},
    {pos: [500, 100], state: STATE_GREY, textPos: [515, 117], text: "36", no: 36},

    {pos: [20, 140], state: STATE_GREY, textPos: [35, 157], text: "37", no: 37},
    {pos: [60, 140], state: STATE_GREY, textPos: [75, 157], text: "38", no: 38},
    {pos: [100, 140], state: STATE_GREY, textPos: [115, 157], text: "39", no: 39},
    {pos: [140, 140], state: STATE_GREY, textPos: [155, 157], text: "40", no: 40},
    {pos: [180, 140], state: STATE_GREY, textPos: [195, 157], text: "41", no: 41},
    {pos: [220, 140], state: STATE_GREY, textPos: [235, 157], text: "42", no: 42},
    {pos: [300, 140], state: STATE_GREY, textPos: [315, 157], text: "43", no: 43},
    {pos: [340, 140], state: STATE_GREY, textPos: [355, 157], text: "44", no: 44},
    {pos: [380, 140], state: STATE_GREY, textPos: [395, 157], text: "45", no: 45},
    {pos: [420, 140], state: STATE_GREY, textPos: [435, 157], text: "46", no: 46},
    {pos: [460, 140], state: STATE_GREY, textPos: [475, 157], text: "47", no: 47},
    {pos: [500, 140], state: STATE_GREY, textPos: [515, 157], text: "48", no: 48},

    {pos: [20, 180], state: STATE_GREY, textPos: [35, 197], text: "49", no: 49},
    {pos: [60, 180], state: STATE_GREY, textPos: [75, 197], text: "50", no: 50},
    {pos: [100, 180], state: STATE_GREY, textPos: [115, 197], text: "51", no: 51},
    {pos: [140, 180], state: STATE_GREY, textPos: [155, 197], text: "52", no: 52},
    {pos: [180, 180], state: STATE_GREY, textPos: [195, 197], text: "53", no: 53},
    {pos: [220, 180], state: STATE_GREY, textPos: [235, 197], text: "54", no: 54},
    {pos: [300, 180], state: STATE_GREY, textPos: [315, 197], text: "55", no: 55},
    {pos: [340, 180], state: STATE_GREY, textPos: [355, 197], text: "56", no: 56},
    {pos: [380, 180], state: STATE_GREY, textPos: [395, 197], text: "57", no: 57},
    {pos: [420, 180], state: STATE_GREY, textPos: [435, 197], text: "58", no: 58},
    {pos: [460, 180], state: STATE_GREY, textPos: [475, 197], text: "59", no: 59},
    {pos: [500, 180], state: STATE_GREY, textPos: [515, 197], text: "60", no: 60},

    {pos: [20, 220], state: STATE_GREY, textPos: [35, 237], text: "61", no: 61},
    {pos: [60, 220], state: STATE_GREY, textPos: [75, 237], text: "62", no: 62},
    {pos: [100, 220], state: STATE_GREY, textPos: [115, 237], text: "63", no: 63},
    {pos: [140, 220], state: STATE_GREY, textPos: [155, 237], text: "64", no: 64},
    {pos: [180, 220], state: STATE_GREY, textPos: [195, 237], text: "65", no: 65},
    {pos: [220, 220], state: STATE_GREY, textPos: [235, 237], text: "66", no: 66},
    {pos: [300, 220], state: STATE_GREY, textPos: [315, 237], text: "67", no: 67},
    {pos: [340, 220], state: STATE_GREY, textPos: [355, 237], text: "68", no: 68},
    {pos: [380, 220], state: STATE_GREY, textPos: [395, 237], text: "69", no: 69},
    {pos: [420, 220], state: STATE_GREY, textPos: [435, 237], text: "70", no: 70},
    {pos: [460, 220], state: STATE_GREY, textPos: [475, 237], text: "71", no: 71},
    {pos: [500, 220], state: STATE_GREY, textPos: [515, 237], text: "72", no: 72},

    {pos: [20, 260], state: STATE_GREY, textPos: [35, 277], text: "73", no: 73},
    {pos: [60, 260], state: STATE_GREY, textPos: [75, 277], text: "74", no: 74},
    {pos: [100, 260], state: STATE_GREY, textPos: [115, 277], text: "75", no: 75},
    {pos: [140, 260], state: STATE_GREY, textPos: [155, 277], text: "76", no: 76},
    {pos: [180, 260], state: STATE_GREY, textPos: [195, 277], text: "77", no: 77},
    {pos: [220, 260], state: STATE_GREY, textPos: [235, 277], text: "78", no: 78},
    {pos: [300, 260], state: STATE_GREY, textPos: [315, 277], text: "79", no: 79},
    {pos: [340, 260], state: STATE_GREY, textPos: [355, 277], text: "80", no: 80},
    {pos: [380, 260], state: STATE_GREY, textPos: [395, 277], text: "81", no: 81},
    {pos: [420, 260], state: STATE_GREY, textPos: [435, 277], text: "82", no: 82},
    {pos: [460, 260], state: STATE_GREY, textPos: [475, 277], text: "83", no: 83},
    {pos: [500, 260], state: STATE_GREY, textPos: [515, 277], text: "84", no: 84},

    {pos: [20, 300], state: STATE_GREY, textPos: [35, 317], text: "85", no: 85},
    {pos: [60, 300], state: STATE_GREY, textPos: [75, 317], text: "86", no: 86},
    {pos: [100, 300], state: STATE_GREY, textPos: [115, 317], text: "87", no: 87},
    {pos: [140, 300], state: STATE_GREY, textPos: [155, 317], text: "88", no: 88},
    {pos: [180, 300], state: STATE_GREY, textPos: [195, 317], text: "89", no: 89},
    {pos: [220, 300], state: STATE_GREY, textPos: [235, 317], text: "90", no: 90},
    {pos: [300, 300], state: STATE_GREY, textPos: [315, 317], text: "91", no: 91},
    {pos: [340, 300], state: STATE_GREY, textPos: [355, 317], text: "92", no: 92},
    {pos: [380, 300], state: STATE_GREY, textPos: [395, 317], text: "93", no: 93},
    {pos: [420, 300], state: STATE_GREY, textPos: [435, 317], text: "94", no: 94},
    {pos: [460, 300], state: STATE_GREY, textPos: [475, 317], text: "95", no: 95},
    {pos: [500, 300], state: STATE_GREY, textPos: [515, 317], text: "96", no: 96},

    {pos: [20, 340], state: STATE_GREY, textPos: [35, 357], text: "97", no: 97},
    {pos: [60, 340], state: STATE_GREY, textPos: [75, 357], text: "98", no: 98},
    {pos: [100, 340], state: STATE_GREY, textPos: [115, 357], text: "99", no: 99},
    {pos: [140, 340], state: STATE_GREY, textPos: [155, 357], text: "100", no: 100},
    {pos: [180, 340], state: STATE_GREY, textPos: [195, 357], text: "101", no: 101},
    {pos: [220, 340], state: STATE_GREY, textPos: [235, 357], text: "102", no: 102},
    {pos: [300, 340], state: STATE_GREY, textPos: [315, 357], text: "103", no: 103},
    {pos: [340, 340], state: STATE_GREY, textPos: [355, 357], text: "104", no: 104},
    {pos: [380, 340], state: STATE_GREY, textPos: [395, 357], text: "105", no: 105},
    {pos: [420, 340], state: STATE_GREY, textPos: [435, 357], text: "106", no: 106},
    {pos: [460, 340], state: STATE_GREY, textPos: [475, 357], text: "107", no: 107},
    {pos: [500, 340], state: STATE_GREY, textPos: [515, 357], text: "108", no: 108},

    {pos: [20, 380], state: STATE_GREY, textPos: [35, 397], text: "109", no: 109},  //118 collumns 1
    {pos: [60, 380], state: STATE_GREY, textPos: [75, 397], text: "110", no: 110},  //10 collumns 2
    {pos: [100, 380], state: STATE_GREY, textPos: [115, 397], text: "111", no: 111},  //10 collumns 3
    {pos: [140, 380], state: STATE_GREY, textPos: [155, 397], text: "112", no: 112},  //10 collumns 4
    {pos: [180, 380], state: STATE_GREY, textPos: [195, 397], text: "113", no: 113},  //10 collumns 5
    {pos: [220, 380], state: STATE_GREY, textPos: [235, 397], text: "114", no: 114},  //10 collumns 6
    {pos: [300, 380], state: STATE_GREY, textPos: [315, 397], text: "115", no: 115},  //10 collumns 7
    {pos: [340, 380], state: STATE_GREY, textPos: [355, 397], text: "116", no: 116},  //10 collumns 8
    {pos: [380, 380], state: STATE_GREY, textPos: [395, 397], text: "117", no: 117},  //10 collumns 9
    {pos: [420, 380], state: STATE_GREY, textPos: [435, 397], text: "118", no: 118},  //10 collumns 10
    {pos: [460, 380], state: STATE_GREY, textPos: [475, 397], text: "119", no: 119},  //10 collumns 11
    {pos: [500, 380], state: STATE_GREY, textPos: [515, 397], text: "120", no: 120},  //10 collumns 12
  ]
  var canvas = document.getElementById("myCanvas");
  var ctx = canvas.getContext("2d");

  // Add event listener for `click` events.
  canvas.addEventListener('click', function (event) {
    var x = event.offsetX,
        y = event.offsetY;
    elements.forEach(function (element) {



      if (y > element.pos[1] && y < element.pos[1] + element.height && x > element.pos[0] && x < element.pos[0] + element.width) {

        selectedSeats.push({
          color: STATE_GREEN,
          width: SEAT_WIDTH,
          height: SEAT_HEIGHT,
          pos: element.pos,
          text: element.text,
          textPos: element.textPos,
        })
      }

      selectedSeats.forEach(function (element) {
        ctx.fillStyle = element.color;
        ctx.fillRect(element.pos[0], element.pos[1], element.width, element.height);
        ctx.font = "18px arial";
        ctx.fillStyle = '#333';
        ctx.textBaseline = "middle";
        ctx.textAlign = "center";
        ctx.fillText(element.text, element.textPos[0], element.textPos[1]);
      });
    });
  }, false);

  seats.forEach(function (f) {
    elements.push({
      color: STATE_GREY,
      width: SEAT_WIDTH,
      height: SEAT_HEIGHT,
      pos: f.pos,
      text: f.text,
      textPos: f.textPos,
    });
  })

  elements.forEach(function (element) {
    ctx.fillStyle = element.color;
    ctx.fillRect(element.pos[0], element.pos[1], element.width, element.height);
    ctx.font = "18px arial";
    ctx.fillStyle = '#333';
    ctx.textBaseline = "middle";
    ctx.textAlign = "center";
    ctx.fillText(element.text, element.textPos[0], element.textPos[1]);
  });

  reservedSeatsIndex.forEach(function (element) {
    ctx.fillStyle = element.color;
    ctx.fillRect(element.pos[0], element.pos[1], element.width, element.height);
    ctx.font = "18px arial";
    ctx.fillStyle = '#333';
    ctx.textBaseline = "middle";
    ctx.textAlign = "center";
    ctx.fillText(element.text, element.textPos[0], element.textPos[1]);
  });

}


</script>

<style scoped>

</style>
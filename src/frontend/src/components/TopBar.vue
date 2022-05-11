<template>

  <div class="header">
    <img alt="Cinema logo" src="../assets/Posters/cinema_logo.png" style="width: 500px ">
  </div>

  <div class="icon-bar">
    <a class="active icon-bar-btn margin_btn" href="#"><i class="fa fa-home"></i></a>
    <a class="icon-bar-btn" href="#"><i class="fa fa-search"></i></a>
    <a class="icon-bar-btn" href="#"><i class="fa fa-envelope"></i></a>
    <a class="icon-bar-btn" href="#"><i class="fa fa-group"></i></a>
  </div>

</template>

<script>
export default {
  name: "TopBar"
}
</script>

<style scoped>


div.icon-bar {
  background-color: #555;
  margin: auto;
  overflow: auto;
  width: 100%;

}

.icon-bar a {
  text-align: center;
  transition: all 0.3s ease;
  color: white;
  font-size: xx-large;
  padding: 0 7%;

}

.icon-bar a:hover {
  background-color: #000;
}

.active {
  background-color: #FF8C00;
}

</style>
<template>

  <div class="scrollmenu" >
    <a  v-for="item in movies" :key="item">
      <Movie :id="item-1"> {{item.movies}}</Movie>
    </a>
  </div>

</template>

<script>
import Movie from "@/components/MovieCard";
import axios from "axios";

export default {
  name: "ScrollMenu",


  components: {
    Movie
  },

  data() {
    return {
      movies: 0,
      id:0,
    }
  },

  mounted() {
    axios.get("api/cinema/movies")
        .then(response => response.data)
        .then((data) => {
              this.movies = Object.keys(data).length ;
        }
        )
        .catch(error => console.log(error))
  },
}
</script>

<style scoped>
div.scrollmenu {
  overflow: auto;
  white-space: break-spaces;
}

div.scrollmenu a {
  display: inline-block;
  color: white;
  text-align: center;
  padding: 4%;

}


</style>
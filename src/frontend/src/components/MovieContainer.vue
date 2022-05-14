<template>

  <div class="movie-wrapper" >
    <a  v-for="id in data" :key="id" class="movie-container">
      <Movie :id="id.id"> {{id.movies}}</Movie>
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
      data:{},
    }
  },

  mounted() {
    axios.get("/movies")
        .then(response => {response.data;
          this.data = response.data;
        }
        )
        .then((data) => {
              this.movies = Object.keys(data).length ;
        }
        )
        .catch(error => console.log(error))
  },
}
</script>

<style scoped>
div.movie-wrapper {
  overflow: auto;
  white-space: break-spaces;
}

div.movie-wrapper a {
  display: inline-block;
  color: white;
  text-align: center;
  padding: 4%;

}

</style>
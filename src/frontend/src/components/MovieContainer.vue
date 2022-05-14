<template>

  <div class="scrollmenu" >
    <a  v-for="id in data" :key="id">
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
          console.log(this.data)})
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
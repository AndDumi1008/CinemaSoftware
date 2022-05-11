<template>

  <div class="movie-card">
<!--    <img :src="poster" :alt="title">-->


    <div class="movie-poster-container">
      <a :href="trailer">
        <div class="movie-poster-container">
          <img class="image-responsive-poster" :src="poster" :alt="title">
        </div>
      </a>


      <div class="movie-details">
        <div class="details">
          <h2><b>Title: {{title}}</b></h2>
          <h3><b>Type: {{genre}}</b></h3>
        </div>

        <router-link :to="'/buy?'+title">
          <button class= "button" >
            Buy tickets
          </button>
        </router-link>

      </div>
    </div>
  </div>

</template>

<script>
import axios from "axios";

export default {

  name: 'MovieComponent',

  props:['id'],

  data() {
    return {
      title: '',
      genre: '',
      poster: "",
      trailer: '',
    }
  },

  mounted() {
    axios.get("api/cinema/movies/id=".concat(this.id))
        .then(response => response.data)
        .then((data) =>{
          this.title = data.title;
          this.genre = data.genre;
          this.trailer = data.movieTrailer;
          this.poster = require('../assets/Posters/'.concat(data.posterLocation));
        })
        .catch(error => console.log(error))
  },



  methods: {

    buyButton() {
      location.href = "http://localhost:3000/api/cinema/homepage";
    }

  }
}
</script>


<style scoped>

div.movie-card {
  background-image: linear-gradient(to right, rgba(204, 93, 0, 0.95), #f87300);
  box-shadow: 2px 2px rgba(204, 93, 0, 0.64);/*maybe leave it there*/
  text-align: center;
  border-radius: 16px;
  border: 2px solid #8a4d00;
  width: 100%;
  height: 100%;
}

img {
  margin-top: 1%;
  border-radius: 20px;
  width:280px;
  height:400px;
  transition: 0.4s ease-in;

}
img:hover{
  opacity: 0.5;
}

button {
  outline: 0;
  padding: 4% 6%;
  color: white;
  background-color: #ee9020;
  text-align: center;
  cursor: pointer;
  width: 80%;
  font-size: 18px;
  bottom: 0;

  border-radius: 16px;
  height: 90%;
}
button:hover {
  opacity: 0.6;
}

a {
  cursor: pointer;

}


</style>
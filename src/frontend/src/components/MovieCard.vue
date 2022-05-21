<template>

  <div class="movie-card">
    <div class="card-header">

        <div>
          <img class="image-responsive-poster" :src="poster" :alt="title" @click="showModalTrailer = true" >

          <Teleport to="body" id="trailer">
            <trailer-modal :show="showModalTrailer" @close="showModalTrailer = false" :url="this.trailer">
            </trailer-modal>
          </Teleport>
        </div>


      <div class="movie-details" >

        <div class="details">
          <h2><b> {{title}} </b></h2>
          <h3 class="movie-genre"><b> {{genre}} </b></h3>
        </div>

        <button class= "buy-button" @click="showModal = true" :id="this.id" >
            Buy tickets
        </button>

        <Teleport to="body">
          <modal :show="showModal" @close="showModal = false">
            <template #header>
              <h3> {{ this.title}} </h3>
            </template>
            <template #body>
              <h3> {{ this.date}} </h3>
            </template>
          </modal>
        </Teleport>

      </div>
    </div>
  </div>

</template>

<script>
import axios from "axios";
import Modal from "./BuyBox"
import trailerModal from "./ModalTrailer"
export default {

  name: 'MovieComponent',

  props:['id'],

  components: {
    Modal,
    trailerModal,
  },
  //https://forum.vuejs.org/t/how-to-format-date-for-display/3586

  data() {
    return {
      title: '',
      genre: '',
      poster: "",
      trailer: '',
      date:[],
      showModal: false,
      showModalTrailer: false,
    }
  },
  //TODO: format date to show a table of same day on what hours.= movie will be projected
  mounted() {
    axios.get("/movies/".concat(this.id))
        .then(response => response.data)
        .then((data) =>{
          this.title = data.title;
          this.genre = data.category;
          this.trailer = data.trailerUrl;
          this.poster = data.imageUrl;
          this.date = data.projectionDate;
        })
        .catch(error => console.log(error))
  },



}

</script>


<style scoped>

div.movie-card {
  background-image: linear-gradient(to right, rgba(204, 93, 0, 0.95), #f87300);
  box-shadow: 2px 2px rgba(204, 93, 0, 0.64);/*maybe leave it there*/
  border-radius: 16px;
  border: 2px solid #8a4d00;
  position: relative;
  display: table-cell;

}

img {
  margin-top: 1%;
  border-radius: 20px;
  width:280px;
  height:400px;
  transition: 0.3s ease-in-out;
  margin-left: auto;
  margin-right: auto;

}
img:hover{
  opacity: 0.8;
}

button {
  outline: 0;
  padding: 4% 6%;
  color: white;
  background-color: #ee9020;
  cursor: pointer;
  font-size: 18px;
  margin-left: auto;
  margin-right: auto;
  border-radius: 16px;
}
button:hover {
  opacity: 0.6;
}
.buy-button {
  position:absolute;
  height: 10%;
  width: 80%;
  bottom:2px;
  left: 0;
  right: 0;
}

a {
  cursor: pointer;
}

.movie-genre {
  outline: 0;
  background-color: #7b0dbd;
  text-align: center;
  width: 30%;
  font-size: 14px;
  bottom: 0;
  border-radius: 12px;
  height: 90%;
  margin-left: auto;
  margin-right: auto;
}

.movie-card {
  width: 285px;
  height: 600px;

}

#trailer {
  width: 285px;
  height: 600px;
}

</style>
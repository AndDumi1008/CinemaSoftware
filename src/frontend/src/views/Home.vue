<template>

  <header>
    <TopBar/>
  </header>

  <body style="background-image: linear-gradient(to bottom, #723d11, #000000);">
  <MovieContainer />
  </body>

  <router-view/>
</template>

<script>
import MovieContainer from "@/components/MovieContainer";
import TopBar from "@/components/TopBar";
export default {
  name: 'App',
  components: {
    TopBar,
    MovieContainer,
  },

  data() {
    return {
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

}


body {
  margin: 0;
  padding: 0;
  height: 100%;
}
#content {
  min-height: 100%;
}

/*Header Style*/

.header {
  background-color: black;
  padding: 30px;
  text-align: center;
}


</style>
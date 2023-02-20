<template>
  <div>
    <el-card class="box-card">
      <div style="font-size:30px">
        Congratulations! 
      </div>
      If you want to go back to 
      <router-link to="/home">Main page</router-link>, click here!
      <!-- <div>
        <img src="../assets/congrat.png" class="image">
      </div> -->
    </el-card>
  </div>
</template>

<script>
  export default {
    name: 'IndexPage',
    props: {
    },
    data() {
      return {
      };
    },
    methods:{
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-card {
    width: 30%;
    height: 60%;
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    margin: auto;
    background-image: url("../assets/congrat.png");
        background-repeat: no-repeat;
    /* Do not repeat the image */
    background-size: cover;
    /* Resize the background image to cover the entire container */
  }
</style>

<template>
  <div class="hello">
    <el-card class="box-card" v-if="see">
      <div slot="header" class="clearfix">
        <span style="font-size: 40px">Please enter the password</span>
      </div>
      <!-- <el-form @submit.native.prevent> -->
      <div>
        <input type="password" v-model="input" class="inputarea">
      </div>
      <!-- </el-form> -->
      <div class="buttons">
        <el-button round class="button-confirm" @click="notice()">See Press Requirement</el-button>
      </div>
      <div class="buttons">
        <el-button round class="button-result" @click="checkResult()">Verify</el-button>
      </div>
    </el-card>
    <el-dialog title="Press" :visible.sync="dialogVisible" width="30%" center top="20%">
      <span>{{output}}</span>
      <p></p>
      <!-- <span slot="footer" class="dialog-footer">
        <el-button round @click="seeVerificationResult()">Confirm</el-button>
      </span> -->
    </el-dialog>
    <el-dialog title="Result" :visible.sync="dialogVisibleForResult" width="30%" center top="20%">
      <p></p>
      <span>{{verification}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button round v-if="able" @click="jump()">Confirm</el-button>
      </span>
    </el-dialog>
    <el-card class="con" v-if="jumpTo">
      <div style="font-size:30px">
        Congratulations! 
      </div>
      If you want to go back to 
      <a href @click="jumpback()">Main page</a>, click here!
      <!-- <div>
        <img src="../assets/congrat.png" class="image">
      </div> -->
    </el-card>
  </div>
</template>

<script>
  // import test from '../test.txt'
  import loadFile from '../utils/loadfile'
  export default {
    name: 'HelloWorld',
    props: {
      msg: String
    },
    data() {
      return {
        dialogVisible: false,
        input: '',
        see: true,
        output: null,
        result: false,
        verification: '',
        able: false,
        jumpTo: false,
        dialogVisibleForResult: false
      };
    },
    mounted() {
      this.output = null
    },
    methods: {
      notice() {
        setTimeout(() => {
          this.dialogVisible = true
          this.getdata()
        }, 1000);
      },
      checkResult(){
        this.dialogVisible = false
        this.dialogVisibleForResult = true
        setInterval(this.getVerification(), 500);
      },

      jump() {
        if (this.able) {
          this.see = false
          this.dialogVisible = false
          this.able = false
          this.jumpTo = true
          this.dialogVisibleForResult = false
          // this.$router.push({
          //   name: 'IndexPage',
          //   path: './indexPage.vue'
          // })
        } else {
          this.dialogVisible = false
        }
      },
      jumpback(){
        this.jumpTo = false
        this.see = true
        this.able = false
      },
      // seeVerificationResult(){
      //   this.dialogVisible = false
      //   this.dialogVisibleForResult = true
      //   setInterval(this.getVerification(), 500);
      // },
      getVerification() {
        var temp = loadFile("./verification.txt")
        this.verification = temp
        if (temp == "pass The verification") {
          this.able = true
        } else {
          this.able = false
        }
      },
      getdata() {
        // setTimeout(() => {
        var res = loadFile("./test.txt")
        console.log(res)
        if (res) {
          this.output = res
          // setInterval(this.getVerification(), 500);
        } else {
          this.output = "no results currently"
        }
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-card {
    width: 60%;
    height: 60%;
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    margin: auto;
  }
  .inputarea {
    width: 800px;
    height: 100px;
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    margin: auto;
    font-size: 100px;
  }
  
  .con {
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
  /* .inner{
                height: 300px;
              } */
  /* .el-input--suffix .el-input__inner {
                padding-right: 30px;
                height: 300px;
              }
              input.el-input__inner {
                  height: 300px;
                  padding: 100px;
              } */
  .buttons{
    padding-top: 200px;
  }
  .button-confirm {
    position: absolute;
    bottom: 5%;
    left:15%;
    font-size: 40px;
    
  }
  .button-result {
    position: absolute;
    bottom: 5%;
    right: 15%;
    font-size: 40px;
  }
  .el-dialog {
    height: 50%;
  }
  h3 {
    margin: 40px 0 0;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
</style>

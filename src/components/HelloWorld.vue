<template>
  <div class="hello">
    <el-card class="box-card" v-if="see">
      <div slot="header" class="clearfix">
        <span style="font-size: 40px">Please enter the password</span>
      </div>
      <el-form @submit.native.prevent>
        <div>
          <!-- <input class="inputarea" @keyup.enter.native="notice2"> -->
          <!-- <input class="inputarea" @submit.native.prevent="notice2"> -->
          <el-input type="password" v-model="input" class="inputarea"></el-input>
          <!-- <el-input type="text" v-model="input" placeholder="" show-password class="inputarea" :rows="2" @keyup.enter.native="notice2"></el-input> -->
          <!-- <el-input v-model="input" placeholder="enter here" show-password @change="notice()" @keyup.enter.native="notice()"></el-input> -->
        </div>
      </el-form>
      <div>
        <el-button round class="button-confirm" @click="notice()">Confirm</el-button>
      </div>
    </el-card>
    <el-dialog title="Press" :visible.sync="dialogVisible" width="30%" center top="20%">
      <span>{{output}}</span>
      <span slot="footer" class="dialog-footer">
                                          <el-button round @click="jump()">Confirm</el-button>
                                        </span>
    </el-dialog>
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
        timer: ''
      };
    },
    mounted() {
      this.output = null
    },
    methods: {
      notice() {
        setTimeout(() => {
          debugger
          console.log(this.output)
          this.dialogVisible = true
          this.getdata()
        }, 1000);
        // console.log(this.output)
        // this.dialogVisible = true
        // this.getdata()
      },
      notice2() {
        console.log(this.output)
        this.dialogVisible = true
        debugger
        this.getdata()
      },
      jump() {
        if (this.result) {
          this.see = false
          this.dialogVisible = false
          this.output = null
          console.log(this.output)
          this.$router.push({
            name: 'IndexPage',
            path: './indexPage.vue'
          })
        }
      },
      // getdata() {
      //   console.log(test)
      // },
      getdata() {
        var res = loadFile("./test.txt")
        console.log(res)
        this.output = res
        if (this.output) {
          this.result = true
        }
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-card {
    opacity: 0.98;
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
  .button-confirm {
    position: absolute;
    bottom: 5%;
    left: 45%;
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

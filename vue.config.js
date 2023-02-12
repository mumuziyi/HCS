const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

// const path = require('path');
// function resolve(dir) {
//   return path.join(__dirname, dir)
// }

// module.exports = {
//   context: path.resolve(__dirname, './'),
//   resolve: {
//     extensions: ['.js', '.vue', '.json'],
//     alias: {
//       '@': resolve('src'),
//       '@views': resolve('src/views'),
//       '@comp': resolve('src/components'),
//       '@core': resolve('src/core'),
//       '@utils': resolve('src/utils')
//     }
//   },
// }
module.exports = {
  //...
  chainWebpack: config => {
    config.module
      .rule('raw')
      .test(/\.txt$/)
      .use('raw-loader')
      .loader('raw-loader')
      .end()
  },
}
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // devServer: {
  //   proxy: {
  //     '/api': {
  //       target: 'http://localhost:9090',
  //       changeOrigin: true,
  //       pathRewrite: {
  //         '^/api': '/'
  //       }
  //     }
  //   }
  // }


  chainWebpack: config => {
    config
        .plugin('html')
        .tap(args => {
          args[0].title = "德晟股份";
          return args
        })
  }

})

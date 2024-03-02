import Vue from 'vue'
import Vuex from 'vuex'
import router  from "@/router";
import request from "@/utils/request";
import ElementUI from "element-ui";
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    hasRoutes:false,
    uploadHeaders: localStorage.getItem("token") ? JSON.parse(localStorage.getItem("token")) : '',
    avatarUrl: localStorage.getItem("user") === null ? "" : JSON.parse(localStorage.getItem("user" || "[]")).avatarUrl
  },
  getters: {
  },
  mutations: {
    changeAvatarUrl() {
      let user = JSON.parse(localStorage.getItem("user"))
      console.log(this.state.avatarUrl)
      console.log(user.avatarUrl)
      this.state.avatarUrl = user.avatarUrl
    },
    logout() {
      request.post("/logout").then(res=>{
        if(res.code===200){
          ElementUI.Message({
            message: "退出成功",
            type: 'info'
          });
          //清空缓存
          localStorage.clear()
        }
      })
      router.replace("/login")
    },
    SET_ROUTES_STATE:(state,hasRoutes)=>{
      state.hasRoutes=hasRoutes
    },
  },
  actions: {
  },
  modules: {
  }
})

import axios from 'axios'
import router from "@/router";
import ElementUI from "element-ui";

const request = axios.create({
    baseURL: `http://192.168.20.215:9292`,
    timeout: 30000,
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : ""
    let token = localStorage.getItem("token") ? JSON.parse(localStorage.getItem("token")) : ""
    if (user) {
        config.headers['token'] = token;  // 设置请求头
    }
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.headers === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
            console.log(res)
        }
        let refreshtoken = response.headers.refreshtoken
        if(refreshtoken){
            console.log(refreshtoken)
        }
        if(refreshtoken!==undefined&&refreshtoken!==""){
            localStorage.setItem("token",JSON.stringify(refreshtoken))
            console.log("token续期成功")
        }

        if (res.code === 401||res.code===403) {
            ElementUI.Message({
                message: res.msg,
                type: 'error'
            });
        }

        if (res.code === 4001) {
            ElementUI.Message({
                message: res.msg,
                type: 'error'
            });
            localStorage.clear()
            router.replace("/login")
        }
        return res;
    },
    error => {
        console.log(error)
        let res = JSON.parse(error.request.response)
        console.log('err' + error) // for debug
        ElementUI.Message({
            message: res.msg,
            type: 'error'
        });
        // if(res.code===401){
        //     router.replace("/login")
        // }
        return Promise.reject(error)
    }
)


export default request


import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store"
Vue.use(VueRouter)

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
// 在index.js 里面写入下面的代码即可解决
const originalReplace = VueRouter.prototype.replace;
VueRouter.prototype.replace = function replace(location) {
    return originalReplace.call(this, location).catch(err => err);
};

const routes = [
    {
        path: '/login-bak',
        name: 'Login-bak',
        component: () => import("@/views/Login-bak.vue")
    },
    {
        path: '/register-bak',
        name: 'Register-bak',
        component: () => import("@/views/Register-bak.vue")
    },
    /*{
        path: '/forgotPassword',
        name: 'ForgotPassword',
        component: () => import("@/components/ForgotPassword-2.vue")
    },*/
    /*{
        path: '/test',
        name: 'Test',
        component: () => import("@/components/Test.vue")
    },*/
    {
        path: '/basic',
        name: 'Basic',
        component: () => import("@/components/Basic.vue")
    },
    {
        path: '/forgotPassword',
        name: 'ForgotPassword',
        component: () => import("@/components/ForgotPassword.vue")
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import("@/components/Register.vue")
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import("@/components/Login.vue")
    },
    /*{
        path: '/login-test',
        name: 'Login-test',
        component: () => import("@/components/Login-test.vue")
    },*/
    {
        path: '*',
        name: '404',
        component: () => import('@/views/404.vue')
    }/*,
    {
        path: '/success',
        name: 'Success',
        component: () => import('@/views/Success.vue')
    }*/
]

const router = new VueRouter({
    mode: 'history',
    routes
})

// 提供一个重置路由的方法
export const resetRouter = () => {
    router.matcher = new VueRouter({
        mode: 'history',
        base: process.env.BASE_URL,
        routes
    })
}

// 检查是否存在于免登陆白名单
function inWhiteList(toPath) {
    const whiteList = ['/login', '/register', '/forgotPassword', '/404', '/basic'/*, '/basic2', '/basic3', '/basic4', '/test', '/login-test'*/]
    const path = whiteList.find((value) => {
        // 使用正则匹配
        const reg = new RegExp('^' + value)
        return reg.test(toPath)
    })
    return !!path
}

export const setRoutes = () => {
    const storeMenus = localStorage.getItem("menus")
    if (storeMenus) {
        // 拼装动态路由
        const manageRoute = {
            path: '/', name: 'Manage', component: () => import('../views/Manage.vue'), redirect: "/home", children: [
                {path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
                {path: 'password', name: '修改密码', component: () => import('../views/Password.vue')}
            ]
        }
        const menus = JSON.parse(storeMenus)
        menus.forEach(item => {
            if (item.path) {  // 当且仅当path不为空的时候才去设置路由
                let itemMenu = {
                    path: item.path.replace("/", ""),
                    name: item.name,
                    component: () => import('../views/' + item.pagePath + '.vue')
                }
                manageRoute.children.push(itemMenu)
            } else if (item.children.length) {
                item.children.forEach(item => {
                    if (item.path) {
                        let itemMenu = {
                            path: item.path.replace("/", ""),
                            name: item.name,
                            component: () => import('../views/' + item.pagePath + '.vue')
                        }
                        manageRoute.children.push(itemMenu)
                    }
                })
            }
        })
        // 获取当前的路由对象名称数组
        const currentRouteNames = router.getRoutes().map(v => v.name)
        if (!currentRouteNames.includes('Manage')) {
            // 动态添加到现在的路由对象中去
            router.addRoute(manageRoute)
        }
    }
}

// 刷新重置我就再set一次路由
//setRoutes()

router.beforeEach((to, from, next) => {
    let hasRoutes = store.state.hasRoutes;
    const token = localStorage.getItem("token")
    if (inWhiteList(to.path)) {
        next()
    } else {
        //用户已登录
        if (token) {
            if (!hasRoutes) { //页面刷新的情况，vuex中的数据刷新会丢失
                setRoutes()
                store.commit("SET_ROUTES_STATE", true);
                next(to.path)
            }
            next()
        } else {
            next(`/login`)
        }
    }
})

export default router

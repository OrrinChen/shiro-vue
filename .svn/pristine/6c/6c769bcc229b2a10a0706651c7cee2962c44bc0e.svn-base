<template>
    <div style="line-height: 60px; display: flex">
        <div style="flex: 9;">
            <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>

            <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
                <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
                <el-breadcrumb-item v-for="(item,index) in breadcrumb" :key="index" :to="{ path: item.path}">
                    {{ item.name }}
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-dropdown  @command="handleCommand" style="width: 100px;cursor: pointer;flex:1">
            <div style="display: inline-block">
                <img :src="user.avatarUrl" alt=""
                     style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px" @click="toPerson">
                <span >{{ user.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            </div>
            <el-dropdown-menu  slot="dropdown" style="width: 100px; text-align: center">
                <el-dropdown-item command="toPerson" style="font-size: 14px; padding: 5px 0">
                    <span style="display:inline-block;width:100%;text-decoration: none;">个人信息</span>
                </el-dropdown-item >
                <el-dropdown-item command="toEditPassword" style="font-size: 14px; padding: 5px 0">
                    <span style="display:inline-block;width:100%;text-decoration: none">修改密码</span>
                </el-dropdown-item>
                <el-dropdown-item command="signOut" style="font-size: 14px; padding: 5px 0">
                    <span style="display:inline-block;width:100%;text-decoration: none">退出</span>
                </el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
    export default {
        props: {
            collapseBtnClass: String,
        },
        data() {
            //这里存放数据
            return {
                user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
            };
        },
        //监听属性 类似于data概念
        computed: {
            // 面包屑数据
            breadcrumb() {
                //console.log(this.$route.matched)
                //所有嵌套路径的片段的路由记录
                let matchedArr = this.$route.matched.filter((item) => {
                    return item.path !== "" && item.path !== '/home'
                })
                //console.log("当前所有的嵌套路由", matchedArr)
                return matchedArr;
            },
        },
        //方法集合
        methods: {
            handleCommand (command) {
                if(command ==="signOut"){
                    this.$store.commit("logout")
                }else if(command ==="toPerson"){
                    this.$router.replace('/person')
                }else if(command === "toEditPassword"){
                    this.$router.replace('/password')
                }
            },
            toPerson(){
                this.$router.replace('/person')
            },
            collapse() {
                this.$emit("collapse")
            },
        },
        //监控data中的数据变化
        watch: {
            '$store.state.avatarUrl'(newVal,oldVal){
                console.log("更新图片成功")
                this.user.avatarUrl = newVal
            },
        },
    };
</script>
<style scoped>

</style>

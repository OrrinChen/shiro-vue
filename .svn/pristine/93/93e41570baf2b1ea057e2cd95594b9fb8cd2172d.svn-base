<template>
    <!--el-menu属性（所有菜单展开）:default-openeds="opens"-->
    <el-menu
             style="height: 100%;"
             background-color="rgb(48,65,86)"
             text-color="#fff"
             router
             :collapse-transition="false"
             :collapse="isCollapse"
             :unique-opened="true"
             :default-active="myPath"
    >
        <div style="height: 60px;line-height: 60px;text-align: center;">
            <img src="../assets/logo200.png" style="width: 20px;position: relative;top: 5px" alt="">
            <b style="color:white" v-show="logoTextShow">后台管理系统</b>
        </div>
        <div v-for="item in menus" :key="item.id">
            <div v-if="item.path">
                <el-menu-item :index="item.path">
                    <i :class="item.icon"></i>
                    <span slot="title">{{ item.name }}</span>
                </el-menu-item>
            </div>
            <div v-else>
                <el-submenu :index="item.id + ''">
                    <template slot="title">
                        <i :class="item.icon"></i>
                        <span slot="title">{{ item.name }}</span>
                    </template>
                    <div  v-for="subItem in item.children" :key="subItem.id">
                        <el-menu-item :index="subItem.path">
                            <i :class="subItem.icon"></i>
                            <span slot="title">{{ subItem.name }}</span>
                        </el-menu-item>
                    </div>
                </el-submenu>
            </div>
        </div>
    </el-menu>

</template>

<script>
    //这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
    //例如：import 《组件名称》 from '《组件路径》';

    export default {
        //import引入的组件需要注入到对象中才能使用
        components: {},
        props: {
            isCollapse: Boolean,
            logoTextShow: Boolean
        },
        data() {
            //这里存放数据
            return {
                menus: localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")) : [],
                opens: localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")).map(v => v.id + '') : [],
              myPath:this.$route.path,
              isCollapse: false,
              expandFalg:false,
              menuList: [],
              openeds: ['1'],

            };
        },
        //代码放在导航菜单组件所在的页面里
        watch: {
          $route: function(to, from) {
            //console.log(this.$route);
            //设置导航菜单高亮的路径为前两段路径 You，5 hours ago。子页面，
            if (this.$route.meta && this.$route.meta.name) {
              this.myPath = this.$route.meta.name
            } else {
              this.myPath = this.$route.matched[1].path; //看你的情况而定!!
            }
          }
        },
    };
</script>
<style scoped>
    /*子菜单悬停颜色*/
    .el-menu-item:hover {
        background-color: #001528 !important;
    }

    /*菜单父级悬停颜色*/
    .el-submenu /deep/ .el-submenu__title:hover {
        background-color: rgb(45, 61, 81) !important;
    }

    /*.el-menu-item.is-active {*/
    /*    background-color: #001528 !important;*/
    /*}*/
    /*子菜单的背景色*/
    .el-menu-item {
        background-color: #1F2D3D
    }
</style>

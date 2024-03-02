<template>
    <div class="home" style="height: 100vh;">
        <el-container style="height: 100vh;">
            <el-aside :width="sideWidth+'px'"
                      style="background-color: rgb(238, 241, 246);height: 100%;box-shadow: 2px 0 6px rgba(0,21,41,.35);">
                <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow" />
            </el-aside>

            <el-container>
                <el-header style="font-size: 12px;border-bottom: 1px solid #ccc;line-height: 60px">
                    <Header :collapseBtnClass="collapseBtnClass" @collapse="collapse" />

                </el-header>

                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import Aside from '../components/Aside'
    import Header from '../components/Header'
    export default {
        name: 'Manage',
        components:{Aside,Header},
        data() {
            return {
                menuList: [],
                collapseBtnClass: 'el-icon-s-fold',
                isCollapse: false,//是否隐藏导航栏
                sideWidth: 200,//隐藏后导航栏的宽度
                logoTextShow: true,//是否隐藏项目名称
            }
        },
        methods: {
            collapse() {
                this.isCollapse = !this.isCollapse
                if (this.isCollapse) { //隐藏导航栏
                    this.logoTextShow = false
                    this.sideWidth = 64
                    this.collapseBtnClass = "el-icon-s-unfold"
                } else {
                    this.logoTextShow = true
                    this.sideWidth = 200
                    this.collapseBtnClass = "el-icon-s-fold"
                }
            },
        },

    }
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
        background-color: #1F2D3D !important;
    }

</style>

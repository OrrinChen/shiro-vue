<template>
    <div class="wrapper">
        <div style="margin: 200px auto; background-color: #fff; width: 350px; padding: 20px; border-radius: 10px">
            <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
            <el-form :model="user" :rules="rules" ref="userForm">
                <el-form-item prop="username">
                    <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user"
                              v-model="user.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password
                              v-model="user.password"></el-input>
                </el-form-item>
                <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="primary" size="medium" autocomplete="off" @click="login">登录</el-button>
                    <el-button type="warning" size="medium" autocomplete="off" @click="$router.push('/register')">注册</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import load from '@/utils/loading.js';

    export default {
        name: "Login",
        data() {
            return {
                user: {
                    username: "admin",
                    password: "123"
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
            login() {
                this.$refs['userForm'].validate((valid) => {
                    if (valid) {  // 表单校验合法
                        //发送请求
                        this.$Loading("登录中...")
                        this.request.post("/login", this.user).then(res => {
                            this.$Loading.close()
                            if (res.code !== 200) {
                                this.$message.error("用户名或密码错误")
                            } else {
                                localStorage.setItem("user",JSON.stringify(res.data.user))
                                localStorage.setItem("token",JSON.stringify(res.data.token))
                                localStorage.setItem("menus",JSON.stringify(res.data.menus))
                                this.$router.replace("/home")
                                this.$message.success("登录成功")
                                console.log("登录成功...")
                            }
                        })
                    } else {
                        return false;
                    }
                });
            }
        }
    }
</script>

<style>
    .wrapper {
        height: 100vh;
        background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
        overflow: hidden;
    }
</style>

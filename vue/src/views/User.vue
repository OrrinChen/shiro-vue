<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search"
                      v-model="pagequery.username"></el-input>
            <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message"
                      class="ml-5" v-model="pagequery.email"></el-input>
            <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position"
                      class="ml-5" v-model="pagequery.address"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button class="ml-5" type="info" @click="reset">重置</el-button>
        </div>
        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-popconfirm class="ml-5"
                           confirm-button-text='确定'
                           cancel-button-text='我再想想'
                           icon="el-icon-info"
                           icon-color="red"
                           title="您确定批量删除这些数据吗？"
                           @confirm="delBatch"
            >
                <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
        </div>

        <el-table @selection-change="handleSelectionChange" :data="tableData" border stripe :header-cell-style="{background:'#eee',color:'#000000'}">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="username" label="用户名" width="60"></el-table-column>
            <el-table-column prop="role"
                    label="角色"
                    width="280">
                <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper" >
                            <el-tag style="margin-left: 5px" v-for="item in scope.row.role" size="medium">{{ item.name }}</el-tag>
                        </div>
                </template>
            </el-table-column>
            <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
            <el-table-column prop="email" width="160" label="邮箱"></el-table-column>
            <el-table-column prop="phonenumber" width="120" label="电话"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column label="操作" width="280" align="center">
                <template slot-scope="scope">
                    <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-popconfirm
                            class="ml-5"
                            confirm-button-text='确定'
                            cancel-button-text='我再想想'
                            icon="el-icon-info"
                            icon-color="red"
                            title="您确定删除吗？"
                            @confirm="del(scope.row.id)"
                    >
                        <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pagequery.pageNum"
                    :page-sizes="[5, 10, 15, 20]"
                    :page-size="pagequery.pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="80px" size="small">
                <el-form-item label="用户名">
                    <el-input v-model="form.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="角色">
                    <el-select v-model="roleid" multiple placeholder="请选择角色">
                        <el-option
                                v-for="item in roles"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input v-model="form.nickname" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="电话">
                    <el-input v-model="form.phonenumber" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>


        <el-dialog title="课程信息" :visible.sync="vis" width="30%" >
            <el-table :data="courses" border stripe>
                <el-table-column prop="name" label="课程名称"></el-table-column>
                <el-table-column prop="score" label="学分"></el-table-column>
            </el-table>
        </el-dialog>

        <el-dialog title="课程信息" :visible.sync="stuVis" width="30%" >
            <el-table :data="stuCourses" border stripe>
                <el-table-column prop="name" label="课程名称"></el-table-column>
                <el-table-column prop="score" label="学分"></el-table-column>
            </el-table>
        </el-dialog>
    </div>
</template>

<script>
    import qs from "qs";

    export default {
        components: {},
        props: {},
        data() {
            return {
                roleid:[],
                dialogFormVisible: false,//新增的对话框
                multipleSelection: [],//批量删除传递id
                form: {},//新增的表单
                roles:[],//角色
                tableData: [],//表格数据
                total: 0,
                pagequery: {  //分页查询条件
                    pageNum: 1,
                    pageSize: 5,
                    username: "",
                    email: "",
                    address: "",
                },
                courses: [],
                vis: false,
                stuCourses: [],
                stuVis: false
            };
        },
        computed: {},
        watch: {},
        methods: {
            lookCourse(courses) {
                this.courses = courses
                this.vis = true
            },
            lookStuCourse(stuCourses) {
                this.stuCourses = stuCourses
                this.stuVis = true
            },
            //新增对话框的初始化
            handleAdd() {
                this.dialogFormVisible = true
                this.form = {}
            },
            //编辑对话框初始化
            handleEdit(row) {
                this.form = row
                this.roleid = row.role.map(k=>k.id)
                this.dialogFormVisible = true
            },
            //新增
            save() {
                console.log(this.form)
                this.form.roleid = this.roleid
                //this.form.role = undefined
                this.request.post("/user", this.form).then(res => {
                    if (res) {
                        this.$message.success("保存成功")
                        this.dialogFormVisible = false
                        this.load()
                    } else {
                        this.$message.error("保存失败")
                    }
                })
            },
            //单个删除
            del(id) {
                this.request.delete("/user/" + id).then(res => {
                    console.log(res)
                    if (res) {
                        this.$message.success("删除成功")
                        this.load()
                    } else {
                        this.$message.error("删除失败")
                    }
                })
            },
            //批量删除
            delBatch() {
                let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
                if(ids.length===0){
                    this.$message.warning("请选择删除的用户")
                    return
                }
                this.request.post("/user/del/batch", ids).then(res => {
                    if (res) {
                        this.$message.success("批量删除成功")
                        this.load()
                    } else {
                        this.$message.error("批量删除失败")
                    }
                })
            },
            reset() {
                this.pagequery = {
                    pageNum: 1,
                    pageSize: 5,
                    username: "",
                    email: "",
                    address: "",
                }
                this.load()
            },
            load() {
                this.request.get("/user/page?" + qs.stringify(this.pagequery)).then(res => {
                    if(res.code===200){
                        this.total = res.data.total
                        this.tableData = res.data.records
                    }
                })
                this.request.get("/role").then(res=>{
                    this.roles = res.data
                })
            },
            handleSizeChange(pageSize) {
                console.log(pageSize)
                this.pagequery.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum) {
                console.log(pageNum)
                this.pagequery.pageNum = pageNum
                this.load()
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }
        },
        //生命周期 - 创建完成（可以访问当前this实例）
        created() {
            this.load()
        },
        //生命周期 - 挂载完成（可以访问DOM元素）
        mounted() {
        },
        beforeCreate() {
        }, //生命周期 - 创建之前
        beforeMount() {
        }, //生命周期 - 挂载之前
        beforeUpdate() {
        }, //生命周期 - 更新之前
        updated() {
        }, //生命周期 - 更新之后
        beforeDestroy() {
        }, //生命周期 - 销毁之前
        destroyed() {
        }, //生命周期 - 销毁完成
        activated() {
        }, //如果页面有keep-alive缓存功能，这个函数会触发
    };
</script>
<style scoped>

</style>

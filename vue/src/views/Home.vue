<template>
    <div>
        <el-row :gutter="10" style="margin-bottom: 40px">
            <el-col :span="6">
                <el-card style="color: #409EFF">
                    <div><i class="el-icon-user-solid"/>用户总数</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold">
                        {{ utotal }}
                    </div>
                </el-card>
            </el-col>

            <el-col :span="6">
                <el-card style="color: #F56C6C">
                    <div><i class="el-icon-money"/>角色总数</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold">
                        {{ rtotal }}
                    </div>
                </el-card>
            </el-col>

            <el-col :span="6">
                <el-card style="color: #E6A23C">
                    <div><i class="el-icon-bank-card"/>菜单总数</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold">
                        {{ mtotal }}
                    </div>
                </el-card>
            </el-col>

            <el-col :span="6">
                <el-card style="color: #67C23A">
                    <div><i class="el-icon-s-shop"/>在线人数</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold">
                        {{ ototal }}
                    </div>
                </el-card>
            </el-col>

        </el-row>


        <el-row>
            <el-col :span="12">
                <div id="main" style="width: 500px; height: 400px"></div>
            </el-col>
            <el-col :span="12">
                <div id="pie" style="width: 500px; height: 400px"></div>
            </el-col>
        </el-row>
    </div>

</template>


<script>
    //这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
    //例如：import 《组件名称》 from '《组件路径》';
    import * as echarts from 'echarts'

    export default {
        //import引入的组件需要注入到对象中才能使用
        components: {},
        props: {},
        data() {
            //这里存放数据
            return {
                utotal:'',
                rtotal:'',
                mtotal:'',
                ototal:'',
            };
        },
        created() {
            this.request.get("/echarts/allTotal").then(res=>{
                let d = res.data
                this.utotal = d.utotal
                this.rtotal = d.rtotal
                this.mtotal = d.mtotal
                this.ototal = d.ototal
            })
        },
        mounted() {
            var option = {
                title: {
                    text: '各季度注册数量统计',
                    subtext: '趋势图',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                xAxis: {
                    type: 'category',
                    data: ["第一季度", "第二季度", "第三季度", "第四季度"]
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: [],
                        type: 'line'
                    },
                    {
                        data: [],
                        type: 'bar',
                    },

                ]
            };
            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);

            this.request.get("/echarts/members").then(res => {
                // 填空
                // option.xAxis.data = res.data.x
                option.series[0].data = res.data
                option.series[1].data = res.data
                // 数据准备完毕之后再set
                myChart.setOption(option);

            })
            var pieOption = {
                title: {
                    text: '各季度注册数量统计',
                    subtext: '比例图',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        type: 'pie',
                        radius: '60%',
                        label: {            //饼图图形上的文本标签
                            show: true,
                            position: 'inner', //标签的位置
                            fontWeight: 300,
                            fontSize: 14,    //文字的字体大小
                            color: "#fff",
                            formatter: '{d}%'
                        },
                        data: [],  // 填空
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            var pieDom = document.getElementById('pie');
            var pieChart = echarts.init(pieDom);

            this.request.get("/echarts/members").then(res => {

                pieOption.series[0].data = [
                    {name: "第一季度", value: res.data[0]},
                    {name: "第二季度", value: res.data[1]},
                    {name: "第三季度", value: res.data[2]},
                    {name: "第四季度", value: res.data[3]},
                ]
                pieChart.setOption(pieOption)
            })
        },

    };
</script>
<style scoped>

</style>

<template>
  <div class="container">
    <div class="row">
      <p class="headline">开启智能支付管理新体验</p>
    </div>
    <div class="row">
      <div class="description">
        <p><span class="yellow">方便、快捷、高效</span><span class="black">于一体的支付系统</span></p>
      </div>
    </div>
    <div class="row">
      <div class="list-item-container">
        <ul class="list">
          <li class="list-item">实现各个支付方式统一管理</li>
          <li class="list-item">处理线上异常交易订单</li>
        </ul>
      </div>
    </div>
    <div class="row">
      <div class=" list-item-container-2">
        <ul class="list-2">
          <li class="list-item">实现可视化检测</li>
          <li class="list-item">实现电子对账</li>
        </ul>
      </div>
    </div>
    <div id="app">
      <!-- 其他元素 -->
      <router-view></router-view>

      <!-- 其他元素 -->
    </div>
    <div>
      <div class="logo">
        <img src="../assets/logo.png" alt="徳晟图标">
      </div>
    </div>
    <div>
      <p class="copyright">copyright 2023徳晟光电股份有限公司版权所有 粤ICP备 </p>
    </div>
  </div>
</template>

<script>
document.body.style.overflow = 'hidden';
// 导入 Home 组件

export default {
  name: 'App',
  // 在 components 属性中注册 Home 组件
  components: {
  }
}
</script>

<style>
element.style {
  overflow: hidden;
}
.container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  box-sizing: border-box;
  background-image: url('../assets/bg1.png');
  background-size: cover; /* 调整背景图片的大小以填充容器 */
  background-position: center;
}

.row {
  display: flex; /* 使用弹性盒子布局 */
  box-sizing: border-box; /* 这一行和下面几行是新添加的 */
}

.list-item-container{
  display: flex;
  flex-direction: column;
  margin: 0;
  position: fixed;
  left: 63.26vw;
  top: 26.13vh;
  box-sizing: border-box; /* 这一行和下面几行是新添加的 */
}


.list-item {
  display: flex; /* 将段落内容设置为弹性盒子布局 */
  align-items: center; /* 垂直居中 */
  margin: 0; /* 移除段落的默认边距 */
  padding-left: 1.39vw; /* 调整左边距以留出空间放置大圆点 */
  position: relative; /* 设置相对定位 */
  width: 15.35vw;
  height: 1.97vh;
  font-family: MicrosoftYaHei;
  font-size: 0.83vw;
  color: #1C295F;
  letter-spacing: 0.01vw;
  box-sizing: border-box; /* 这一行和下面几行是新添加的 */

}

.list-item-container-2 {
  display: flex;
  flex-direction: column;
  margin: 0;
  position: fixed;
  left: 86.39vw;
  top: 26.13vh;
}

.list-item::before {
  content: "•"; /* 使用伪元素添加大圆点 */
  position: relative; /* 设置绝对定位 */
  left: 0; /* 将大圆点放置在段落最左边 */
  margin-right: 0.69vw; /* 调整大圆点与文本的间距 */
}

.list-item-inline {
  display: inline-block;
}


.list-item {
  margin-bottom: 20px;
}

.headline {
  position: fixed;
  left: 67.36vw; /* 将像素值转换为vw单位 */
  top: 12.93vh; /* 将像素值转换为vh单位 */
  width: 21.46vw; /* 将像素值转换为vw单位 */
  height: 3.87vh; /* 将像素值转换为vh单位 */
  font-family: MicrosoftYaHei-Bold;
  font-weight: 700;
  font-size: 1.53vw; /* 将像素值转换为vw单位 */
  color: #1C295F;
  letter-spacing: 0.16vw; /* 将像素值转换为vw单位 */
  box-sizing: border-box; /* 这一行和下面几行是新添加的 */
}
.description {
  position: fixed;
  left: 69.65vw;
  top: 20.13vh;
}
.yellow {
  color: #ff7f00ff;
  font-size: 14px;
  font-face: [object Object];
  font-weight: 0;
  line-height: 0;
  letter-spacing: 1.12px;
  paragraph-spacing: 0;
  text-align: left;
}

.black {
  color: #1c295fff;
  font-size: 14px;
  font-face: [object Object];
  font-weight: 0;
  line-height: 0;
  letter-spacing: 1.12px;
  paragraph-spacing: 0;
  text-align: left;
}
.logo {
  position: fixed;
  top: 43px;
  left: 66px;
  width: 186px;
  height: 65px;
}
.copyright {
  left: 9.44vw;
  top: 93.6vh;
  position: fixed;
  width: 322px;
  height: 20px;
  font-family: PingFangSC-Regular;
  font-weight: 400;
  font-size: 12px;
  color: #00000073;
  line-height: 20px;
}

</style>




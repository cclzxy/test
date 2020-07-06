<template>
  <div class="home">
    <el-container>
      <el-header>{{titleName}}</el-header>
      <el-container>
        <el-aside width="150px">
          <el-col :span="24">
            <el-menu default-active="1" class="el-menu-vertical-demo">
              <el-menu-item index="1" @click="myStudyViews">
                <i class="el-icon-tickets"></i>
                <span slot="title">{{asideList[0]}}</span>
              </el-menu-item>
              <el-menu-item index="2" @click="mySelfViews">
                <i class="el-icon-lock"></i>
                <span slot="title">{{asideList[1]}}</span>
              </el-menu-item>
              <el-menu-item index="3" @click="allTablesViews">
                <i class="el-icon-lollipop"></i>
                <span slot="title">{{asideList[2]}}</span>
              </el-menu-item>
              <el-menu-item index="4" disabled>
                <i class="el-icon-lollipop"></i>
                <span slot="title">{{asideList[3]}}</span>
              </el-menu-item>
              <el-menu-item index="5" disabled>
                <i class="el-icon-lollipop"></i>
                <span slot="title">{{asideList[4]}}</span>
              </el-menu-item>
              <el-menu-item index="6" disabled>
                <i class="el-icon-lollipop"></i>
                <span slot="title">{{asideList[5]}}</span>
              </el-menu-item>
              <el-menu-item index="7" disabled>
                <i class="el-icon-lollipop"></i>
                <span slot="title">{{asideList[6]}}</span>
              </el-menu-item>
              <el-menu-item index="8" disabled>
                <i class="el-icon-lollipop"></i>
                <span slot="title">{{asideList[7]}}</span>
              </el-menu-item>
              <el-menu-item index="9" disabled>
                <i class="el-icon-lollipop"></i>
                <span slot="title">{{asideList[8]}}</span>
              </el-menu-item>
              <el-menu-item index="10" disabled>
                <i class="el-icon-lollipop"></i>
                <span slot="title">{{asideList[9]}}</span>
              </el-menu-item>
              <el-menu-item index="11" disabled>
                <i class="el-icon-lollipop"></i>
                <span slot="title">{{asideList[10]}}</span>
              </el-menu-item>
              <el-menu-item index="12" disabled>
                <i class></i>
                <span slot="title">{{asideList[11]}}</span>
              </el-menu-item>
              <el-menu-item index="13" disabled>
                <i class></i>
                <span slot="title">{{asideList[12]}}</span>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-aside>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      titleName: "人的一生是短的,但如果卑劣地过这一生,就太长了。",
      asideList: ["笔记", "个人", "测试", "", "", "", "", "", "", ""]
    };
  },
  created() {},
  methods: {
    //第一个导航路由
    myStudyViews() {
      if (this.$route.path !== "/") {
        this.$router.push({
          path: "/",
          query: { token: "", user: "" }
        });
      }
    },
    //第二个导航路由
    mySelfViews() {
      if (this.$route.path !== "/mySelf") {
        this.$router.push({
          path: "/mySelf",
          query: { token: "", user: "" }
        });
      }
    },
    //第三个导航路由
    allTablesViews() {
      if (this.$route.path !== "/AllTables") {
        this.$router.push({
          path: "/AllTables",
          query: { token: "", user: "" }
        });
      }
    }
  }
};
</script>
<style>
.el-header {
  color: black;
  text-align: center;
  line-height: 50px;
  font-size: 18px;
  background-color: aliceblue;
}

.el-aside {
  color: #333;
  text-align: center;
  height: 800px;
  width: 150px;
}

.el-main {
  color: #333;
  text-align: center;
  height: 800px;
}
</style>

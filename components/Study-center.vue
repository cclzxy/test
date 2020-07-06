<template>
  <div class="mainStudy">
    <el-table :data="$store.state.studyList" stripe style="width: 100%">
      <el-table-column prop="title" label="标题"></el-table-column>
      <el-table-column prop="content" label="内容"></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    getList() {
      let _that = this;
      this.$http({
        method: "POST",
        url: "/api/getList"
      })
        .then(res => {
          _that.$store.state.studyList = res.data;
        })
        .catch(err => {
          console.log("error study");
        });
    }
  },
  components: {},
  mounted() {},
  created() {
    this.getList(); //页面创建之前从数据库拉取数据
  }
};
</script>

<style scoped>
.mainStudy {
  height: 600px;
  margin-top: 20px;
}
</style>

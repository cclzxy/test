<template>
  <div class="mainTop">
    <el-row :gutter="20">
      <el-col :span="6" :offset="6">
        <div class="grid-content bg-purple">
          <el-input suffix-icon="el-icon-search" placeholder="关键字" v-model="input"></el-input>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple">
          <el-button type="primary" @click="dialogFormVisible = true">添加一个</el-button>
        </div>
      </el-col>
    </el-row>
    <el-dialog title="加一条笔记" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="醒目的标题" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="笔记的内容" :label-width="formLabelWidth">
          <el-input v-model="form.content" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>
    <el-divider>
      <i class="el-icon-wind-power"></i>
    </el-divider>
  </div>
</template>

<script>
export default {
  props: {
    msg: String
  },
  data() {
    return {
      list: "",
      input: "",
      dialogFormVisible: false,
      form: {
        name: "",
        content: ""
      },
      formLabelWidth: "120px"
    };
  },
  methods: {
    add() {
      let _that = this;
      this.dialogFormVisible = false;
      this.$http({
        method: "POST",
        url: "/api/add",
        params: { title: _that.form.name, content: _that.form.content }
      })
        .then(res => {
          this.$http({
            method: "POST",
            url: "/api/getList"
          })
            .then(res => {
              this.$store.state.studyList = res.data;
            })
            .catch(err => {
              console.log("找不到列表");
            });
        })
        .catch(err => {
          console.log("插入失败");
        });
    }
  },
  watch: {
    input(value) {
      let _that = this;
      this.$http({
        method: "POST",
        url: "/api/findByTitle",
        params: { title: _that.input }
      })
        .then(res => {
          this.$store.state.studyList = res.data;
        })
        .catch(err => {
          console.log("搜索失败");
        });
    }
  },
  created() {}
};
</script>

<style scoped>
.mainTop {
  height: 70px;
  text-align: center;
}
</style>

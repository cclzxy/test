<template>
  <div>
    <el-button type="success" @click="addTable" class="btn">新增一张表</el-button>
    <!-- 所有表单 -->
    <div class="topAllTable">
      <div v-for="n in allTableName" class="butn">
        <el-button type="primary" @click="getList(n.tableId,n.title)">{{n.title}}</el-button>
      </div>
    </div>
    <!-- 所有表单结束 -->
    <!-- 分割线 -->
    <el-divider>
      <i class="el-icon-s-order"></i>
    </el-divider>
    <!-- 分割线结束 -->
    <!-- 弹框添加新表单 -->
    <el-dialog title="新的表单" :visible.sync="dialogVisible" width="38%">
      <!-- 新表单具体内容 -->
      <el-input placeholder="请输入新的表单名称" v-model="newTableName">
        <template slot="prepend">新表单名称</template>
      </el-input>

      <div class="cloNum" v-for="i in cloNum">
        <el-input :placeholder="'属性'+i" v-model="newTableClo[i-1]"></el-input>
      </div>

      <el-button type="primary" @click="addNewCol">
        <i class="el-icon-plus">增加</i>
      </el-button>
      <el-button type="primary" @click="cutCol">
        <i class="el-icon-minus">减少</i>
      </el-button>
      <!-- 新表单具体内容结束 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeTable">取 消</el-button>
        <el-button type="primary" @click="confirmTable">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 弹框添加新表单结束 -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      allTableName: [],
      dialogVisible: false, //控制弹框显示隐藏
      allClos: [], //所有可用的列
      newTableName: "", //新表单名称
      newTableClo: [], //新表单属性
      cloNum: 3 //默认初始为3条属性
    };
  },
  computed: {},
  methods: {
    //提交新表单
    submitNewTable() {
      let tbClo = this.allClos.slice(1, this.newTableClo.length + 1);
      let _that = this;
      this.$http({
        method: "POST",
        url: "/loc/addTable",
        params: {
          tableName: _that.newTableName, //表头
          cloName: _that.newTableClo.toString(), //表clo
          tableClo: tbClo.toString() //数据库中可用的表列截取
        }
      })
        .then(res => {
          alert("提交成功");
          _that.getAllClo();
          _that.getAllTableName();
        })
        .catch(err => {
          console.log("提交失败");
        });
    },
    //初始化弹框中的内容
    initTable() {
      this.newTableName = "";
      this.newTableClo = [];
      this.cloNum = 3;
    },
    //加号按钮，上限为10
    addNewCol() {
      this.cloNum <= 9 ? this.cloNum++ : (this.cloNum = this.cloNum);
    },
    //减号按钮,下限为2
    cutCol() {
      this.cloNum >= 3
        ? this.newTableClo.pop()
        : (this.newTableClo = this.newTableClo);
      this.cloNum >= 3 ? this.cloNum-- : (this.cloNum = this.cloNum);
    },
    //新增表单弹框弹出
    addTable() {
      this.dialogVisible = true;
    },
    //弹框校验以及提交
    confirmTable() {
      if (
        this.newTableName != "" &&
        this.cloNum - this.newTableClo.length == 0
      ) {
        this.dialogVisible = false;
        this.submitNewTable(); //提交表单
        this.initTable(); //初始化弹框数据
      } else {
        alert("不能有空行");
      }
    },
    //弹框取消
    closeTable() {
      this.dialogVisible = false;
      this.initTable();
    },
    //获取所有表单
    getList(tableId, activeTitle) {
      let _that = this;
      this.$http({
        method: "POST",
        url: "/loc/getClo",
        params: { tableId, tableId }
      })
        .then(res => {
          _that.$store.state.activeTableCol = res.data; //当前表列
          _that.$store.state.activeTableTitle = activeTitle; //当前表名字
          let clo = []; //列数组
          for (let i of res.data) {
            clo.push(i.cloTitle);
          }
          _that.$store.state.dataListName = clo; //列（对应数据库）
          _that.getNowTableDatas(clo);
          //   console.log("当前活跃的表：" + _that.$store.state.activeTableTitle);
          //   console.log("当前活跃的列：" + clo);
        })
        .catch(err => {
          console.log("找不到当前表数据getList");
        });
    },
    //获取所有可用的列
    getAllClo(tableId) {
      let _that = this;
      this.$http({
        method: "POST",
        url: "/loc/getAllClo"
      })
        .then(res => {
          _that.allClos = res.data;
        })
        .catch(err => {
          console.log("找不到当前表数据getAllClo");
        });
    },
    //请求表中数据
    getNowTableDatas(nowClo) {
      this.$store.state.activeTableList = [];
      let _that = this;
      this.$http({
        method: "POST",
        url: "/loc/getNowTableData",
        params: {
          nowClo: nowClo.toString()
        }
      })
        .then(res => {
          _that.$store.state.activeTableList = res.data;
        })
        .catch(err => {
          console.log("找不到当前表数据getNowTableDatas");
        });
    },
    //所有表名数据
    getAllTableName() {
      let _that = this;
      this.$http({
        method: "POST",
        url: "/loc/getTableName"
      })
        .then(res => {
          _that.$store.state.tableList = res.data;
          _that.allTableName = res.data;
        })
        .catch(err => {
          console.log("找不到列表");
        });
    }
  },
  mounted() {},
  created() {
    this.getAllClo(); //获取所有可用的列
    this.getAllTableName(); //获取所有表列表
  },
  components: {}
};
</script>

<style scoped>
.cloNum {
  padding: 5px;
}
.topAllTable {
  height: 70px;
  text-align: center;
}
.butn {
  float: left;
  padding: 10px;
}
.btn {
  width: 20%;
}
</style>

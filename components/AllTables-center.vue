<template>
  <div class="mainAllTables">
    <el-table :data="tableData" style="width: 100%">
      <div v-for="(i,index) in cloName">
        <el-table-column :prop="dataList[index]" :label="i.cloName"></el-table-column>
      </div>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="info" @click="handleAdd">新增</el-button>
          <el-button
            size="mini"
            disabled
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 弹框 -->
    <el-dialog :title="boxTitle" :visible.sync="dialogVisible" width="50%">
      <!-- 弹框具体内容 -->

      <div class="cloNum" v-for="(i,index) in clo">
        <el-input placeholder="请输入" v-model="nowRow[index]">
          <template slot="prepend">{{clo[index]}}</template>
        </el-input>
      </div>

      <!-- 弹框具体内容结束 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeTable">取 消</el-button>
        <el-button v-if="ifAdd" type="primary" @click="confirmTable(1)">确 定</el-button>
        <el-button v-if="!ifAdd" type="primary" @click="confirmTable(2)">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 弹框结束 -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      clo: [],
      nowRow: [], //当前选中的数据（value值[]）
      nowDataRow: [], //当前选中行（数据库key值[]）
      dialogVisible: false, //控制弹框显示隐藏
      boxTitle: "",
      ifAdd: true, //默认确认按钮为添加
      nowDataId: ""
    };
  },
  computed: {
    //当前表结构（对象集合）
    cloName() {
      return this.$store.state.activeTableCol;
    },
    //当前表所有数据（对象集合）
    tableData() {
      if (this.$store.state.activeTableList == []) {
        return [];
      }
      return this.$store.state.activeTableList;
    },
    //当前表结构（数据库名）
    dataList() {
      return this.$store.state.dataListName;
    }
  },
  methods: {
    //选中的行编辑
    handleEdit(index, row) {
      this.boxTitle = "修改编辑";
      this.clo = [];
      this.nowDataRow = [];
      this.nowRow = [];
      //   this.nowDataId = this.$store.state.activeTableList2[index];
      console.log(this.nowDataId);
      for (let i of this.$store.state.activeTableCol) {
        this.clo.push(i.cloName);
      }
      for (let i in row) {
        this.nowDataRow.push(i);
        this.nowRow.push(row[i]);
      }
      this.nowRow.shift();

      this.dialogVisible = true;
    },
    //添加数据
    handleAdd() {
      this.ifAdd = false;
      this.boxTitle = "添加数据";
      this.clo = [];
      this.nowDataRow = [];
      this.nowRow = [];
      for (let i of this.$store.state.activeTableCol) {
        this.clo.push(i.cloName);
      }
      this.dialogVisible = true;
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    //弹框提交
    confirmTable(updOrAdd) {
      if (updOrAdd == 1) {
        alert("思路：将修改后的对象信息传到后台，再重新返回新数据列表");
        this.dialogVisible = false;
      } else {
        let _that = this;
        let clo = this.nowRow;
        let nlname = this.$store.state.dataListName;
        this.$http({
          method: "POST",
          url: "/loc/addOne",
          params: {
            nowRol: clo.toString(), //数据
            listName: nlname.toString() //结构(clo001,clo002....)
          }
        })
          .then(res => {
            _that.getNowTableDatas(nlname.toString());
          })
          .catch(err => {
            console.log("提交失败");
          });
        this.dialogVisible = false;
      }
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
    //弹框取消
    closeTable() {
      this.dialogVisible = false;
    }
  },
  created() {},
  components: {}
};
</script>

<style scoped>
.cloNum {
  padding: 5px;
}
.mainAllTables {
  height: 600px;
  margin-top: 20px;
}
</style>

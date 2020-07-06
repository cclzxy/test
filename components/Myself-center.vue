<template>
  <div class="mainMyself"></div>
</template>

<script>
export default {
  data() {
    return {
      dynamicValidateForm: {
        domains: [
          {
            value: ""
          }
        ]
      }
    };
  },
  methods: {},
  mounted() {},
  created() {},
  components: {}
};
</script>

<style scoped>
.el-input {
  width: 500px;
}
.mainMyself {
  height: 600px;
  margin-top: 20px;
}
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple {
  background: #d3dce6;
}
.grid-content {
  border-radius: 4px;
  height: 34px;
}
</style>

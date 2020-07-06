<template>
  <div id="center">
    <Top />
    <Main />
  </div>
</template>

<script>
import Top from "@/components/AllTables-top.vue";
import Main from "@/components/AllTables-center.vue";
export default {
  data() {
    return {};
  },
  methods: {},
  mounted() {},
  components: {
    Top,
    Main
  }
};
</script>

<style scoped>
</style>

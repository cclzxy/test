<template>
  <div id="center">
    <Top />
    <Main />
  </div>
</template>

<script>
// @  相当于 ../src
import Top from "@/components/Myself-top.vue";
import Main from "@/components/Myself-center.vue";

export default {
  data() {
    return {};
  },
  methods: {},
  components: {
    Top,
    Main
  }
};
</script>
<style scoped>
#center {
  height: 700px;
}
</style>
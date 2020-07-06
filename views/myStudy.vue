<template>
  <div id="center">
    <Top msg="ok" />
    <center />
  </div>
</template>

<script>
// @  相当于 ../src
import Top from "@/components/Study-top.vue";
import center from "@/components/Study-center.vue";
export default {
  name: "Center",
  components: {
    Top,
    center
  }
};
</script>
<style scoped>
#center {
  height: 700px;
}
</style>
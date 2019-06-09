<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
  >
    <el-submenu
      v-for="(parent, parentIndex) in checkItem"
      :key="parent.id"
      :index="parent.id"
    >
      <template slot="title">
        <el-checkbox
          :indeterminate="isIndeterminate[parentIndex]"
          v-model="checkAll[parentIndex]"
          @change="handleCheckAllChange(parentIndex)"
        >{{parent.name}}</el-checkbox>
      </template>

      <el-menu-item-group>
        <el-checkbox-group
          v-model="checkedItem[parentIndex]"
          @change="handleCheckedChange(parentIndex)"
        >
          <el-menu-item
            v-for="child in parent.list"
            :key="child.id"
            :index="child.id"
          >
            <el-checkbox :label="child.id">{{child.name}}</el-checkbox>
          </el-menu-item>
        </el-checkbox-group>

      </el-menu-item-group>

    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  data() {
    return {
      checkAll: [],
      isIndeterminate: [],
      checkedItem: [],
      checkItem: [
        {
          id: "1",
          name: "test1",
          list: [
            {
              id: "11",
              name: "test11"
            },
            {
              id: "12",
              name: "test12"
            }
          ]
        },
        {
          id: "2",
          name: "test2",
          list: [
            {
              id: "21",
              name: "test21"
            },
            {
              id: "22",
              name: "test22"
            }
          ]
        }
      ]
    };
  },
  methods: {
    getChildList(parentIndex) {
        var childList = [];
        for (var childIndex in this.checkItem[parentIndex].list) {
            childList.push(this.checkItem[parentIndex].list[childIndex].id)
        }
        return childList;
    },
    handleCheckAllChange(parentIndex) {
      var childList = this.getChildList(parentIndex);
      this.$set(this.checkedItem, parentIndex, this.checkAll[parentIndex] ? childList : []);
      this.$set(this.isIndeterminate, parentIndex, false);
    },
    handleCheckedChange(parentIndex) {
      var childList = this.getChildList(parentIndex);
      let checkedCount = this.checkedItem[parentIndex].length;
      this.$set(this.checkAll, parentIndex, checkedCount === childList.length);
      this.$set(this.isIndeterminate, parentIndex, checkedCount > 0 && checkedCount < childList.length);
    }
  },
  created () {
    for (var parentIndex in this.checkItem) {
      this.$set(this.checkedItem, parentIndex, []);
    }
  }
};
</script>

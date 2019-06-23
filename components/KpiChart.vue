<template>
  <div>
    <vue-c3
      id="chart"
      :handler="handler"
    ></vue-c3>
  </div>
</template>

<script>
import Vue from "vue";
import VueC3 from "vue-c3";
import "c3/c3.min.css";
import moment from "moment";
import * as d3 from "d3";
var dataMap = {
  1: "工程1",
  2: "工程2",
  3: "工程3",
  4: "工程4",
  5: "工程5",
  6: "工程6"
};

export default {
  components: {
    VueC3
  },
  data() {
    return {
      handler: new Vue(),
      columns_data: [
        ["data1", 30, 20, 50, 40, 60, 50],
        ["data2", 200, 130, 90, 240, 130, 220],
        ["data3", 300, 200, 160, 400, 250, 250],
        ["data4", 200, 130, 90, 240, 130, 220]
      ]
    };
  },
  mounted() {
    let options = {
      data: {
        columns: this.columns_data,
        type: "bar",
        types: {
          data4: "line"
        },
        axes: {
            data4: 'y2'
        },
        onclick: function (d, element) {
            console.log(d)
            console.log(element)
        }
      },
      legend: {
        show: false
      },
      axis: {
        // rotated: true,
        x: {
          type: "category",
          categories: ["1月", "2月", "3月", "4", "6", "7", "8", "9", "10"]
        },
        y2: {
          show: true
        }
      }
    };

    // let options = {
    //   data: {
    //     xs: {
    //       y: "x"
    //     },
    //     xFormat: "%Y-%m-%d %H:%M",
    //     columns: [
    //       ["x", "2019-08-20 08:20", "2019-08-20 09:20", "2019-08-20 11:20"],
    //       //            ['x', '20130101', '20130102', '20130103', '20130104', '20130105', '20130106'],
    //       ["y", 1, 2, 3]
    //     ],
    //   },
    //   //c3.chartのデータ例
    //   axis: {
    //     x: {
    //       type: "timeseries",
    //       tick: {
    //         format: "%Y-%m-%d %H:%M"
    //       }
    //     },
    //     xFormat: "%Y-%m-%d %H:%M",
    //     y: {
    //       tick: {
    //         format: function(d) {
    //           if (d == 1) {
    //             return "unko";
    //           }
    //           return "$" + d;
    //         }
    //       }
    //     }
    //   },
    // onrendered: function() {
    //   d3.select("#chart")
    //     .selectAll(".c3-chart")
    //     .on("contextmenu", function(d, i, c, e) {
    //       d3.event.preventDefault();
    //       // var vals = chart.data().map(function(series) {
    //       //   var name = series.id;
    //       //   return {
    //       //     name: name,
    //       //     value: chart.data.values(name)[d.x]
    //       //   };
    //       // });
    //       console.log(d);
    //       console.log(c);
    //       console.log(d3.event);
    //       console.log(i);
    //       alert("aaa");
    //     });
    // }
    // };
    this.handler.$emit("init", options);
    for (var i = 1; i < 6 + 1; i++) {
      if (i % 2 != 0) {
        this.handler.$emit("dispatch", chart => {
          chart.regions.add({
            axis: "y",
            start: i + 1,
            end: i,
            class: "regionY"
          });
        });
      }
    }

    // const data1 = {
    //   xs: {
    //       y2: "x2"
    //     },
    //     xFormat: "%Y-%m-%d %H:%M",
    //     columns: [
    //       ["x2", "2019-08-20 08:20", "2019-08-20 09:20", "2019-08-20 11:20"],
    //       //            ['x', '20130101', '20130102', '20130103', '20130104', '20130105', '20130106'],
    //       ["y2", 1, 4, 9]
    //     ],
    // };
    // this.handler.$emit("dispatch", chart => {
    //   chart.load(data1);
    // });

    // const data3 = {
    //   xs: {
    //       y3: "x3"
    //     },
    //     xFormat: "%Y-%m-%d %H:%M",
    //     columns: [
    //       ["x3", "2019-08-20 07:20", "2019-08-20 09:00", "2019-08-20 12:20"],
    //       //            ['x', '20130101', '20130102', '20130103', '20130104', '20130105', '20130106'],
    //       ["y3", 1, 4, 9]
    //     ],
    // };
    // this.handler.$emit("dispatch", chart => {
    //   chart.load(data3);
    // });

    // const data2 = {
    //   json: this.data2,
    //   keys: {
    //     x: "x",
    //     value: ["value"]
    //   },
    //   xFormat: "%Y-%m-%d %H:%M",
    //   names: ["b"]
    // };
    // this.handler.$emit("dispatch", chart => {
    //   chart.load(data2);
    // });
    // d3.select("#chart").selectAll(".c3-circle-0").on("contextmenu", function(d, i, c, e) {
    //         d3.event.preventDefault();
    // var vals = chart.data().map(function(series) {
    //   var name = series.id;
    //   return {
    //     name: name,
    //     value: chart.data.values(name)[d.x]
    //   };
    // });
    // });
  }
};
</script>

<style>
.c3-region.regionY {
  fill: blue;
}
.c3-region.regionY2 {
  fill: white;
}
</style>

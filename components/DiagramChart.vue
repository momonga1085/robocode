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
        ["x1", "2018-03-21", "2018-03-22", "2018-03-29", "2018-03-30"],
        ["data1", 1, 2, 4, 5],
        [
          "x2",
          "2018-03-22",
          "2018-03-23",
          "2018-03-24",
          "2018-03-25",
          "2018-03-27"
        ],
        ["data2", 1, 2, 3, 4, 5],
        ["x3", "2018-03-23", "2018-03-24", "2018-03-25"],
        ["data3", 1, 2, 3]
      ],
      xs: {
        data1: "x1",
        data2: "x2",
        data3: "x3"
      }
    };
  },
  mounted() {
    let options = {
      data: {
        xs: this.xs,
        xFormat: "%Y-%m-%d",
        columns: this.columns_data
      },
      color: {
        pattern: ["#1f77b4"]
      },
      legend: {
        show: false
      },
      axis: {
        // rotated: true,
        y: {
          tick: {
            format: function(d) {
              console.log(dataMap);
              return dataMap[d];
            }
          },
          max: 0,
          min: 6
        },
        x: {
          type: "timeseries",
          tick: {
            format: "%Y-%m-%d"
          },
          max: "2018-03-22",
          min: "2018-03-28"
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

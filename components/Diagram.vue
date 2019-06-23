<template id="line">
  <div
    id="chart--wrapper"
    ref="chartWrapper"
  >
    <svg
      :width="width"
      :height="height"
    >
      <g
        :id="chartName + '-x-axis'"
        class="axis x-axis"
        :transform="transformX()"
      ></g>

      <g
        :id="chartName + '-y-axis'"
        class="axis y-axis"
        :transform="transformY()"
      ></g>

      <clipPath id="clip-path">
        <rect
          id="clip-rect"
          x=0
          y=0
          :width="width"
          :height="height - 100"
        ></rect>
      </clipPath>

      <g
        :id="chartName + '-data'"
        class="plot-area"
        v-for="(lineData, lineIndex) in chartData"
        :key="lineIndex"
      >
        <path
          :id="chartName + '-line'"
          class="line"
          :transform="transformY()"
          :d="drawLine(formatData(lineData))"
          fill="none"
          stroke="black"
          stroke-width="2px"
          clip-path="url(#clip-path)"
        ></path>

        <circle
          :id="chartName + '-point'"
          v-for="(data, dataIndex) in formatData(lineData)"
          :key="dataIndex"
          :cx="getCX(data)"
          :cy="getCY(data)"
          :transform="transformY()"
          clip-path="url(#clip-path)"
          r=5
        >
        </circle>
      </g>

    </svg>
  </div>
</template>

<script>
import * as d3 from "d3";
import XAxis from "./XAxis";

// var width = this.$refs.chartWrapper.clientWidth;
// width = contents.node().clientWidth;
// height = contents.node().clientHeight;
var width = 1000;
var height = 300;
var margin = { top: 50, left: 50, bottom: 50, right: 50 };

export default {
  props: {
    chartData: Array
  },
  components: {
    "x-axis": XAxis
  },
  data: function() {
    return {
      chartName: "diagram",
      width: width,
      height: height,
      xScale: this.getScaleX(),
      yScale: this.getScaleY()
      // margin: { top: 50, left: 50, bottom: 50, right: 50 },
    };
  },
  methods: {
    transformX() {
      var cx = margin.left;
      var cy = this.height - margin.top;
      return "translate(" + cx + "," + cy + ")";
    },
    transformY() {
      var cx = margin.left;
      var cy = margin.top;
      return "translate(" + cx + "," + cy + ")";
    },
    getScaleX() {
      var xScale = d3
        .scaleTime()
        .domain([
          new Date("2007-10-01 0:00:00"),
          new Date("2007-10-31 0:00:00")
        ])
        .range([0, this.width-margin.right-margin.left]);
      return xScale;
    },
    getScaleY() {
      var labels = [
        { id: 1, name: "ばなな" },
        { id: 2, name: "りんご" },
        { id: 3, name: "ぶどう" }
      ];

      var yScale = d3
        .scaleLinear()
        .domain([
          7,
          0
        ])
          // d3.max(this.formatData(this.chartData), function(d) {
          //   return d.value;
          // })
        // ])
        // .scaleQuantile()
        // .domain(["a", "b"])
        // .scaleBand()
        // .domain(
        //   labels.map(function(d) {
        //     return d.name;
        //   }))
        // )
        // .rangeRound([0, this.height])
      .range([this.height - margin.top - margin.bottom, 0]);
      return yScale;
    },
    drawAxisX() {
      var xAxis = d3
        .axisBottom(this.getScaleX())
        .tickFormat(d3.timeFormat("%m/%d"))

      var xAxisElem = d3.select("#" + this.chartName + "-x-axis");

      xAxisElem.call(xAxis);
    },
    drawAxisY() {
      var map = {
        1: "工程1",
        2: "工程2",
        3: "工程3",
        4: "工程4",
        5: "工程5",
        6: "工程6",
      }
      var yAsis = d3.axisLeft(this.getScaleY())
      .tickFormat(function(d) {
        return map[d]
        return d
      })
      .ticks(8);

      var yAxisElem = d3.select("#" + this.chartName + "-y-axis");

      yAxisElem.call(yAsis);
    },
    drawLine(data) {
      let color = d3.rgb("#85a7cc");

      var xScale = this.getScaleX();
      var yScale = this.getScaleY();

      var line = d3
        .line()
        .x(function(d) {
          // console.log(xScale(d.date))
          return xScale(d.date);
        })
        .y(function(d) {
          return yScale(d.value);
        });

      return line(data);

      // var lineElem = d3.select("#" + this.chartName + "-line");
      // lineElem
      //   .datum(this.formatData(this.chartData))
      //   .attr("d", line)
      //   .attr("fill", "none")
      //   .attr("stroke", color)
      //   .attr("stroke-width", "2px")
      //   .attr("clip-path", "url(#clip-path)");
      // .attr("x", margin.left)
      // .attr("y", margin.bottom);

      // var g = d3.select("#" + this.chartName + "-data");
      // g.selectAll("circle")
      //   .data(this.formatData(this.chartData))
      //   .enter()
      //   .append("circle")
      //   .attr("cx", line.x())
      //   .attr("cy", line.y())
      //   .attr("fill", 1)
      //   .attr("r", 4)
      //   .attr("transform", this.transformY());
      // .attr({
      //   cx: line.x(),
      //   cy: line.y(),
      //   r: 5,
      //   fill: "#000",
      //   transform: this.transformY()
      // })
      // .attr("transform", this.transformY());
    },
    drawPoint() {
      var xScale = this.getScaleX();
      var yScale = this.getScaleY();

      var line = d3
        .line()
        .x(function(d) {
          return xScale(d.date);
        })
        .y(function(d) {
          return yScale(d.value);
        });

      var $point = d3.selectAll("#" + this.chartName + "-point");
      $point
        .data(this.formatData(this.chartData))
        .attr("cx", function(d) {
          return xScale(d.date);
        })
        .attr("cy", function(d) {
          return yScale(d.value);
        })
        .attr("r", 5)
        .attr("transform", this.transformY())
        .attr("fill", 1)
        .attr("r", 4);
    },
    getCX(d) {
      var xScale = this.getScaleX();
      return xScale(d.date);
    },
    getCY(d) {
      var yScale = this.getScaleY();
      return yScale(d.value);
    },
    handleResize() {
      this.width = this.$refs.chartWrapper.clientWidth - 200;
      this.height = this.$refs.chartWrapper.clientHeight;
      this.drawAxisX();
      this.drawAxisY();
      console.log(this.height)
      // this.drawLine();
      // this.drawPoint();
    },
    drawChart() {
      this.drawAxisX();
      this.drawAxisY();
      // this.drawLine();
      // this.drawPoint();
    },
    formatData(chartData) {
      let timeparser = d3.timeParse("%Y-%m-%d");
      var formatedChartData = chartData.map(function(d) {
        return { date: timeparser(d.date), value: d.value };
      });
      return formatedChartData;
    }
  },

  mounted() {
    window.addEventListener("resize", this.handleResize);
    this.width = this.$refs.chartWrapper.clientWidth - 50;
    this.height = this.$refs.chartWrapper.clientHeight - 50;
    this.drawChart();
  },

  beforeDestroy: function() {
    window.removeEventListener("resize", this.handleResize);
    this.drawChart();
  }
};
</script>

<style>
.axis .domain {
  stroke: #ccc;
  position: relative;
}
.axis .tick line {
  stroke: #ccc;
}

.plot-area {
  margin-left: 50px;
}

.axis .tick text {
  fill: #223f4b;
  font-family: "Helvetica Neue", "Roboto", "ヒラギノ角ゴ ProN",
    "Hiragino Kaku Gothic ProN", "游ゴシック体", "Yu Gothic", YuGothic, Verdana,
    Meiryo, sans-serif !important;
  font-size: 0.7rem;
}
#chart--wrapper {
  width: 100%;
  height: 100%;
}
</style>



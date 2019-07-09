<template id="line">
  <div
    id="chart--wrapper"
    ref="chartWrapper"
  >
    <svg
      :id="chartName + '-svg'"
      :width="width + margin.left + margin.right"
      :height="height  + margin.top + margin.bottom"
    >

      <g :transform="transform(margin.left, margin.top )">

        <g
          :id="chartName + '-x-axis'"
          class="axis x-axis"
          :transform="transform(0, height)"
        ></g>

        <g
          :id="chartName + '-y-axis'"
          class="axis y-axis"
        ></g>

        <clipPath
          id="clip-path"
        >
          <rect
            id="clip-rect"
            :width="width"
            :height="height"
          ></rect>
          <!-- <rect
            id="clip-rect-2"
            :width="width"
            :height="height"
            :transform="transform(-5, 0)"
          ></rect> -->
        </clipPath>

        <clipPath
          id="clip-path"
        >
          <rect
            id="clip-rect-2"
            :width="width"
            :height="height"
          ></rect>
          <rect
            id="clip-rect-2"
            :width="width"
            :height="height"
            :transform="transform(-5, 0)"
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
            :class="chartName + '-line'"
            :d="drawLine(formatData(lineData.values))"
            clip-path="url(#clip-path)"
            @mouseover="mouseoverLine(lineData, $event)"
          ></path>

          <circle
            :id="chartName + '-point'"
            :class="chartName + '-point'"
            v-for="(data, dataIndex) in formatData(lineData.values)"
            :key="dataIndex"
            @mouseover="mouseoverPoint(data, $event)"
            :cx="getCX(data)"
            :cy="getCY(data)"
            clip-path="url(#clip-path-2)"
            r=2
          >
          </circle>
        </g>
      </g>
    </svg>

    <div
      :id="chartName + '-tooltip'"
      class="tooltip"
    ></div>

  </div>
</template>

<script>
import * as d3 from "d3";
import * as d3Tip from "d3-tip";
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
      yScale: this.getScaleY(),
      margin: { top: 50, left: 50, bottom: 50, right: 50 }
      // margin: { top: 50, left: 50, bottom: 50, right: 50 },
    };
  },
  methods: {
    transform(x, y) {
      return "translate(" + x + "," + y + ")";
    },
    getScaleX() {
      var xScale = d3
        .scaleTime()
        .domain([
          new Date("2007-09-30 0:00:00"),
          new Date("2007-10-31 0:00:00")
        ])
        .range([0, this.width]);
      return xScale;
    },
    getScaleY() {
      var labels = [
        { id: 1, name: "工程1" },
        { id: 2, name: "工程2" },
        { id: 3, name: "工程3" },
        { id: 3, name: "工程4" },
        { id: 3, name: "工程5" },
        { id: 3, name: "工程6" },
        { id: 3, name: "工程7" }
      ];

      var yScale = d3
        .scaleBand()
        .rangeRound([0, this.height])
        .padding(1)
        .domain(
          labels.map(function(d) {
            return d.name;
          })
        );
      return yScale;
    },
    drawAxisX() {
      var xAxis = d3
        .axisBottom(this.getScaleX())
        .tickFormat(d3.timeFormat("%m/%d"));

      var xAxisElem = d3.select("#" + this.chartName + "-x-axis");

      xAxisElem.call(xAxis);
    },
    drawAxisY() {
      var yAsis = d3.axisLeft(this.getScaleY());
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
      // this.height = this.$refs.chartWrapper.clientHeight;
      this.drawAxisX();
      this.drawAxisY();
      // this.drawLine();
      // this.drawPoint();
    },
    // mousemove() {
    //   d3.select(".tooltip")
    //     .style("top", d3.event.pageY - 20 + "px")
    //     .style("left", d3.event.pageX + 10 + "px");
    // },
    drowToolTip() {
      var tooptipElm = d3.select("#" + this.chartName + "-tooltip");

      var targetLineClass = "." + this.chartName + "-line";
      var targetPointClass = "." + this.chartName + "-point";

      d3.selectAll(targetLineClass + ", " + targetPointClass)
        .on("mousemove", function(d) {
          d3.select(".tooltip")
            .style("top", d3.event.pageY - 20 + "px")
            .style("left", d3.event.pageX + 10 + "px");
        })
        .on("mouseout", function() {
          d3.select(".tooltip")
            .style("visibility", "hidden");
        });
    },
    drawChart() {
      this.drawAxisX();
      this.drawAxisY();
      this.drowToolTip();
      // this.drawLine();
      // this.drawPoint();
    },
    formatData(chartData) {
      let timeparser = d3.timeParse("%Y-%m-%d");
      console.log(chartData);
      var formatedChartData = chartData.map(function(d) {
        // console.log(d)
        return { date: timeparser(d.date), value: d.value };
      });
      return formatedChartData;
    },
    mouseoverLine(lineData, e) {
      d3.select(".tooltip")
        .attr("fill", "rgb(39, 250, 102)")
        .attr("display", "block")
        .style("visibility", "visible")
        .html("<div>シリアルNO：" + lineData.serialNo + "</div>");
    },
    mouseoverPoint(data, e) {
      d3.select(".tooltip")
        .attr("fill", "rgb(39, 250, 102)")
        .attr("display", "block")
        .style("visibility", "visible")
        .html("<div>シリアルNO：" + data.date + "</div>");
    },
  },

  mounted() {
    window.addEventListener("resize", this.handleResize);
    this.width =
      this.$refs.chartWrapper.clientWidth - margin.left - margin.right;
    // this.height = this.$refs.chartWrapper.clientHeight - 50;
    this.height = 200 + margin.top - margin.bottom;
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
  /* position: relative; */
}
.axis .tick line {
  stroke: #ccc;
}

.plot-area {
  margin-left: 50px;
}
/* 
.axis .tick text {
  fill: #223f4b;
  font-family: "Helvetica Neue", "Roboto", "ヒラギノ角ゴ ProN",
    "Hiragino Kaku Gothic ProN", "游ゴシック体", "Yu Gothic", YuGothic, Verdana,
    Meiryo, sans-serif !important;
  font-size: 0.7rem;
} */
#chart--wrapper {
  width: 100%;
  height: 100%;
}

#diagram-line {
  stroke: #4682b4;
  fill: none;
  stroke-width: 2px;
}

#diagram-line:hover {
  stroke: #00008b;
}

#diagram-point {
  stroke: black;
  fill:white;
  r: 4
}

#diagram-point:hover {
  fill:black;
}

.tooltip {
  position: absolute;
  text-align: center;
  width: auto;
  height: auto;
  padding: 5px;
  font: 10px;
  background: yellow;
  visibility: hidden;
}

.bar:hover {
  fill: Brown;
}
</style>



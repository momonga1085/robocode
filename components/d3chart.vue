<template id="line">
  <svg :width="width" :height="height" :viewBlx="'0 0 ' + width + ' ' + height">

    <transition-group name="line" tag="g">
      <path class="line" :style="lineStyle(i)"
            v-for="(line, i) in lines" :key="i"
            :d="d(line)" :transform="transform()" 
            :x-scale="scale('Left')" :y-scale="scale('Bottom')">
      </path>
    </transition-group>

    <g :transform="transform()">
      <g class="axis">
        <g :id="'axis' + orient" :transform="transform(orient)"
           v-for="(orient, i) in axis" :key="i"></g>
      </g>
    </g>

  </svg>
</template>

<script>
import * as d3 from 'd3';
export default {
    template: "#line",
  props: ["lines"],
  data: function () {
    return {
      width: 600, height: 300, margin: 20, axis: ["Left", "Bottom"]
    }
  },
  mounted: function () {
    for (var i = 0; this.axis.length > i; i++) {
      var o = this.axis[i];
      d3.select("#axis" + o).call(d3["axis" + o](this.scale(o)));
    }
  },
  computed: {
    viewBox: function () {
      return [0, 0, this.width, this.height].join(" ");
    },
    xArray: function () {
      var arr = [];
      this.lines.forEach(function (l) {
        arr = arr.concat(l.map(function (d) { return d[0]; }));
      });
      return arr;
    },
    yArray: function () {
      var arr = [];
      this.lines.forEach(function (l) {
        arr = arr.concat(l.map(function (d) { return d[0]; }));
      });
      return arr;
    }
  },
  methods: {
    lineStyle: function (i) {
      return { stroke: this.stroke(i) };
    },
    scale: function (o) {
      var linear = d3.scaleLinear();
      if (["Left", "Right"].indexOf(o) > -1) {
        return linear.domain([10, 0]).range([0, this.height - this.margin * 2]);
      } else if (["Top", "Bottom"].indexOf(o) > -1) {
        return linear.domain(d3.extent(this.xArray)).range([0, this.width - this.margin * 2]);
      }
      return linear;
    },
    transform: function (o) {
      if (o == undefined) {
        return "translate(" + this.margin + "," + this.margin / 2 + ")";
      }

      var x = (o == "Right") ? this.width : 0;
      var y = (o == "Bottom") ? this.height - this.margin * 2 : 0;
      return "translate(" + x + "," + y + ")";
    },
    d: function (l) {
      var xScale = this.scale("Bottom");
      var yScale = this.scale("Left");
      var line = d3.line();
      line
        .curve(d3.curveMonotoneX)
        .x(function (d) { return xScale(d[0]); })
        .y(function (d) { return yScale(d[1]); });
      return line(l);
    },
    stroke: function (i) {
      return d3.schemeCategory10[i];
    }
  }
}
</script>

<style>
    .line {
    fill: none;
    stroke-width: 2px;
    transition: all 1s;
    }
    .line-move {
    transition: transform 1s;
    }
</style>

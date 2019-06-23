<template>
<div>
<head>
  <link href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons' rel="stylesheet">
</head>
  <v-app>
    <!-- <side-menu></side-menu>
    <table2></table2> -->
    <!-- <diagram :chartData="chartData"></diagram> -->
    
     <!-- <chart></chart> -->
     <diagram-chart></diagram-chart>
     <kpi-chart></kpi-chart>
  </v-app>
  </div>
</template>

<script>
import SideMenu from './components/SideMenu'
// import d3chart from './components/d3chart'
import table from './components/table'
import diagram from './components/Diagram'
import DiagramChart from './components/DiagramChart'
import KpiChart from './components/KpiChart'

var chartData = [
  [
    {
      date: "2007-09-28",
      value: 1
    },
    {
      date: "2007-10-02",
      value: 2
    },
    {
      date: "2007-10-03",
      value: 3
    },
    {
      date: "2007-10-04",
      value: 4
    },
    // {
    //   date: "2008-01-01",
    //   value: 33
    // },
  ],
  [
    {
      date: "2007-02-01",
      value: 10
    },
    {
      date: "2007-10-01",
      value: 30
    },
    {
      date: "2007-10-02",
      value: 55
    },
    {
      date: "2007-10-08",
      value: 50
    },
  ]
];

export default {
  name: 'app',
  data: function () {
    return {
      lines : [[1,2],[10,23],[222,323]],
      chartData: chartData,
    }
  },
  methods:{
    select (val) {
      console.log(val)
    }
  },
  components: {
    "side-menu" : SideMenu,
    // "chart" : d3chart,
    "table2": table,
    "diagram" : diagram,
    "diagram-chart" : DiagramChart,
    "kpi-chart":KpiChart
  },created () {
      var lines = [];
        for (var i = 0; i < 2; i++) {
          var line = [];
          for (var j = 0; j < 11; j++) {
            line.push([j, Math.floor(Math.random() * 10)
            ]);
          }
          lines.push(line);
        }
        this.lines = lines;
        console.log(lines)
    }
  
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>

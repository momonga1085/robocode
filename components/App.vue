<template>
  <div>

    <head>
      <link
        href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons'
        rel="stylesheet"
      >
    </head>
    <v-app>
      <!-- <side-menu></side-menu>
    <table2></table2> -->
      <!-- <diagram :chartData="chartData"></diagram> -->
      <BarChart :chartData="chartData"></BarChart>
      <!-- <Heatmap :chartData="chartData"></Heatmap> -->
      <!-- <chart></chart> -->
      <!-- <diagram-chart></diagram-chart> -->
      <!-- <kpi-chart></kpi-chart> -->
    </v-app>
  </div>
</template>

<script>
import SideMenu from "./components/SideMenu";
// import d3chart from './components/d3chart'
import table from "./components/table";
import diagram from "./components/Diagram";
import diagram2 from "./components/d3/DiagramChart";
import DiagramChart from "./components/DiagramChart";
import KpiChart from "./components/KpiChart";
import Heatmap from "./components/Heatmap";
import BarChart from "./components/BarChart";

// var chartData = [
//   {
//     serialNo: "aaa",
//     values: [
//       {
//         date: "2007-10-15 08:15",
//         value: "工程1",
//         z: 10,
//         message: "aaaa"
//       },
//       {
//         date: "2007-10-15 08:30",
//         value: "工程2",
//         z: 60,
//       },
//       {
//         date: "2007-10-15 08:00",
//         value: "工程3",
//         z: 30,
//       },
//       {
//         date: "2007-10-15 08:45",
//         value: "工程4",
//         z: 100,
//       },
//       {
//         date: "2007-10-15 09:00",
//         value: "工程5",
//         z: 20,
//       }
//     ]
//   },
// ];

// var chartData = [
//   {
//     serialNo: "aaa",
//     values: [
//       {
//         date: "2007-09-28",
//         value: "工程1",
//         z: 10,
//         message: "aaaa"
//       },
//       {
//         date: "2007-10-02",
//         value: "工程2",
//         z: 60,
//       },
//       {
//         date: "2007-10-03",
//         value: "工程3",
//         z: 30,
//       },
//       {
//         date: "2007-10-04",
//         value: "工程4",
//         z: 100,
//       },
//       {
//         date: "2008-01-01",
//         value: "工程5",
//         z: 20,
//       }
//     ]
//   },
//   {
//     serialNo: "bbb",
//     values: [
//       {
//         date: "2007-09-27",
//         value: "工程1",
//         message: "aaaa",
//         z: 60,
//       },
//       {
//         date: "2007-09-30",
//         value: "工程1",
//         message: "aaaa",
//         z: 60,
//       },
//       {
//         date: "2007-10-01",
//         value: "工程2",
//         z: 60,
//       },
//       {
//         date: "2007-10-02",
//         value: "工程3",
//         z: 60,
//       },
//       {
//         date: "2007-10-03",
//         value: "工程4",
//         z: 60,
//       },
//       {
//         date: "2007-10-04",
//         value: "工程5",
//         z: 60,
//       },
//       {
//         date: "2007-10-31",
//         value: "工程6",
//         z: 60,
//       }
//     ]
//   }
// ];

var chartData = [
  {
    serialNo: "aaa",
    values: [
      {
        date: "1",
        value: "工程1",
        z: 10,
        message: "aaaa"
      },
      {
        date: "2",
        value: "工程3",
        z: 10,
        message: "aaaa"
      },
    ]
  },
  {
    serialNo: "aaa",
    values: [
      {
        date: "1",
        value: "工程2",
        z: 10,
        message: "aaaa"
      },
      {
        date: "2",
        value: "工程2",
        z: 10,
        message: "aaaa"
      },
    ]
  },
  {
    serialNo: "aaa",
    values: [
      {
        date: "1",
        value: "工程2",
        z: 10,
        message: "aaaa"
      },
      {
        date: "2",
        value: "工程2",
        z: 10,
        message: "aaaa"
      },
    ]
  }
]

export default {
  name: "app",
  data: function() {
    return {
      lines: [[1, 2], [10, 23], [222, 323]],
      chartData: chartData
    };
  },
  methods: {
    select(val) {
      console.log(val);
    }
  },
  components: {
    "side-menu": SideMenu,
    // "chart" : d3chart,
    table2: table,
    diagram: diagram,
    "diagram-chart": DiagramChart,
    "kpi-chart": KpiChart,
    Heatmap : Heatmap,
    BarChart: BarChart
  },
  created() {
    var lines = [];
    for (var i = 0; i < 2; i++) {
      var line = [];
      for (var j = 0; j < 11; j++) {
        line.push([j, Math.floor(Math.random() * 10)]);
      }
      lines.push(line);
    }
    this.lines = lines;
    console.log(lines);
  }
};
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

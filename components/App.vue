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
      <!-- <BarChart :chartData="chartData"></BarChart> -->
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
import { constants } from "crypto";

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
    lineNo: "aaa",
    values: [
      {
        x: "19",
        y: "工程1",
        z: 10,
        message: "aaaa"
      },
      {
        x: "2",
        y: "工程3",
        z: 10,
        message: "aaaa"
      }
    ]
  },
  {
    lineNo: "bbb",
    values: [
      {
        x: "6",
        y: "工程2",
        z: 10,
        message: "aaaa"
      },
      {
        x: "221",
        y: "工程2",
        z: 10,
        message: "aaaa"
      }
    ]
  },
  {
    lineNo: "ccc",
    values: [
      {
        x: "87",
        y: "工程2",
        z: 10,
        message: "aaaa"
      },
      {
        x: "53",
        y: "工程2",
        z: 10,
        message: "aaaa"
      }
    ]
  }
];

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
    Heatmap: Heatmap,
    BarChart: BarChart
  },
  created() {
    console.log("--before--");
    console.log(this.chartData);

    let transformedData = [];
    let lineData = {};

    for (let i = 0; i < this.chartData.length; i++) {
      let lineNo = this.chartData[i].lineNo;
      for (let j = 0; j < this.chartData[i].values.length; j++) {
        let values = this.chartData[i].values[j];
        console.log(values.y);
        if (lineData[values.y] == null) {
          lineData[values.y] = { values: [] };
        }
        let data = { x: values.x, y: lineNo };
        lineData[values.y].values.push(data);
      }
    }
    console.log("--after--");
    console.log(lineData);
    console.log("--sort--");
    for (let key in lineData) {
      lineData[key].values.sort(function(a, b) {
        console.log(a)
        console.log(b)
        console.log("")
        if (a.x > b.x) return -1;
        if (a.x < b.x) return 1;
        return 0;
      });
      console.log(lineData[key].values);
    }
    console.log(lineData);
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

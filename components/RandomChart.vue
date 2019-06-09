<template>
  <div>
    <button @click.stop="createChartData">追加</button>
    <line-chart
      :chartData="chartData"
      :options="chartOptions"
    ></line-chart>
  </div>
</template>

<script>
import LineChart from "./chart.vue";

const FONT_COLOR = "rgba(0, 0, 0, 1)";
const GRID_LINES_SETTING = {
  display: true,
  drawOnChartArea: false,
  color: "rgba(255, 255, 255, .5)",
  zeroLineColor: "rgba(255, 255, 255, 1)"
};
export default {
  name: "ChartLineSample",
  components: {
    LineChart
  },
  data: function() {
    return {
      counter: 0,
      btnName: "追加",
      chartData: {
        labels: ["1", "2", "3", "4", "5"],
        datasets: [
          {
            yAxisID: "yAxis_1",
            type: "line",
            label: "湿度",
            backgroundColor: "#6090EF",
            borderColor: "#6090EF",
            fill: false,
            data: [1, 20, 30, 40, 5]
          },
          {
            yAxisID: "yAxis_2",
            type: "bar",
            label: "温度",
            backgroundColor: "#F87979",
            borderColor: "#F87979",
            fill: false,
            data: [1, 2, 3, 4, 5]
          }
        ]
      },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false,
        layout: {
          padding: {
            top: 20,
            left: 20,
            bottom: 20,
            right: 20
          }
        },
        legend: {
          fullWidth: false,
          labels: {
            boxWidth: 20,
            fontColor: FONT_COLOR
          }
        },
        scales: {
          xAxes: [
            {
              gridLines: GRID_LINES_SETTING,
              ticks: {
                autoSkip: true,
                fontColor: FONT_COLOR,
                fontSize: 14
              },
              scaleLabel: {
                display: true,
                fontColor: FONT_COLOR,
                labelString: "月"
              }
            }
          ],
          yAxes: [
            {
              id: "yAxis_1",
              type: "linear",
              gridLines: GRID_LINES_SETTING,
              scaleLabel: {
                display: true,
                fontColor: FONT_COLOR,
                labelString: "湿度(%)"
              },
              ticks: {
                autoSkip: true,
                fontColor: FONT_COLOR,
                fontSize: 14,
                min: 0,
                max: 100
              }
            },
            {
              id: "yAxis_2",
              type: "linear",
              gridLines: GRID_LINES_SETTING,
              scaleLabel: {
                display: true,
                fontColor: FONT_COLOR,
                labelString: "温度(℃)"
              },
              ticks: {
                autoSkip: true,
                fontColor: FONT_COLOR,
                fontSize: 14,
                min: 0,
                max: 40
              },
              position: "right"
            }
          ]
        }
      }
    };
  }
};
</script>

<style>
</style>
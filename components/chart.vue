<script>
import { Line, Bar, mixins} from "vue-chartjs";
const { reactiveProp } = mixins

export default {
  mixins: [Line, Bar,reactiveProp],
  name: 'LineChart',
  props: [
    "chartData",
    "options"
  ],
  mounted () {
    this.renderChart(this.chartData, this.options);
  }
}
</script>

<style>

</style>
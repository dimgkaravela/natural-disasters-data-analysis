package Statistics;

import dom2app.IMeasurementVector;
import domain.SingleMeasureRequest;
import org.apache.commons.math3.util.Pair;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class StatisticsCalc {

    public SingleMeasureRequest calculateStatistics(String requestName, IMeasurementVector vector) {
    	List<Pair<Integer, Integer>> measurements = vector.getMeasurements();

        if (measurements.isEmpty()) {
            System.out.println("StatisticsCalc - No measurements available for statistics.");
            return null;
        }

        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Pair<Integer, Integer> measurement : measurements) {
            stats.addValue(measurement.getValue());
        }

        int count = (int) stats.getN();
        double gMean = stats.getGeometricMean();
        double mean = stats.getMean();
        int median = (int) stats.getPercentile(50);
        int max = (int) stats.getMax();
        double kurtosis = stats.getKurtosis();
        double stdev = stats.getStandardDeviation();
        double sum = stats.getSum();

        System.out.println("Calculating Statistics for request: " + requestName);
        String statsString = String.format("Count: %d\nMin: %.0f\nGMean: %.2f\nMean: %.14f\nMedian: %d\nMax: %d\nKurtosis: %.14f\nStdev: %.14f\nSum: %.0f",
                                           count, stats.getMin(), gMean, mean, median, max, kurtosis, stdev, sum);
        
        System.out.println("Calculated Statistics: " + statsString);
        SingleMeasureRequest resultRequest = new SingleMeasureRequest(requestName, vector, statsString, null);
        System.out.println("Created resultRequest with stats: " + resultRequest.getDescriptiveStatsString());

        return resultRequest;
    }

   
}
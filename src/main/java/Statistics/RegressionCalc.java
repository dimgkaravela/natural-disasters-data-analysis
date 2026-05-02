package Statistics;

import dom2app.IMeasurementVector;
import domain.SingleMeasureRequest;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.Pair;
import java.util.List;

public class RegressionCalc {

    public SingleMeasureRequest calculateRegression(String requestName, IMeasurementVector vector) {
        List<Pair<Integer, Integer>> measurements = vector.getMeasurements();
        if (measurements.isEmpty()) {
            System.out.println("RegressionCalc - No measurements available for regression.");
            return null;
        }

        SimpleRegression regression = new SimpleRegression();
        for (Pair<Integer, Integer> measurement : measurements) {
            regression.addData(measurement.getKey(), measurement.getValue());
        }

        double intercept = regression.getIntercept();
        double slope = regression.getSlope();
        double slopeError = regression.getSlopeStdErr();

        
        String trend = determineTrend(slope, slopeError);

        String regressionResult = String.format(
            "Intercept: %.14f\nSlope: %.14f\nSlope Error: %.14f\nTrend: %s", 
            intercept, slope, slopeError, trend
        );

        System.out.println("Calculated Regression: " + regressionResult);

        SingleMeasureRequest resultRequest = new SingleMeasureRequest(requestName, vector, null,  regressionResult);
        System.out.println("Setting regression in SingleMeasureRequest: " + resultRequest.getRegressionResultString());
        return resultRequest;
    }

    private String determineTrend(double slope, double slopeError) {
        if (Math.abs(slope) <= slopeError) {
            return "Tendency stable";
        } else if (slope > 0) {
            return "Increasing trend";
        } else {
            return "Decreasing trend";
        }
    }
}
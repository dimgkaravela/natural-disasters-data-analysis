package StatisticsTest;

import org.junit.Test;

import Statistics.RegressionCalc;

import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;
import dom2app.IMeasurementVector;
import domain.MeasurementVector;
import domain.SingleMeasureRequest;
import org.apache.commons.math3.util.Pair;

public class RegressionTestHappy {

    private RegressionCalc regressionCalc;
    private IMeasurementVector mockVector;

    @Before
    public void setUp() {
        regressionCalc = new RegressionCalc();

        // Mock data setup
        List<Pair<Integer, Integer>> measurements = Arrays.asList(new Pair<>(1980, 100), new Pair<>(1985, 150), new Pair<>(1990, 200));
        mockVector = new MeasurementVector("Greece", "Earthquake", measurements);
    }

    @Test
    public void testSuccessfulRegressionComputation() {
        String requestName = "TestRequest";

        SingleMeasureRequest result = regressionCalc.calculateRegression(requestName, mockVector);

        assertNotNull("Result should not be null", result);
        assertFalse("Regression string should not be empty", result.getRegressionResultString().isEmpty());
        System.out.println("Calculated Regression: " + result.getRegressionResultString());
    }
}

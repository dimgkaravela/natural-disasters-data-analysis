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

public class RegressionTestRainy {

    private RegressionCalc regressionCalc;
    private IMeasurementVector mockEmptyVector;

    @Before
    public void setUp() {
        regressionCalc = new RegressionCalc();

        // Mock empty data setup
        List<Pair<Integer, Integer>> measurements = new ArrayList<>();
        mockEmptyVector = new MeasurementVector("Greece", "Earthquake", measurements);
    }

    @Test
    public void testUnsuccessfulRegressionComputation() {
        String requestName = "TestRequest";

        SingleMeasureRequest result = regressionCalc.calculateRegression(requestName, mockEmptyVector);

        assertNull("Result should be null due to empty measurements", result);
    }
}

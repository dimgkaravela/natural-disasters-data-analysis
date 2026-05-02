package StatisticsTest;

import org.junit.Test;

import Statistics.StatisticsCalc;

import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;
import dom2app.IMeasurementVector;
import domain.MeasurementVector;
import domain.SingleMeasureRequest;
import org.apache.commons.math3.util.Pair;

public class StatisticsTestRainy{

    private StatisticsCalc statisticsCalc;
    private IMeasurementVector mockEmptyVector;

    @Before
    public void setUp() {
        statisticsCalc = new StatisticsCalc();

        // Mock empty data setup
        List<Pair<Integer, Integer>> measurements = new ArrayList<>();
        mockEmptyVector = new MeasurementVector("Greece", "Earthquake", measurements);
    }

    @Test
    public void testUnsuccessfulStatisticsComputation() {
        String requestName = "TestRequest";

        SingleMeasureRequest result = statisticsCalc.calculateStatistics(requestName, mockEmptyVector);

        assertNull("Result should be null due to empty measurements", result);
    }
}

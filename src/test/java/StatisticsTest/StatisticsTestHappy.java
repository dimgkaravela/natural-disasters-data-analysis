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

public class StatisticsTestHappy {

    private StatisticsCalc statisticsCalc;
    private IMeasurementVector mockVector;

    @Before
    public void setUp() {
        statisticsCalc = new StatisticsCalc();

        // Mock data setup
        List<Pair<Integer, Integer>> measurements = Arrays.asList(new Pair<>(1980, 5), new Pair<>(1985, 10), new Pair<>(1990, 15));
        mockVector = new MeasurementVector("Greece", "Earthquake", measurements);
    }

    @Test
    public void testSuccessfulStatisticsComputation() {
        String requestName = "TestRequest";

        SingleMeasureRequest result = statisticsCalc.calculateStatistics(requestName, mockVector);

        assertNotNull("Result should not be null", result);
        assertFalse("Statistics string should not be empty", result.getDescriptiveStatsString().isEmpty());
        System.out.println("Calculated Statistics: " + result.getDescriptiveStatsString());
    }
}

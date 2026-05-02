package SearchTests;

import org.junit.Test;

import Search.Search;

import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import domain.MeasurementVector;
import org.apache.commons.math3.util.Pair;

public class SearchYearsHappy {

    private Search search;
    private List<IMeasurementVector> mockData;
    private Map<String, ISingleMeasureRequest> mockRequestMap;

    @Before
    public void setUp() {
        mockData = new ArrayList<>();
        mockRequestMap = new HashMap<>();

        // Mock data setup
        List<Pair<Integer, Integer>> measurements = Arrays.asList(new Pair<>(1980, 5), new Pair<>(1985, 10), new Pair<>(1990, 15));
        MeasurementVector vector = new MeasurementVector("Greece", "Earthquake", measurements);
        mockData.add(vector);

        search = new Search(mockData, mockRequestMap);
    }

    @Test
    public void testSuccessfulSearchWithinTimeRange() {
        String requestName = "TestRequest";
        String countryName = "Greece";
        String disasterType = "Earthquake";
        int startYear = 1983;
        int endYear = 1987;

        ISingleMeasureRequest result = search.findSingleCountryIndicatorYearRange(requestName, countryName, disasterType, startYear, endYear, mockData);

        assertNotNull("Search should return a result", result);
        assertFalse("Result should contain measurements", result.getAnswer().getMeasurements().isEmpty());
        assertTrue("Measurements should be within the time range",
            result.getAnswer().getMeasurements().stream().allMatch(measurement -> 
                measurement.getKey() >= startYear && measurement.getKey() <= endYear));
    }
}

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

public class SearchTestHappy {

    private Search search;
    private List<IMeasurementVector> mockData;
    private Map<String, ISingleMeasureRequest> mockRequestMap;

    @Before
    public void setUp() {
        mockData = new ArrayList<>();
        mockRequestMap = new HashMap<>();

        // Mock data setup
        List<Pair<Integer, Integer>> measurements = Arrays.asList(new Pair<>(1980, 5), new Pair<>(1981, 10));
        MeasurementVector vector = new MeasurementVector("Greece", "Earthquake", measurements);
        mockData.add(vector);

        search = new Search(mockData, mockRequestMap);
    }

    @Test
    public void testSuccessfulSearch() {
        String requestName = "TestRequest";
        String countryName = "Greece";
        String disasterType = "Earthquake";

        ISingleMeasureRequest result = search.findSingleCountryIndicator(requestName, countryName, disasterType, mockData);

        assertNotNull("Search should return a result", result);
        assertTrue("Result should contain measurements", !result.getAnswer().getMeasurements().isEmpty());
        assertEquals("Result should be for the requested country", countryName, result.getAnswer().getCountryName());
        assertEquals("Result should be for the requested disaster type", disasterType, result.getAnswer().getIndicatorString());
    }
}

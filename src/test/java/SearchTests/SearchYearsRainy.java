package SearchTests;

import org.junit.Test;

import Search.Search;
import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import domain.MeasurementVector;

import org.apache.commons.math3.util.Pair;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;

public class SearchYearsRainy {

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
    public void testUnsuccessfulSearchWithinTimeRange() {
        String requestName = "TestRequest";
        String countryName = "Greece";
        String disasterType = "Earthquake";
        int startYear = 2000;
        int endYear = 2005;

        ISingleMeasureRequest result = search.findSingleCountryIndicatorYearRange(requestName, countryName, disasterType, startYear, endYear, mockData);

        assertNull("Search should return null for a time range with no data", result);
    }
}

package Search;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import domain.MeasurementVector;
import domain.SingleMeasureRequest;

import org.apache.commons.math3.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Search {
    private List<IMeasurementVector> data;
    private Map<String, ISingleMeasureRequest> requestMap;

    public Search(List<IMeasurementVector> data, Map<String, ISingleMeasureRequest> requestMap) {
        this.data = data;
        this.requestMap = requestMap;
    }

 
	public ISingleMeasureRequest findSingleCountryIndicator(String requestName, String country, String disasterType, List<IMeasurementVector> lvectors) {
        if (requestName == null || requestName.trim().isEmpty() ||
            country == null || country.trim().isEmpty() ||
            disasterType == null || disasterType.trim().isEmpty()) {
            throw new IllegalArgumentException("Request name, country name, and indicator string cannot be empty");
        }
        ISingleMeasureRequest request = null;
        
        for (IMeasurementVector vector : lvectors) {
            if (vector.getCountryName().equals(country) && vector.getIndicatorString().equals(disasterType)) {
                // Check if the vector has valid data
                if (!vector.getMeasurements().isEmpty()) {
                    request = new SingleMeasureRequest(requestName, vector);
                    requestMap.put(requestName, request);
                    System.out.println("Request with valid data added: " + requestName);
                } else {
                    System.out.println("No valid data for request: " + requestName);
                }
               
            }
        }
        return request;
    }

    public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName, String indicatorString, int startYear, int endYear, List<IMeasurementVector> lvectors) {
        if (requestName == null || requestName.trim().isEmpty() ||
            countryName == null || countryName.trim().isEmpty() ||
            indicatorString == null || indicatorString.trim().isEmpty()) {
            throw new IllegalArgumentException("Request name, country name, and indicator string cannot be empty");
        }
        ISingleMeasureRequest request = null;
        for (IMeasurementVector vector : lvectors) {
            if (vector.getCountryName().equals(countryName) && vector.getIndicatorString().equals(indicatorString)) {
                List<Pair<Integer, Integer>> filteredMeasurements = vector.getMeasurements().stream()
                    .filter(measurement -> measurement.getKey() >= startYear && measurement.getKey() <= endYear)
                    .collect(Collectors.toList());
                if (!filteredMeasurements.isEmpty()) {
                    MeasurementVector filteredVector = new MeasurementVector(countryName, indicatorString, filteredMeasurements);
                    request = new SingleMeasureRequest(requestName, filteredVector);
                    requestMap.put(requestName, request);
                    System.out.println("Request with valid data added: " + requestName);
                } else {
                    System.out.println("No valid data for request: " + requestName);
                }
                return request;
            }
        }
        return null;
    }


    public Set<String> getAllRequestNames() {
        return requestMap.keySet();
    }

    public ISingleMeasureRequest getRequestByName(String requestName) {
        return requestMap.get(requestName);
    }
}
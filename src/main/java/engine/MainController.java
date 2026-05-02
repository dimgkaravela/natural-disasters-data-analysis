package engine;


import Search.Search;
import Statistics.RegressionCalc;
import Statistics.StatisticsCalc;
import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import domain.SingleMeasureRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import LoadFile.ILoadController;
import LoadFile.LoadControllerFactory;
import Saving.SaveReport;

public class MainController implements IMainController{
	 private SaveReport saveReport;
	 private List<IMeasurementVector> loadedvectors;
	 
	 private Search search;
	    private RegressionCalc regressionCalc;
	    private StatisticsCalc statisticsCalc;
	    private Map<String, ISingleMeasureRequest> requestMap = new HashMap<>();
	    private Map<String, String> regressionResults = new HashMap<>();
	    private Map<String, String> statisticsResults = new HashMap<>();
	   
	    
	    public MainController(Search search) {
	    	this.requestMap = new HashMap<>();
	        this.search = new Search(new ArrayList<>(), requestMap);
	        this.regressionCalc = new RegressionCalc();
	        this.statisticsCalc = new StatisticsCalc();
	        this.saveReport = new SaveReport();
	    }
	    
	    public void setRegressionCalc(RegressionCalc regressionCalc) {
	        this.regressionCalc = regressionCalc;
	    }

	    public void setStatisticsCalc(StatisticsCalc statisticsCalc) {
	        this.statisticsCalc = statisticsCalc;
	    }

    @Override
    public List<IMeasurementVector> load(String fileName, String delimiter) throws FileNotFoundException, IOException {
    	LoadControllerFactory loadControllerFactory = new LoadControllerFactory();
        ILoadController loadController = loadControllerFactory.createDelimetedLoadController(delimiter);
        loadedvectors = loadController.load(fileName);
        return loadedvectors;
    }

    @Override
    public ISingleMeasureRequest findSingleCountryIndicator(String requestName, String countryName, String indicatorString) throws IllegalArgumentException {
//    	ISingleMeasureRequest findSingleCI = search.findSingleCountryIndicator(requestName, countryName, indicatorString, loadedvectors);
//    	requestMap.put(requestName, findSingleCI); 
//        return findSingleCI; 
    	return search.findSingleCountryIndicator(requestName, countryName, indicatorString, loadedvectors);
    }

    @Override
    public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName, String indicatorString, int startYear, int endYear) throws IllegalArgumentException {
        return search.findSingleCountryIndicatorYearRange(requestName, countryName, indicatorString, startYear, endYear, loadedvectors); // Delegation to Search
    }

    @Override
    public Set<String> getAllRequestNames() {
        return search.getAllRequestNames(); 
    }

    @Override
    public ISingleMeasureRequest getRequestByName(String requestName) {
        return search.getRequestByName(requestName); 
    }
    
    @Override
    public ISingleMeasureRequest getDescriptiveStats(String requestName) {
        System.out.println("Fetching request for statistics: " + requestName);
        ISingleMeasureRequest request = requestMap.get(requestName);

        if (request == null) {
            System.out.println("Request not found in map: " + requestName);
            return null;
        }

        System.out.println("Processing request for statistics: " + requestName);
        if (request.isAnsweredFlag() && !request.getAnswer().getMeasurements().isEmpty()) {
            SingleMeasureRequest updatedRequest = statisticsCalc.calculateStatistics(requestName, request.getAnswer());
            if (updatedRequest != null) {
                requestMap.put(requestName, updatedRequest);
                System.out.println("Updated Descriptive Stats: " + updatedRequest.getDescriptiveStatsString());
            } else {
                System.out.println("Failed to calculate descriptive stats for: " + requestName);
                return null;
            }
        } else {
            System.out.println("Request has no valid data or is not marked as answered: " + requestName);
            return null;
        }
        
        return requestMap.get(requestName); // Return the updated request
    }



    @Override
    public ISingleMeasureRequest getRegression(String requestName) {
        System.out.println("Available requests in map: " + requestMap.keySet());
        ISingleMeasureRequest request = requestMap.get(requestName);
        if (request == null) {
   
            return null;
        }
       
        if (request != null && request.isAnsweredFlag()) {
            
            if (!request.getAnswer().getMeasurements().isEmpty()) {
                SingleMeasureRequest updatedRequest = regressionCalc.calculateRegression(requestName, request.getAnswer());
            requestMap.put(requestName, updatedRequest); 
        }else {
            System.out.println("No valid data for regression calculation for: " + requestName);
        }
      
        }
        ISingleMeasureRequest updatedRequest = requestMap.get(requestName);

        return updatedRequest;
    }

    public String getStoredStatisticsResult(String requestName) {
        return statisticsResults.getOrDefault(requestName, "No result available");
    }

    public String getStoredRegressionResult(String requestName) {
        return regressionResults.getOrDefault(requestName, "No result available");
    
    }

    @Override
    public int reportToFile(String outputFilePath, String requestName, String reportType) throws IOException {
        ISingleMeasureRequest request = requestMap.get(requestName);
        if (request == null) {
            throw new IllegalArgumentException("Request not found: " + requestName);
        }
        return saveReport.reportToFile(outputFilePath, request, reportType); // Now saveReport is initialized
    }
}
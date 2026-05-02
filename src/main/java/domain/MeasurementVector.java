package domain;

import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;

import java.util.List;

public class MeasurementVector implements IMeasurementVector {
    private String countryName;
    private String indicator;
    private List<Pair<Integer, Integer>> measurements;

    public MeasurementVector(String countryName, String indicator, List<Pair<Integer, Integer>> measurements) {
        this.countryName = countryName;
        this.indicator = indicator;
        this.measurements = measurements;
    }
    

    @Override
    public String getCountryName() {
        return countryName;
    }

    @Override
    public String getIndicatorString() {
        return indicator;
    }

    @Override
    public List<Pair<Integer, Integer>> getMeasurements() {
        return measurements;
    }

	@Override
	public String getDescriptiveStatsAsString() {
		
		return "Descriptive Stats Here";
	}

	@Override
	public String getRegressionResultAsString() {
		
		return "Regression Results Here";
	}

    
}
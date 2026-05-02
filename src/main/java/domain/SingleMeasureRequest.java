package domain;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;



public class SingleMeasureRequest implements ISingleMeasureRequest {
    private String requestName;
    private String requestFilter;
    private boolean isAnswered;
    private IMeasurementVector answer;
    private String descriptiveStatsString;
    private String regressionResultString;

    public SingleMeasureRequest(String requestName, IMeasurementVector vector, 
            String statsString, String regressionResult) {
        this.requestName = requestName;
        this.answer = vector;
        this.isAnswered = (vector != null && !vector.getMeasurements().isEmpty());
        this.descriptiveStatsString = (statsString != null) ? statsString : "";
        this.regressionResultString = (regressionResult != null) ? regressionResult : "";
    }


    


    public SingleMeasureRequest(String requestName2, IMeasurementVector vector) {
		this.answer = vector; 
		this.requestName = requestName2;
		this.isAnswered = (answer != null && !answer.getMeasurements().isEmpty());
	    // Set default strings as empty to avoid null values
	    this.descriptiveStatsString = "";
	    this.regressionResultString = "";
	}
	@Override
    public String getRequestName() {
        return requestName;
    }

    @Override
    public String getRequestFilter() {
        return requestFilter;
    }

    @Override
    public boolean isAnsweredFlag() {
        return isAnswered;
    }

    @Override
    public IMeasurementVector getAnswer() {
        return answer;
    }

    @Override
    public String getDescriptiveStatsString() {
        return descriptiveStatsString;
    }

    @Override
    public String getRegressionResultString() {
        
        return regressionResultString;
    }
}
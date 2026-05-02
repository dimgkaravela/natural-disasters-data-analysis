package Saving;

import dom2app.ISingleMeasureRequest;
import domain.MeasurementVector;
import org.apache.commons.math3.util.Pair;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveTextReport implements ISaveReport {

    @Override
    public int saveReport(ISingleMeasureRequest request, String filePath) throws IOException {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null.");
        }

        MeasurementVector vector = (MeasurementVector) request.getAnswer();
        int lineCount = 0; 

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(request.getRequestName() + "\n\n");
            lineCount++;

            System.out.println("Writing report to file: " + filePath);
            String descriptiveStats = request.getDescriptiveStatsString();
            String regressionResult = request.getRegressionResultString();

           
            System.out.println("Descriptive Stats to be written: " + descriptiveStats);
            System.out.println("Regression Result to be written: " + regressionResult);
            
            System.out.println("Saving report for request: " + request.getRequestName());
            System.out.println("Descriptive Stats to be written: " + request.getDescriptiveStatsString());
            
            System.out.println("Regression Result to be written: " + request.getRegressionResultString());
            
            writer.write("Country: " + vector.getCountryName() + "\n");
            writer.write("Indicator: " + vector.getIndicatorString() + "\n\n");
            lineCount += 2;

            writer.write("Year\tValue\n");
            lineCount++;

            List<Pair<Integer, Integer>> measurements = vector.getMeasurements();
            for (Pair<Integer, Integer> measurement : measurements) {
                writer.write(measurement.getKey() + "\t" + measurement.getValue() + "\n");
                lineCount++;
            }

            writer.write("\nDescriptive Stats Result\n");
            writer.write(descriptiveStats + "\n\n");
            lineCount += 2 + descriptiveStats.split("\n").length;

            writer.write("Regression Result\n");
            writer.write(regressionResult + "\n");
            lineCount += 2 + regressionResult.split("\n").length;
        }

        return lineCount;
    }
}
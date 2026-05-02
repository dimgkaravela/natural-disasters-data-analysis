package Saving;

import dom2app.ISingleMeasureRequest;
import domain.MeasurementVector;
import org.apache.commons.math3.util.Pair;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveHTMLReport implements ISaveReport {

    @Override
    public int saveReport(ISingleMeasureRequest request, String filePath) throws IOException {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<head>\n");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1253\">\n");
            writer.write("<title>Natural Disaster Data</title>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");

            
            writer.write("<h1>" + request.getRequestName() + "</h1>\n");

          
            MeasurementVector vector = (MeasurementVector) request.getAnswer();
            writer.write("<p><b>" + request.getRequestName() + "</b></p>\n");
            writer.write("<p><i>Country ~ " + vector.getCountryName() + " Indicator: " + vector.getIndicatorString() + "</i></p>\n");

            
            writer.write("<table>\n");
            writer.write("<tr><th>Year</th><th>Value</th></tr>\n");
            List<Pair<Integer, Integer>> measurements = vector.getMeasurements();
            for (Pair<Integer, Integer> measurement : measurements) {
                writer.write("<tr><td>" + measurement.getKey() + "</td><td>" + measurement.getValue() + "</td></tr>\n");
            }
            writer.write("</table>\n");
            
         
            String descriptiveStats = request.getDescriptiveStatsString();
            String regressionResult = request.getRegressionResultString();

            
            writer.write("<h2>Descriptive Stats Result</h2>\n<pre>" + descriptiveStats + "</pre>\n");

            writer.write("<h2>Regression Result</h2>\n<pre>" + regressionResult + "</pre>\n");

            writer.write("</body>\n</html>\n");

            writer.write("</body>\n");
            writer.write("</html>\n");

            return measurements.size() + 10; 
        }
    }
}
package Saving;

import dom2app.ISingleMeasureRequest;
import domain.MeasurementVector;
import org.apache.commons.math3.util.Pair;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveMarkDownReport implements ISaveReport {

    @Override
    public int saveReport(ISingleMeasureRequest request, String filePath) throws IOException {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null.");
        }

        int lineCount = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("**" + request.getRequestName() + "**" + "\n\n");
            lineCount++;

            MeasurementVector vector = (MeasurementVector) request.getAnswer();

            writer.write("_**Country: " + vector.getCountryName() );
            writer.write(" Indicator: " + vector.getIndicatorString() + "**_" + "\n\n");
            lineCount += 2;

            writer.write("| _Year_ | _Value_ |\n");
            lineCount ++;

            writer.write("\n");
            List<Pair<Integer, Integer>> measurements = vector.getMeasurements();
            Integer previousYear = null;
            for (Pair<Integer, Integer> measurement : measurements) {
                if (previousYear != null && !measurement.getKey().equals(previousYear)) {
                    writer.write("\n");
                    lineCount++;
                }
                writer.write("| " + measurement.getKey() + " | " + measurement.getValue() + " |\n");
                lineCount++;
                previousYear = measurement.getKey();
            }

            
            

            writer.write("\n");
            lineCount++;
            String descriptiveStats = request.getDescriptiveStatsString();
            String regressionResult = request.getRegressionResultString();

            
            writer.write("\n## Descriptive Stats Result\n");
            writer.write(descriptiveStats.replace("\n", "  \n") + "\n");
            lineCount += 2 + descriptiveStats.split("\n").length;

            writer.write("\n## Regression Result\n");
            writer.write(regressionResult.replace("\n", "  \n") + "\n");
            lineCount += 2 + regressionResult.split("\n").length;

            return lineCount;
        }
    }
}
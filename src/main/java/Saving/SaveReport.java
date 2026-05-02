package Saving;

import dom2app.ISingleMeasureRequest;
import java.io.IOException;

public class SaveReport {
    public int reportToFile(String filePath, ISingleMeasureRequest request, String reportType) throws IOException {
        ISaveReport saveReport;
        switch (reportType.toLowerCase()) {
            case "html":
                saveReport = new SaveHTMLReport();
                break;
            case "md":
                saveReport = new SaveMarkDownReport();
                break;
            case "text":
                saveReport = new SaveTextReport();
                break;
            default:
                throw new IllegalArgumentException("Unsupported report type: " + reportType);
        }
        return saveReport.saveReport(request, filePath);
    }
}

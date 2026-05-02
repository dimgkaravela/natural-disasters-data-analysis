package Saving;

import dom2app.ISingleMeasureRequest;
import java.io.IOException;

public interface ISaveReport {
    int saveReport(ISingleMeasureRequest request, String filePath) throws IOException;
}

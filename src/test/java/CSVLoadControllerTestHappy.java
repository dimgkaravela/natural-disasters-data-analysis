import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import java.io.*;
import java.util.List;
import static org.junit.Assert.*;
import LoadFile.CSVLoadController;
import dom2app.IMeasurementVector;

public class CSVLoadControllerTestHappy {

    private File tempFile;

    @Before
    public void setUp() throws IOException {
        
        tempFile = File.createTempFile("test", ".csv");
        PrintWriter out = new PrintWriter(new FileWriter(tempFile));
        out.println("Country,Year,Data1,Data2,Data3,1980,1981,1982"); 
        out.println("CountryA,1980,DataA1,DataA2,DataA3,10,20,30"); 
        out.close();
    }

    @Test
    public void testLoadCSV_Success() throws IOException {
        CSVLoadController loader = new CSVLoadController();

        
        List<IMeasurementVector> result = loader.load(tempFile.getAbsolutePath());

      
        assertNotNull("Data should not be null", result);
        assertFalse("Data list should not be empty", result.isEmpty());
       
    }

    @After
    public void tearDown() {
        
        if (tempFile != null) {
            tempFile.delete();
        }
    }
}


 import org.junit.Test; 
 import java.io.FileNotFoundException; 
 import java.io.IOException;
 import LoadFile.CSVLoadController;
 
  public class CSVLoadControllerTestRainy {
  
  @Test(expected = FileNotFoundException.class) 
  public void testLoadCSV_FileNotFound() throws IOException { 
	  	CSVLoadController loader = new CSVLoadController();
  
  

  loader.load("non_existent_file.csv");
  }
}
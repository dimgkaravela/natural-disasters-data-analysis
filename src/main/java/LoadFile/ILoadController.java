package LoadFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dom2app.IMeasurementVector;

public interface ILoadController {
	  public List<IMeasurementVector> load(String fileName) throws FileNotFoundException, IOException;
}
package LoadFile;

import dom2app.IMeasurementVector;
import domain.MeasurementVector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;

public class CSVLoadController implements ILoadController {

	public List<IMeasurementVector> load(String fileName) throws FileNotFoundException, IOException{
        List<IMeasurementVector> vectors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
            
                String country = parts[1].trim();
                String indicator = parts[4].trim();
                List<Pair<Integer, Integer>> measurements = new ArrayList<>();
                int year = 1980;
                MeasurementVector vector = null;                
                for (int i = 5; i < parts.length; i++) {
                    	if(parts[i].isEmpty()) {
                    		measurements.add(new Pair<>(year, 0));
                    	}else{
                    		measurements.add(new Pair<>(year, Integer.parseInt(parts[i].trim())));
                    	}
                    	year++;
                    	vector = new MeasurementVector(country, indicator, measurements);
                }

               
                vectors.add(vector);
            }
        }
        return vectors;
    }
}
package LoadFile;

public class LoadControllerFactory {
	public ILoadController createDelimetedLoadController(String delimeter) {
		if(delimeter.equals("\t")) {
			return new TSVLoadController();
		}
		else if(delimeter.equals(",")) {
			return new CSVLoadController();
		}else {
			return null;
		}
		
	}

}

package engine;

import Search.Search;
import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IMainControllerFactory {

    public enum ControllerTypeEnum { DEFAULT };

    public IMainController createMainController(ControllerTypeEnum controllerType) {
        if (controllerType == ControllerTypeEnum.DEFAULT) {
            Map<String, ISingleMeasureRequest> requestMap = new HashMap<>();
            List<IMeasurementVector> dataList = new ArrayList<>();
            Search search = new Search(dataList, requestMap);

            MainController mainController = new MainController(search);
       

            return mainController;
        }
        return null;
    }
}
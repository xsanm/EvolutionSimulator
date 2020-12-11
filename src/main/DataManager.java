import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;


public class DataManager {
    int duration;
    int startGrassNumber;

    int mapWidth;
    int mapHeight;
    int startAnimalNumber;
    double startEnergy;
    double moveEnergy;
    double grassEnergy;
    double jungleRatio;
    boolean twoMaps;


    public DataManager() {
        resetData();
    }

    public void resetData() {
        //TODO try catch jesli plik sie zmineil
        String jsonString = null; //assign your JSON String here
        try {
            String path = new File("src/main/startingParameters.json").getAbsolutePath();
            System.out.println(path);
            jsonString = Files.readString(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject(jsonString);
        JSONObject startingData = obj.getJSONObject("StartingParameters");
        //System.out.println(startingData.get("duration"));


        //JSONObject obj = new JSONObject(jsonString);

        //JSONObject employeeDetails = new JSONObject();

        //C:\Users\xsan\Desktop\EvolutionSimulator\src\main


        mapWidth = (int) startingData.get("mapWidth");
        mapHeight = (int) startingData.get("mapHeight");
        startAnimalNumber = (int) startingData.get("startAnimalNumber");
        startEnergy = (double) startingData.get("startEnergy");
        moveEnergy = (double) startingData.get("moveEnergy");
        grassEnergy = (double) startingData.get("grassEnergy");
        jungleRatio = (double) startingData.get("jungleRatio");
        twoMaps = (boolean) startingData.get("twoMaps");
        startGrassNumber = (int) startingData.get("startGrassNumber");
        duration = (int) startingData.get("duration");
        /*mapWidth = 10;
        mapHeight = 10;
        startAnimalNumber = 15;
        startEnergy = 2.5;
        moveEnergy = 0.1;
        grassEnergy = 1.0;
        jungleRatio = 0.5;
        twoMaps = false;
        startGrassNumber = 30;
        duration = 1000;*/
    }

    @Override
    public String toString() {
        return "DataManager{" +
                "mapWidth=" + mapWidth +
                ", mapHeight=" + mapHeight +
                ", startAnimalNumber=" + startAnimalNumber +
                ", startEnergy=" + startEnergy +
                ", moveEnergy=" + moveEnergy +
                ", grassEnergy=" + grassEnergy +
                ", jungleRatio=" + jungleRatio +
                ", twoMaps=" + twoMaps +
                '}';
    }
}

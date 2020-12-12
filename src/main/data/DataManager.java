package data;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class DataManager {

    private int duration;
    private int startGrassNumber;

    private int mapWidth;
    private int mapHeight;
    private int startAnimalNumber;
    private double startEnergy;
    private double moveEnergy;
    private double grassEnergy;
    private double jungleRatio;
    private boolean twoMaps;


    public DataManager() {
        resetData();
    }

    public void resetData() {
        //TODO try catch jesli plik sie zmineil
        String jsonString = null;
        try {
            String path = new File("startingParameters.json").getAbsolutePath();
            System.out.println(path);
            jsonString = Files.readString(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject(jsonString);
        JSONObject startingData = obj.getJSONObject("StartingParameters");

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

    }

    @Override
    public String toString() {
        return "data.DataManager{" +
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

    public int getMapWidth() {
        return mapWidth;
    }

    public double getGrassEnergy() {
        return grassEnergy;
    }

    public double getJungleRatio() {
        return jungleRatio;
    }

    public double getMoveEnergy() {
        return moveEnergy;
    }

    public double getStartEnergy() {
        return startEnergy;
    }

    public int getDuration() {
        return duration;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getStartAnimalNumber() {
        return startAnimalNumber;
    }

    public int getStartGrassNumber() {
        return startGrassNumber;
    }
    public boolean getTwoMaps() {
        return twoMaps;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void setGrassEnergy(double grassEnergy) {
        this.grassEnergy = grassEnergy;
    }

    public void setJungleRatio(double jungleRatio) {
        this.jungleRatio = jungleRatio;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setMoveEnergy(double moveEnergy) {
        this.moveEnergy = moveEnergy;
    }

    public void setStartAnimalNumber(int startAnimalNumber) {
        this.startAnimalNumber = startAnimalNumber;
    }

    public void setStartEnergy(double startEnergy) {
        this.startEnergy = startEnergy;
    }

    public void setStartGrassNumber(int startGrassNumber) {
        this.startGrassNumber = startGrassNumber;
    }

    public void setTwoMaps(boolean twoMaps) {
        this.twoMaps = twoMaps;
    }

}

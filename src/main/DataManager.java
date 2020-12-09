import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataManager{
    public int duration;
    int startGrassNumber;
    int age;
    int grasses;
    int mapWidth;
    int mapHeight;
    int startAnimalNumber;
    double startEnergy;
    double moveEnergy;
    double grassEnergy;
    double jungleRatio;
    boolean twoMaps;
    int animals;

    public DataManager(){
        resetData();
    }

    public void resetData(){
        mapWidth = 10;
        mapHeight = 10;
        startAnimalNumber = 15;
        startEnergy = 2.5;
        moveEnergy = 0.1;
        grassEnergy = 1.0;
        jungleRatio = 0.5;
        twoMaps = false;
        animals = 0;
        grasses = 0;
        age = 0;
        startGrassNumber = 30;
        duration = 1000;
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
                ", animals=" + animals +
                '}';
    }
}

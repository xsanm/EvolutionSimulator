import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataManager{
    int mapWidth;
    int mapHeight;
    int startAnimalNumber;
    double startEnergy;
    double moveEnergy;
    double grassEnergy;
    double jungleRatio;
    boolean twoMaps;

    public DataManager(){
        resetData();
    }

    public void resetData(){
        mapWidth = 10;
        mapHeight = 10;
        startAnimalNumber = 5;
        startEnergy = 2.5;
        moveEnergy = 1.5;
        grassEnergy = 1.0;
        jungleRatio = 0.5;
        twoMaps = false;
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

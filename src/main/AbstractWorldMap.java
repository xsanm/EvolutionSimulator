import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.round;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected TreeMap<Vector2D, List<Animal>> animals;
    private final int MAP_WIDTH;
    private final int MAP_HEIGHT;
    private GrassMap grassMap;
    private Vector2D jungleBegin;
    private Vector2D jungleEnd;
    private DataManager dataManager;
    double jungleRatio;
    MapPanel mapPanel;

    public AbstractWorldMap(DataManager dataManager){
        this.dataManager = dataManager;
        grassMap = new GrassMap(this, dataManager.mapWidth, dataManager.mapHeight);
        MAP_WIDTH = dataManager.mapWidth;
        MAP_HEIGHT = dataManager.mapHeight;
        this.jungleRatio = dataManager.jungleRatio;
        animals = new TreeMap<>();
        countJungleRanges();

        this.mapPanel = new MapPanel(dataManager.mapWidth, dataManager.mapHeight, getJungleBegin(), getJungleEnd());
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    private void countJungleRanges() {
        int x = (int) ( MAP_WIDTH * jungleRatio);
        int y = (int) ( MAP_HEIGHT * jungleRatio);
        jungleBegin = new Vector2D((MAP_WIDTH - x) / 2,  (MAP_HEIGHT - y) / 2);
        jungleEnd = new Vector2D(jungleBegin.x + x, jungleBegin.y + y);
    }

    public Vector2D getJungleBegin() {
        return jungleBegin;
    }

    public Vector2D getJungleEnd() {
        return jungleEnd;
    }

    @Override
    public boolean canMoveTo(Vector2D position) {
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        //TODO try catch
        if(this.animals.get(animal.getPosition()) == null)
            this.animals.put(animal.getPosition(), new ArrayList<>() {
            });

        this.animals.get(animal.getPosition()).add(animal);


        return true;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return this.animals.get(position) != null && this.animals.get(position).size() > 0;
    }

    @Override
    public List<Animal> objectAt(Vector2D position) {
        return this.animals.get(position);
    }

    @Override
    public String toString() {
        MapConsoleVisualizer mapa = new MapConsoleVisualizer(this, grassMap);
        return mapa.draw(new Vector2D(0, 0), new Vector2D(MAP_WIDTH - 1, MAP_WIDTH - 1));
    }

    public void addGrass(){
        this.grassMap.addGrass();
    }

    public TreeMap<Vector2D, List<Animal>> getAnimals() {
        return animals;
    }

    public GrassMap getGrassMap() {
        return grassMap;
    }

    public int getMAP_HEIGHT() {
        return MAP_HEIGHT;
    }

    public int getMAP_WIDTH() {
        return MAP_WIDTH;
    }

    public void positionChanged(Animal animal, Vector2D oldPosition, Vector2D newPosition){
       // Animal animal = (Animal) this.objectAt(oldPosition);
        this.animals.get(oldPosition).remove(animal);
        this.place(animal);

        //this.animals.put(newPosition, animal);
        //this.animals.remove(oldPosition);
        //mapBoundary.positionChanged(oldPosition, newPosition);
        //mapBoundary.positionChanged(oldPosition, newPosition);
    }

    public void addRandomAnimal(){
        this.place(
                new Animal(new Vector2D(generateRandom(0, dataManager.mapWidth), generateRandom(0, dataManager.mapHeight)),
                        new Genotype(), 1, (IPositionChangeObserver) this, dataManager));
    }

    private int generateRandom(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}

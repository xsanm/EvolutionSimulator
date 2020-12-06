import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected TreeMap<Vector2D, List<Animal>> animals;
    private final int MAP_WIDTH;
    private final int MAP_HEIGHT;
    private GrassMap grassMap;

    public AbstractWorldMap(int map_width, int map_height){
        grassMap = new GrassMap(this, map_width, map_height);
        MAP_WIDTH = map_width;
        MAP_HEIGHT = map_height;
        animals = new TreeMap<>();
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
}

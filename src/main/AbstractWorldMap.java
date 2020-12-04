import java.util.ArrayList;
import java.util.TreeMap;

public abstract class AbstractWorldMap implements IWorldMap {

    protected TreeMap<Vector2D, ArrayList<Animal>> animals;
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
            this.animals.put(animal.getPosition(), new ArrayList<Animal>());

        this.animals.get(animal.getPosition()).add(animal);


        return true;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return this.animals.get(position) != null && this.animals.get(position).size() > 0;
    }

    @Override
    public ArrayList<Animal> objectAt(Vector2D position) {
        return this.animals.get(position);
    }

    @Override
    public String toString() {
        MapConsoleVisualizer mapa = new MapConsoleVisualizer(this, grassMap);
        return mapa.draw(new Vector2D(0, 0), new Vector2D(MAP_WIDTH, MAP_WIDTH));
    }

    public void addGrass(){
        this.grassMap.addGrass();
    }

}

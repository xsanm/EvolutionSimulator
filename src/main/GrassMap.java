import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class GrassMap implements IGrassMap{
    protected TreeSet<MapElement> grasses;
    private final int MAP_WIDTH;
    private final int MAP_HEIGHT;
    IWorldMap map;

    public GrassMap(IWorldMap imap, int map_width, int map_height) {
        map = imap;
        MAP_WIDTH = map_width;
        MAP_HEIGHT = map_height;
        grasses = new TreeSet<>();
    }

    //TODO
    public void addGrass() {
        Vector2D v1 = new Vector2D(1,1);
        Vector2D v2 = new Vector2D(3,3);
        if(!map.isOccupied(v1)) {
            this.place(new MapElement(v1));
        }
        if(!map.isOccupied(v2)) {
            this.place(new MapElement(v2));
        }
    }

    public TreeSet<MapElement> getGrasses() {
        return grasses;
    }

    @Override
    public boolean place(MapElement element) {
        grasses.add(element);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return grasses.contains(new MapElement(position));
    }

    @Override
    public MapElement objectAt(Vector2D position) {
        //TODO
        return null;
    }

    @Override
    public String toString() {
        return "*";
    }
}

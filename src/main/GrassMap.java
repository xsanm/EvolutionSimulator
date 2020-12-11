import java.util.TreeMap;

public class GrassMap implements IGrassMap {
    private final int MAP_WIDTH;
    private final int MAP_HEIGHT;
    protected TreeMap<Vector2D, MapElement> grasses;
    IWorldMap map;

    public GrassMap(IWorldMap imap, int map_width, int map_height) {
        map = imap;
        MAP_WIDTH = map_width;
        MAP_HEIGHT = map_height;
        grasses = new TreeMap<>();
    }

    //TODO
    public void addGrass() {
        Vector2D v1 = new Vector2D(1, 1);
        Vector2D v2 = new Vector2D(3, 3);
        if (!map.isOccupied(v1)) {
            this.place(new MapElement(v1));
        }
        if (!map.isOccupied(v2)) {
            this.place(new MapElement(v2));
        }
    }


    @Override
    public boolean place(MapElement element) {
        grasses.put(element.getPosition(), element);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return grasses.get(position) != null;
    }

    @Override
    public MapElement objectAt(Vector2D position) {
        return grasses.get(position);
    }

    @Override
    public String toString() {
        return "*";
    }
}

import java.util.List;
import java.util.TreeMap;

public interface IWorldMap {
    /**
     * Indicate if any object can move to the given position.
     *
     * @param position The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    boolean canMoveTo(Vector2D position);

    /**
     * Place a animal on the map.
     *
     * @param animal The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    boolean place(Animal animal);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2D position);

    /**
     * Return an object at a given position.
     *
     * @param position The position of the object.
     * @return Object or null if the position is not occupied.
     */
    List<Animal> objectAt(Vector2D position);

    TreeMap<Vector2D, List<Animal>> getAnimals();

    public int getMAP_HEIGHT();

    public int getMAP_WIDTH();

    public Vector2D getJungleBegin();

    public Vector2D getJungleEnd();

    void addRandomAnimal();

    public MapPanel getMapPanel();

    public void generateAnimals();

    public void deleteDead();

    public void rotate();

    public void move();

    public void eat();

    public void procreate();

    public void addGrass();

    public void generateGrasses();

    void countStats();

    void getObjectsAtPosition(Vector2D vec);

    void redrawAnimals();
}

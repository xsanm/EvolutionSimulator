public interface IGrassMap {

    boolean place(MapElement element);

    boolean isOccupied(Vector2D position);

    MapElement objectAt(Vector2D position);
}

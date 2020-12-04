public class MapElement implements Comparable<MapElement> {
    private Vector2D position;

    public MapElement(Vector2D position){
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }


    @Override
    public int compareTo(MapElement ob) {
        Vector2D o = ob.getPosition();
        if(this.equals(o))
            return 0;
        if(this.getPosition().x < o.x)
            return -1;
        else if(this.getPosition().x == o.x)
            return this.getPosition().y - o.y;
        else
            return 1;
    }
}

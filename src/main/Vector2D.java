import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2D implements Comparable<Vector2D> {
    public final int x;
    public final int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Vector2D vector2d = (Vector2D) other;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2D opposite() {
        return new Vector2D(-this.x, -this.y);
    }

    public boolean precedes(Vector2D other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2D other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2D upperRight(Vector2D other) {
        return new Vector2D(max(this.x, other.x), max(this.y, other.y));
    }

    public Vector2D lowerLeft(Vector2D other) {
        return new Vector2D(min(this.x, other.x), min(this.y, other.y));
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D substract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    @Override
    public int compareTo(Vector2D o) {
        if (this.equals(o))
            return 0;
        if (this.x < o.x)
            return -1;
        else if (this.x == o.x)
            return this.y - o.y;
        else
            return 1;
    }
}

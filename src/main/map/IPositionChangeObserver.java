package map;

import objects.Animal;
import objects.Vector2D;

public interface IPositionChangeObserver {
    void positionChanged(Animal animal, Vector2D oldPosition, Vector2D newPosition);
}

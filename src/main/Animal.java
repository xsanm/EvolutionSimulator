import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Animal implements Comparable<Animal>, Drawable {
    private Vector2D position;
    private int orientation;
    private Genotype genotype;
    private double energy;
    private List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(){

    }

    public Animal(Vector2D position, Genotype genotype, double energy){
        this.position = position;
        this.genotype = genotype;
        this.orientation = genotype.getRandomGene();
        this.energy = energy;
    }

    public Animal(Vector2D position, Genotype genotype, double energy, IPositionChangeObserver obs) {
        this.position = position;
        this.genotype = genotype;
        this.orientation = genotype.getRandomGene();
        this.energy = energy;
        observers.add(obs);
    }

    public Animal(Vector2D vector2D) {
        this.position = vector2D;
    }

    public void move() {
        this.orientation = genotype.getRandomGene();
        Vector2D oldPosition = this.position;
        //TODO
        Vector2D moveVector = genotype.genToUnitVector(this.orientation).add(this.position).add(new Vector2D(5, 5));
        Vector2D newPosition = new Vector2D(moveVector.x % 5, moveVector.y % 5);
        this.position = newPosition;
        System.out.println("rooszam sie" + oldPosition + newPosition);
        System.out.println(this);
        this.positionChanged(this, oldPosition, newPosition);
    }

    public Vector2D getPosition(){
        return this.position;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Animal{" +
                ", energy=" + energy +
                "position=" + position +
                ", orientation=" + orientation +
                ", genotype=" + genotype +

                '}';
    }

    @Override
    public int compareTo(Animal o) {
        return (int) (this.energy - o.energy);
    }

    public void positionChanged(Animal animal, Vector2D oldPosition, Vector2D newPosition){
        for(IPositionChangeObserver observer : this.observers){
            observer.positionChanged(this, oldPosition, newPosition);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.fillOval(position.x, position.y, 30, 30);
    }
}

import java.util.Objects;

public class Animal {
    private Vector2D position;
    private int orientation;
    private Genotype genotype;
    private double energy;

    public Animal(){

    }
    public Animal(Vector2D position, Genotype genotype, double energy){
        this.position = position;
        this.genotype = genotype;
        this.orientation = genotype.getRandomGene();
        this.energy = energy;
    }

    public void move() {
        this.orientation = genotype.getRandomGene();
        Vector2D oldPosition = this.position;
        Vector2D newPosition = this.position.add(genotype.genToUnitVector(this.orientation));
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
                "position=" + position +
                ", orientation=" + orientation +
                ", genotype=" + genotype +
                ", energy=" + energy +
                '}';
    }
}

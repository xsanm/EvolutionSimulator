import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    DataManager dataManager;


    BufferedImage image;


    public Animal(){

    }

    public Animal(Vector2D position, Genotype genotype, double energy){
        this.position = position;
        this.genotype = genotype;
        this.orientation = genotype.getRandomGene();
        this.energy = energy;
    }

    public Animal(Vector2D position, Genotype genotype, double energy, IPositionChangeObserver obs, DataManager dataManager) {
        this.position = position;
        this.genotype = genotype;
        this.orientation = genotype.getRandomGene();
        this.energy = energy;
        observers.add(obs);
        this.dataManager = dataManager;
        try {
            image = ImageIO.read(new File("C:\\Users\\xsan\\Desktop\\arr3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Animal(Vector2D vector2D) {
        this.position = vector2D;
    }

    public void rotate() {
        this.orientation = genotype.getRandomGene();
    }

    public void move() {


        Vector2D oldPosition = this.position;
        //TODO
        Vector2D moveVector = genotype.genToUnitVector(this.orientation).add(this.position).add(new Vector2D(dataManager.mapWidth, dataManager.mapWidth));
        Vector2D newPosition = new Vector2D(moveVector.x % dataManager.mapWidth, moveVector.y % dataManager.mapHeight);
        this.position = newPosition;
        //System.out.println("rooszam sie" + oldPosition + newPosition);
        //System.out.println(this);
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
        double rotationRequired = Math.toRadians (orientation * 45);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(image, null), 0, 0, null);
        //g.fillOval(position.x, position.y, 30, 30);
    }

    public double getEnergy() {
        return energy;
    }
    public void decreaseEnergy(double e) {
        this.energy -= e;
    }
    public void encreaseEnergy(double e) {
        this.energy += e;
    }

    public Genotype getGenotype() {
        return genotype;
    }
}

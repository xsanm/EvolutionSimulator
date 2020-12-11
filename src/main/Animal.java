import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Animal implements Comparable<Animal>, Drawable, Comparator<Animal> {
    DataManager dataManager;
    BufferedImage image;
    private Vector2D position;
    private int orientation;
    private Genotype genotype;
    private double energy;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private int years;
    private int children;


    public Animal() {

    }

    public Animal(Vector2D position, Genotype genotype, double energy) {
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
        years = 0;
        children = 0;
        try {
            String path = new File("src/main/arr3.png").getAbsolutePath();
            //System.out.println(path);
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Animal(Vector2D vector2D) {
        this.position = vector2D;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void rotate() {
        this.orientation += genotype.getRandomGene();
        this.orientation %= 8;
    }

    public int getYears() {
        return years;
    }

    public void move() {

        years++;
        Vector2D oldPosition = this.position;
        //TODO
        Vector2D moveVector = genotype.genToUnitVector(this.orientation).add(this.position).add(new Vector2D(dataManager.mapWidth, dataManager.mapHeight));
        Vector2D newPosition = new Vector2D(moveVector.x % dataManager.mapWidth, moveVector.y % dataManager.mapHeight);
        this.position = newPosition;
        //System.out.println("rooszam sie" + oldPosition + newPosition);
        //System.out.println(this);
        this.positionChanged(this, oldPosition, newPosition);
    }

    public Vector2D getPosition() {
        return this.position;
    }


    @Override
    public int compare(Animal o1, Animal o2) {
        if (o1.energy > o2.energy) return 1;
        else return -1;
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
        if (o.energy > this.energy) return 1;
        else return -1;
    }

    public void positionChanged(Animal animal, Vector2D oldPosition, Vector2D newPosition) {
        for (IPositionChangeObserver observer : this.observers) {
            observer.positionChanged(this, oldPosition, newPosition);

        }
    }

    @Override
    public void draw(Graphics g) {
        double rotationRequired = Math.toRadians(orientation * 45);

        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(image, null), 5, 0, 20, 20, null);
        g.drawRect(3, 18, 24, 8);
        g.fillRect(3, 18, min(24, (int) (24 * (this.energy / dataManager.startEnergy))), 8);
    }

    public double getEnergy() {
        return energy;
    }

    public void decreaseEnergy(double e) {
        this.energy = max(0, this.energy - e);
    }

    public void encreaseEnergy(double e) {
        this.energy += e;
    }

    public Genotype getGenotype() {
        return genotype;
    }
}

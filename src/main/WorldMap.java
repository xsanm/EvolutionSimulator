import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.round;

public class WorldMap implements IWorldMap, IPositionChangeObserver {
    protected TreeMap<Vector2D, List<Animal>> animals;
    List<Animal> animalsList;
    private TreeMap<Vector2D, MapElement> grasses;
    private final int MAP_WIDTH;
    private final int MAP_HEIGHT;
    private Vector2D jungleBegin;
    private Vector2D jungleEnd;
    private DataManager dataManager;
    double jungleRatio;
    MapPanel mapPanel;

    public WorldMap(DataManager dataManager, MapPanel mapPanel){
        this.dataManager = dataManager;
        grasses = new TreeMap<>();

        MAP_WIDTH = dataManager.mapWidth;
        MAP_HEIGHT = dataManager.mapHeight;
        this.jungleRatio = dataManager.jungleRatio;
        animals = new TreeMap<>();
        animalsList = new ArrayList<>();
        countJungleRanges();

        this.mapPanel = mapPanel;
        this.mapPanel.resizeMap(dataManager.mapHeight, dataManager.mapWidth, this.getJungleBegin(), this.getJungleEnd());
        //System.out.println(this.mapPanel);
        //System.out.println(dataManager);
    }

    public void generateGrasses() {
        int cnt = (int) (0.15 * dataManager.mapWidth * dataManager.mapHeight);
        List<Vector2D> intList = new ArrayList<>();
        for(int i = 0; i < dataManager.mapHeight; i++)
            for(int j = 0; j < dataManager.mapWidth; j++)
                if(animals.get(new Vector2D(i, j)) == null)
                    intList.add(new Vector2D(i, j));

        Collections.shuffle(intList);
        for(int i = 0; i < cnt; ++i) {

            MapElement el = new MapElement(intList.get(i));
            this.mapPanel.drawGrass(el);
            grasses.put(el.getPosition(), el);
        }
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    private void countJungleRanges() {
        int x = (int) ( MAP_WIDTH * jungleRatio);
        int y = (int) ( MAP_HEIGHT * jungleRatio);
        jungleBegin = new Vector2D((MAP_WIDTH - x) / 2,  (MAP_HEIGHT - y) / 2);
        jungleEnd = new Vector2D(jungleBegin.x + x, jungleBegin.y + y);
    }

    public Vector2D getJungleBegin() {
        return jungleBegin;
    }

    public Vector2D getJungleEnd() {
        return jungleEnd;
    }

    @Override
    public boolean canMoveTo(Vector2D position) {
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        //TODO try catch
        if(this.animals.get(animal.getPosition()) == null)
            this.animals.put(animal.getPosition(), new ArrayList<>() {
            });

        this.animals.get(animal.getPosition()).add(animal);


        return true;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return this.animals.get(position) != null && this.animals.get(position).size() > 0;
    }

    @Override
    public List<Animal> objectAt(Vector2D position) {
        return this.animals.get(position);
    }

    @Override
    public String toString() {
        //MapConsoleVisualizer mapa = new MapConsoleVisualizer(this, grassMap);
        //return mapa.draw(new Vector2D(0, 0), new Vector2D(MAP_WIDTH - 1, MAP_WIDTH - 1));
        return "AMPA";
    }



    public TreeMap<Vector2D, List<Animal>> getAnimals() {
        return animals;
    }


    public int getMAP_HEIGHT() {
        return MAP_HEIGHT;
    }

    public int getMAP_WIDTH() {
        return MAP_WIDTH;
    }

    public void positionChanged(Animal animal, Vector2D oldPosition, Vector2D newPosition){
        // Animal animal = (Animal) this.objectAt(oldPosition);
        this.animals.get(oldPosition).remove(animal);
        this.place(animal);

        //this.animals.put(newPosition, animal);
        //this.animals.remove(oldPosition);
        //mapBoundary.positionChanged(oldPosition, newPosition);
        //mapBoundary.positionChanged(oldPosition, newPosition);
    }

    public void addRandomAnimal(){
        Animal animal = new Animal(new Vector2D(generateRandom(0, dataManager.mapHeight - 1), generateRandom(0, dataManager.mapWidth - 1)),
                new Genotype(), 1, (IPositionChangeObserver) this, dataManager);
        this.place(animal);
        this.animalsList.add(animal);
        this.mapPanel.drawAnimal(animal);
    }

    public void generateAnimals() {
        int cnt = dataManager.startAnimalNumber;
        while(cnt-- >= 0) {
            this.addRandomAnimal();
        }
    }

    @Override
    public void deleteDead() {
        List<Animal> to_del = new ArrayList<>();
        for(int i = 0; i < animalsList.size(); i++) {
            Animal a = animalsList.get(i);
            if(a.getEnergy() <= 0) {
                this.mapPanel.eraseAnimal(a);
                animals.get(a.getPosition()).remove(a);
                animalsList.remove(i);
                //to_del.add(a);
            }
        }
    }

    @Override
    public void rotate() {
        for(Animal a: animalsList) {
            a.rotate();
        }
    }

    @Override
    public void move() {
        for(Animal a: animalsList) {
            this.mapPanel.eraseAnimal(a);
            a.move();
            a.decreaseEnergy(dataManager.moveEnergy);
            this.mapPanel.drawAnimal(a);
        }

    }

    @Override
    public void eat() {
        //TODO jedza najslisi
        for(int i = 0; i < dataManager.mapHeight; i++)
            for(int j = 0; j < dataManager.mapWidth; j++) {
                Vector2D vec = new Vector2D(i, j);
                if (animals.get(vec) != null && grasses.get(vec) != null) {
                    Animal a = animals.get(vec).get(0);
                    MapElement grass = grasses.get(vec);
                    a.encreaseEnergy(dataManager.grassEnergy);
                    this.mapPanel.eraseGrass(grass);
                    this.grasses.remove(grass.getPosition());
                }
            }

    }

    @Override
    public void procreate() {
        //TODO romnazaja sie najsilniejsi
        for(int i = 0; i < dataManager.mapHeight; i++)
            for(int j = 0; j < dataManager.mapWidth; j++) {
                Vector2D vec = new Vector2D(i, j);
                if (animals.get(vec) != null && animals.get(vec).size() > 1) {
                    Animal a = animals.get(vec).get(0);
                    Animal b = animals.get(vec).get(1);
                    a.decreaseEnergy(a.getEnergy() * 0.25);
                    b.decreaseEnergy(b.getEnergy() * 0.25);
                    Animal c = new Animal(getPosAround(vec), new Genotype(a.getGenotype().getGenes(), b.getGenotype().getGenes()), a.getEnergy() * 0.25 + b.getEnergy() * 0.25, this, dataManager);
                    this.animalsList.add(c);
                    this.place(c);
                    this.mapPanel.drawAnimal(c);

                }
            }


    }

    private Vector2D getPosAround(Vector2D vec) {
        //TODO co jesli nie ma wolnego miesjca
        List<Vector2D> stepPositions = new ArrayList<>();

        for(int i = -1; i <= 1; i++) for(int j = -1; j <= 1; j++) {
            Vector2D v = new Vector2D(vec.x + i, vec.y + j);
            if (animals.get(vec) == null) {
                stepPositions.add(v);
            }
        }


        Collections.shuffle(stepPositions);
        if(stepPositions.isEmpty()) return vec;
        return stepPositions.get(0);
    }

    @Override
    public void addGrass(){
        List<Vector2D> stepPositions = new ArrayList<>();
        List<Vector2D> junglePositions = new ArrayList<>();
        for(int i = 0; i < dataManager.mapHeight; i++)
            for(int j = 0; j < dataManager.mapWidth; j++) {
                Vector2D vec = new Vector2D(i, j);
                if (animals.get(vec) == null && grasses.get(vec) == null) {
                    if (isInJungle(vec)) {
                        junglePositions.add(vec);
                    } else {
                        stepPositions.add(vec);
                    }
                }
            }

        Collections.shuffle(stepPositions);
        Collections.shuffle(junglePositions);

        if(!stepPositions.isEmpty()) {
            MapElement el = new MapElement(stepPositions.get(0));
            this.mapPanel.drawGrass(el);
            grasses.put(el.getPosition(), el);
        }
        if(!junglePositions.isEmpty()) {
            MapElement el = new MapElement(junglePositions.get(0));
            this.mapPanel.drawGrass(el);
            grasses.put(el.getPosition(), el);
        }

    }

    private int generateRandom(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private boolean isInJungle(Vector2D position) {
        return position.follows(getJungleBegin()) && position.precedes(getJungleEnd());
    }

}

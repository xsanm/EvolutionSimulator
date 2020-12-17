package map;

import data.DataManager;
import data.StatManager;
import gui.MapPanel;
import objects.Animal;
import objects.Genotype;
import objects.MapElement;
import objects.Vector2D;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class WorldMap implements IWorldMap, IPositionChangeObserver {
    private final int MAP_WIDTH;
    private final int MAP_HEIGHT;
    protected TreeMap<Vector2D, List<Animal>> map;
    List<Animal> animals;
    double jungleRatio;
    MapPanel mapPanel;
    int sumOFDeadYears = 0;
    int sumOfDead = 0;
    private TreeMap<Vector2D, MapElement> grasses;
    private Vector2D jungleBegin;
    private Vector2D jungleEnd;
    private DataManager dataManager;
    private StatManager statManager;

    public WorldMap(DataManager dataManager, MapPanel mapPanel, StatManager statManager) {
        this.dataManager = dataManager;
        grasses = new TreeMap<>();

        MAP_WIDTH = dataManager.getMapWidth();
        MAP_HEIGHT = dataManager.getMapHeight();
        this.jungleRatio = dataManager.getJungleRatio();
        map = new TreeMap<>();
        animals = new ArrayList<>();
        countJungleRanges();
        this.statManager = statManager;
        this.mapPanel = mapPanel;
        this.mapPanel.resizeMap(dataManager, this);
    }

    public void generateGrasses() {
        int cnt = (dataManager.getStartGrassNumber());
        List<Vector2D> intList = new ArrayList<>();
        for (int i = 0; i < dataManager.getMapWidth(); i++)
            for (int j = 0; j < dataManager.getMapHeight(); j++)
                if (map.get(new Vector2D(i, j)) == null)
                    intList.add(new Vector2D(i, j));

        Collections.shuffle(intList);
        for (int i = 0; i < cnt && i < intList.size(); ++i) {

            MapElement el = new MapElement(intList.get(i));
            this.mapPanel.drawGrass(el);
            grasses.put(el.getPosition(), el);
        }
    }
    public void setGrasses(TreeMap<Vector2D, MapElement> grasses) {
        this.grasses.clear();
        for(Map.Entry<Vector2D, MapElement> entry: grasses.entrySet()) {
            MapElement el = new MapElement(entry.getKey());
            this.mapPanel.drawGrass(el);
            grasses.put(el.getPosition(), el);
            this.grasses.put(entry.getKey(), new MapElement(entry.getKey()));
        }
    }

    @Override
    public void countStats() {
        statManager.setAges(statManager.getAges() + 1);
        statManager.setAnimals(animals.size());
        statManager.setGrasses(grasses.size());
        statManager.setAverageEnergy(this.getAverageEnergy());
        statManager.setAverageChildren(this.getAverageChildren());
        statManager.setAverageLife(this.getAverageLife());
        statManager.setDominatingGene(this.getDominatingGenotype());
        statManager.saveStat();
    }

    private int getDominatingGenotype() {
        int topGene[] = {0,0,0,0,0,0,0,0,0};
        for(Animal a:animals) {
            int genes[] = a.getGenotype().getGenes();
            for(int i = 0; i < 32; i++) topGene[genes[i]]++;
        }
        int val = topGene[0];
        int mx = 0;
        for(int i = 1; i < 8; i++) {
            if(topGene[i] > val) {
                val = topGene[i];
                mx = i;
            }
        }
        return mx;
    }

    private double getAverageChildren() {
        double sum = 0.0;

        for (Animal a : animals) {
            sum += a.getChildren();
        }
        if (animals.isEmpty()) return 0.0;
        return sum / animals.size();
    }

    private double getAverageLife() {
        if (sumOfDead == 0) return 0.0;
        return sumOFDeadYears / sumOfDead;
    }

    private double getAverageEnergy() {
        double sum = 0.0;
        for (Animal a : animals) {
            sum += a.getEnergy();
        }

        if (animals.isEmpty()) return 0.0;
        return sum / animals.size();

    }

    @Override
    public void getObjectsAtPosition(Vector2D vec) {
    }

    @Override
    public void redrawAnimals() {
        for (Animal a : animals) {
            mapPanel.drawAnimal(a);
        }
    }



    private void countJungleRanges() {
        int x = (int) (MAP_WIDTH * jungleRatio);
        int y = (int) (MAP_HEIGHT * jungleRatio);
        jungleBegin = new Vector2D((MAP_WIDTH - x) / 2, (MAP_HEIGHT - y) / 2);
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
        if (this.map.get(animal.getPosition()) == null)
            this.map.put(animal.getPosition(), new ArrayList<>() {
            });

        this.map.get(animal.getPosition()).add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return this.map.get(position) != null && this.map.get(position).size() > 0;
    }

    @Override
    public List<Animal> objectAt(Vector2D position) {
        return this.map.get(position);
    }

    @Override
    public String toString() {
      return "MAPA";
    }


    @Override
    public void setAnimals(List<Animal> animals) {
        this.animals.clear();

        for(Animal a:animals){
            Animal animal = new Animal(a.getPosition(),
                    new Genotype(a.getGenotype().getGenes()), dataManager.getStartEnergy(), (IPositionChangeObserver) this, dataManager);
            this.place(animal);
            this.animals.add(animal);
            this.mapPanel.drawAnimal(animal);
        }
    }

    public void positionChanged(Animal animal, Vector2D oldPosition, Vector2D newPosition) {
        this.map.get(oldPosition).remove(animal);
        this.place(animal);
    }

    public void addRandomAnimal() {
        Animal animal = new Animal(new Vector2D(generateRandom(0, dataManager.getMapWidth() - 1), generateRandom(0, dataManager.getMapHeight() - 1)),
                new Genotype(), dataManager.getStartEnergy(), (IPositionChangeObserver) this, dataManager);
        this.place(animal);
        this.animals.add(animal);
        this.mapPanel.drawAnimal(animal);
    }

    public void generateAnimals() {
        int cnt = dataManager.getStartAnimalNumber();
        while (cnt-- > 0) {
            this.addRandomAnimal();
        }
    }

    @Override
    public void deleteDead() {
        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);
            if (a.getEnergy() <= 0) {
                sumOfDead++;
                sumOFDeadYears += a.getYears();
                this.mapPanel.eraseAnimal(a);
                map.get(a.getPosition()).remove(a);
                if (map.get(a.getPosition()).isEmpty()) {
                    map.remove(a.getPosition());
                }
                animals.remove(a);
            }
        }
    }

    @Override
    public void rotate() {
        for (Animal a : animals) {
            a.rotate();
        }
    }

    @Override
    public void move() {
        for (Animal a : animals) {
            this.mapPanel.eraseAnimal(a);
            if (a.getEnergy() <= dataManager.getMoveEnergy()) {
                a.decreaseEnergy(a.getEnergy() + 1.0);
            } else {
                a.move();
                a.decreaseEnergy(dataManager.getMoveEnergy());
            }
        }
    }

    @Override
    public void eat() {
        for(Map.Entry<Vector2D, List<Animal>> entry: map.entrySet()) {
            Vector2D vec = entry.getKey();
            List<Animal> animalsToEat = entry.getValue();
            if(isOccupied(vec) && !animalsToEat.isEmpty() && grasses.get(vec) != null) {
                Collections.sort(animalsToEat);

                if (animalsToEat.size() == 1) {
                    animalsToEat.get(0).encreaseEnergy(dataManager.getGrassEnergy());
                } else {
                    int cnt;
                    for (cnt = 1; cnt < animalsToEat.size() && animalsToEat.get(cnt - 1).getEnergy() == animalsToEat.get(cnt).getEnergy(); cnt++)
                        ;
                    for (int k = 0; k < cnt; k++) {
                        animalsToEat.get(k).encreaseEnergy(dataManager.getGrassEnergy() / cnt);
                    }
                }
                MapElement grass = grasses.get(vec);
                this.mapPanel.eraseGrass(grass);
                this.grasses.remove(grass.getPosition());
            }
        }
    }

    @Override
    public void procreate() {
        List<Animal> animalsToAdd = new ArrayList<>();
        for(Map.Entry<Vector2D, List<Animal>> entry: map.entrySet()) {
            Vector2D vec = entry.getKey();
            List<Animal> animalsToProcreate = entry.getValue();
            if(isOccupied(vec) && animalsToProcreate.size() > 1) {
                Collections.sort(animalsToProcreate);
                Animal a = map.get(vec).get(0);
                Animal b = map.get(vec).get(1);

                if (a.getEnergy() / dataManager.getStartEnergy() < 0.5) continue;
                if (b.getEnergy() / dataManager.getStartEnergy() < 0.5) continue;

                a.setChildren(a.getChildren() + 1);
                b.setChildren(b.getChildren() + 1);

                a.decreaseEnergy(a.getEnergy() * 0.25);
                b.decreaseEnergy(b.getEnergy() * 0.25);
                Animal c = new Animal(getPosAround(vec), new Genotype(a.getGenotype().getGenes(), b.getGenotype().getGenes()), a.getEnergy() * 0.25 + b.getEnergy() * 0.25, this, dataManager);
                animalsToAdd.add(c);
            }
        }

        for(Animal a: animalsToAdd) {
            this.animals.add(a);
            this.place(a);
            this.mapPanel.drawAnimal(a);
        }
    }

    private Vector2D getPosAround(Vector2D vec) {
        List<Vector2D> stepPositions = new ArrayList<>();

        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                Vector2D v = new Vector2D((vec.x + i + dataManager.getMapWidth()) % dataManager.getMapWidth(), (vec.y + j + dataManager.getMapHeight()) % dataManager.getMapHeight());
                if (!isOccupied(v)) {
                    stepPositions.add(v);
                }
            }
        if (stepPositions.isEmpty()) return vec;

        return stepPositions.get(generateRandom(0, stepPositions.size()));
    }

    @Override
    public void addGrass() {
        List<Vector2D> stepPositions = new ArrayList<>();
        List<Vector2D> junglePositions = new ArrayList<>();

        for(Map.Entry<Vector2D, List<Animal>> entry: map.entrySet()) {
            Vector2D vec = entry.getKey();
            if(!isOccupied(vec)){
                if (isInJungle(vec)) {
                    junglePositions.add(vec);
                } else {
                    stepPositions.add(vec);
                }
            }
        }

        if (!stepPositions.isEmpty()) {
            MapElement el = new MapElement(stepPositions.get(generateRandom(0, stepPositions.size())));
            this.mapPanel.drawGrass(el);
            grasses.put(el.getPosition(), el);
        }
        if (!junglePositions.isEmpty()) {
            MapElement el = new MapElement(junglePositions.get(generateRandom(0, junglePositions.size())));
            this.mapPanel.drawGrass(el);
            grasses.put(el.getPosition(), el);
        }

    }

    private int generateRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private boolean isInJungle(Vector2D position) {
        return position.follows(getJungleBegin()) && position.precedes(getJungleEnd());
    }

    public TreeMap<Vector2D, MapElement> getGrasses() {
        return grasses;
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }


}

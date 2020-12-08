import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine, ActionListener {
    private IWorldMap map1;
    private IWorldMap map2;
    DataManager dataManager;
    List<Animal> animalList;
    MapPanel mapPanel1;
    MapPanel mapPanel2;
    MainWindow m;
    private boolean SIMULATE;

    public SimulationEngine(){
        SIMULATE = false;
        this.dataManager = new DataManager();
        mapPanel1 = new MapPanel(dataManager.mapWidth, dataManager.mapHeight, new Vector2D(0,0), new Vector2D(0,0));
        mapPanel2 = new MapPanel(dataManager.mapWidth, dataManager.mapHeight, new Vector2D(0,0), new Vector2D(0,0));
        m = new MainWindow(this, dataManager, mapPanel1, mapPanel2);

        initialize();
    }
    public void initialize() {

        this.map1 = new WorldMap(dataManager, mapPanel1);
        this.map2 = new WorldMap(dataManager, mapPanel2);

        m.pack();

        map1.generateAnimals();
        //GENERATE GRASS

    }


    public void run(){
        //TODO

        TreeMap<Vector2D, List<Animal>> animals = map1.getAnimals();
        GrassMap grassMap = map1.getGrassMap();
        System.out.println(map1);
        int ages = 30;
        while(ages-- > 0) {

          for(int i = 0; i < this.animalList.size(); i++) {
               Animal a = this.animalList.get(i);
              mapPanel1.eraseAnimal(a);
               a.move();
              mapPanel1.drawAnimal(a);
           }

            System.out.println(map1);


            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Apply Changes":

                //mapa1.resizeMap(dataManager.mapWidth, dataManager.mapHeight, map.getJungleBegin(), map.getJungleEnd());
                //mapa2.resizeMap(dataManager.mapWidth, dataManager.mapHeight, map.getJungleBegin(), map.getJungleEnd());
                m.steerSecondMap(dataManager.twoMaps);
                initialize();
                m.pack();
                //
                break;
            case "Reset Default":
                dataManager.resetData();

                //mapa1.resizeMap(dataManager.mapWidth, dataManager.mapHeight, map.getJungleBegin(), map.getJungleEnd());
                //mapa2.resizeMap(dataManager.mapWidth, dataManager.mapHeight, map.getJungleBegin(), map.getJungleEnd());
                m.steerSecondMap(dataManager.twoMaps);
                initialize();
                m.pack();
                break;
            case "START":
                SIMULATE = true;
                startSimulationCycle();
                break;
            case "STOP":
                SIMULATE = false;
                break;
            case "Make Step":
                SIMULATE = false;
                cycle();
                break;
            default:
                break;
        }
    }

    private void cycle() {
        //map1.deleteDead();
        map1.rotate();
        map1.move();
        //map1.eat();
        //map1.procreate();
        //map1.addGrass();
        m.repaint();
    }

    private void startSimulationCycle() {
        int timerDelay = 1000;
        new Timer(timerDelay, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(!SIMULATE) return;
                cycle();
            }
        }).start();

    }

    private int generateRandom(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }



    public void runSimulation() {
        System.out.println("Hello");

        Genotype g1 = new Genotype();
        Genotype g2 = new Genotype();
        Genotype g3 = new Genotype(g1.getGenes(), g2.getGenes());
        //System.out.println(g1);
        //System.out.println(g2);
        //System.out.println(g3);
        //for(int i = 0; i < 10; i++) System.out.println(g3.getRandomGene());


        m.setVisible(true);

    }
}

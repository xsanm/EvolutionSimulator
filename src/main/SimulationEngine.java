import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private boolean SIMULATE1;
    private boolean SIMULATE2;
    StatPanel statPanel1;
    StatPanel statPanel2;

    private Timer stepTimer;

    public SimulationEngine(){
        SIMULATE1 = false;
        SIMULATE2 = false;

        this.dataManager = new DataManager();
        stepTimer = new Timer(dataManager.duration, this::startEngine);
        mapPanel1 = new MapPanel(dataManager, map1);
        mapPanel2 = new MapPanel(dataManager, map2);
        statPanel1 = new StatPanel(dataManager);
        statPanel2 = new StatPanel(dataManager);

        m = new MainWindow(this, dataManager, mapPanel1, mapPanel2, statPanel1, statPanel2);

        initialize();
    }
    public void initialize() {

        this.map1 = new WorldMap(dataManager, mapPanel1);
        this.map2 = new WorldMap(dataManager, mapPanel2);


        map1.generateAnimals();
        map1.generateGrasses();
        map1.countStats();
        dataManager.age = 0;
        statPanel1.refreshStats();

        if(dataManager.twoMaps) {
            map2.generateAnimals();
            map2.generateGrasses();
            map2.countStats();
            map2.countStats();
            statPanel2.refreshStats();
        }



        m.pack();
        //GENERATE GRASS

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
            case "START 1":
                SIMULATE1 = true;
                if(!stepTimer.isRunning()) stepTimer.start();
                //stepTimer = new Timer(dataManager.duration, this::startEngine);
                stepTimer.start();

                //startSimulationCycle();
                break;
            case "STOP 1":
                SIMULATE1 = false;
                if(stepTimer != null && !SIMULATE2){
                    stepTimer.stop();
                    //stepTimer = null;
                }
                break;
            case "START 2":
                SIMULATE2 = true;
                if(!stepTimer.isRunning()) stepTimer.start();
                //stepTimer = new Timer(dataManager.duration, this::startEngine);
                //stepTimer.start();

                //startSimulationCycle();
                break;
            case "STOP 2":
                SIMULATE2 = false;
                if(stepTimer != null && !SIMULATE1){
                    stepTimer.stop();
                    //stepTimer = null;
                }
                break;
            case "Make Step 1":
                SIMULATE1 = false;
                //System.out.println(dataManager);
                cycle(map1, statPanel1);
                break;
                case "Make Step 2":
                SIMULATE2 = false;
                //System.out.println(dataManager);
                cycle(map2, statPanel2);
                break;
            default:
                System.out.println("dgfhdfh");
        }
    }

    private void startEngine(ActionEvent e){
        if(SIMULATE1) cycle(map1, statPanel1);
        if(SIMULATE2)if(dataManager.twoMaps) cycle(map2, statPanel2);
    }

    public void speedChanged(){
        if(stepTimer != null && stepTimer.isRunning()){
            this.stepTimer.setDelay(dataManager.duration);
        }
    }

    private void cycle(IWorldMap map, StatPanel statPanel) {

        map.deleteDead();
        map.rotate();
        map.move();
        map.eat();
        map.procreate();
        map.addGrass();
        map.redrawAnimals();
        map.countStats();
        statPanel.refreshStats();
        m.repaint();
    }

    /*private void startSimulationCycle() {
        int timerDelay = 500;
        new Timer(dataManager.duration, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!SIMULATE) return;
                cycle(map1, statPanel1);
                if(dataManager.twoMaps) cycle(map2, statPanel2);
                System.out.println(dataManager.duration);
                System.out.println( ((Timer) e.getSource()).getDelay());
                ((Timer) e.getSource()).setDelay(dataManager.duration);
                ((Timer) e.getSource()).restart();
            }
        }).start();

    }*/

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
